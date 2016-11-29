package com.ackywow.session.data.db.upgrade;

import org.greenrobot.greendao.database.Database;

/**
 * 抽象数据库升级DBHelper
 * Created by Jiang on 16/1/19.
 */
public abstract class AbstractMigratorHelper {
  public abstract void onUpgrade(Database db);
}
