package com.ackywow.session.data.db.util;

import com.ackywow.session.BuildConfig;
import java.util.List;
import java.util.concurrent.Callable;
import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

/**
 * 抽象通用数据库操作Dao
 * Created by Jiang on 16/1/20.
 *
 * @param <DAO> 表DAO
 * @param <E> 表
 * @param <K> 主键类型
 */
public class GenericDaoUtil<DAO extends AbstractDao<E, K>, E, K> {

  static {
    if (BuildConfig.DEBUG) {
      QueryBuilder.LOG_SQL = true;
      QueryBuilder.LOG_VALUES = true;
    }
  }

  private DAO dao;

  public GenericDaoUtil(DAO dao) {
    this.dao = dao;
  }

  public DAO getDao() {
    return dao;
  }

  private void clearDao() {
    dao = null;
  }

  /**
   * 插入数据
   *
   * @param t
   */
  public void insert(E t) {
    dao.insert(t);
  }

  /**
   * 插入或替换数据（主键相同）
   *
   * @param t
   */
  public void insertOrReplace(E t) {
    dao.insertOrReplace(t);
  }

  /**
   * 插入或替换数据（主键相同,开启事务）
   *
   * @param list
   */
  public void insertOrReplaceInTx(List<E> list) {
    dao.insertOrReplaceInTx(list);
  }

  /**
   * 批量添加数据
   */
  public void insertList(List<E> list) {
    dao.insertInTx(list);
  }

  /**
   * 更新一条数据（不一定有）
   *
   * @param e
   */
  public void update(E e) {
    try {
      dao.update(e);
    } catch (Exception e1) {
      e1.printStackTrace();
    }
  }

  /**
   * 查询根据主键
   */
  public E getByK(K key) {
    return dao.load(key);
  }

  /**
   * 批量查询
   */
  public List<E> getAll() {
    return dao.loadAll();
  }

  /**
   * 删除表中所有数据
   */
  public void clearTable() {
    dao.deleteAll();
    dao = null;
  }

  /**
   * 获取count
   */
  public long getTableCount() {
    int count = 0;
    count = (int) dao.count();
    return count;
  }

  /**
   * 删除某一条
   *
   * @param key
   */
  public void deleteByKey(K key) {
    dao.deleteByKey(key);
  }

  /**
   * 开启一个事物
   *
   * @param runnable
   */
  public void startTransaction(Runnable runnable) {
    dao.getSession()
       .runInTx(runnable);
  }

  /**
   * 开启一个事务
   *
   * @param callable
   * @param <T>
   */
  public <T> T startTransaction(Callable<T> callable) {
    T t = null;
    try {
      t = dao.getSession()
             .callInTx(callable);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return t;
  }

  /**
   * 获取查询qb
   *
   * @return
   */
  protected QueryBuilder<E> getQueryBuilder() {
    return dao.queryBuilder();
  }

  /**
   * 自定义高级查询
   *
   * @param where 条件
   * @return 用来直接查询的Query
   */
  private Query<E> getCustomQuery(String where) {
    QueryBuilder<E> queryBuilder = getQueryBuilder();
    if (queryBuilder != null) {
      Query<E> query = queryBuilder.where(new WhereCondition.StringCondition(where))
                                   .build();
      return query.forCurrentThread();
    }
    return null;
  }

  /**
   * 自定义高级查询
   *
   * @param where 条件
   * @return 查询到的唯一实体
   */
  public E queryCustomerT(String where) {
    Query<E> query = getCustomQuery(where);
    if (query != null
        && query.list()
                .size() > 0) {
      return query.unique();
    }
    return null;
  }

  /**
   * 自定义高级查询
   *
   * @param where 条件
   * @return 查询到的List
   */
  public List<E> queryCustomList(String where) {
    Query<E> query = getCustomQuery(where);
    query.forCurrentThread();
    if (query != null) {
      return query.list();
    }
    return null;
  }
}
