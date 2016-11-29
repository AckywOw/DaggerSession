package com.ackywow.session.data.db.util;

import org.greenrobot.greendao.AbstractDao;

/**
 * 主数据库操作数据库Dao
 * Created by Jiang on 16/1/20.
 *
 * @param <DAO> 表DAO
 * @param <E> 表
 * @param <K> 主键类型
 */
public abstract class AbstractMainDaoUtil<DAO extends AbstractDao<E, K>, E, K>
    extends GenericDaoUtil<DAO, E, K> {

  public AbstractMainDaoUtil(DAO dao) {
    super(dao);
  }
}
