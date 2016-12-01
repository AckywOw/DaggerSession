package com.ackywow.session.data.db.util;

import com.ackywow.session.data.db.bean.User;
import com.ackywow.session.data.db.dao.UserDao;

/**
 * Created by Jiang on 2016/11/29.
 */
public class UserDaoUtil extends AbstractMainDaoUtil<UserDao, User, String> {

  public UserDaoUtil(UserDao dao) {
    super(dao);
  }
}
