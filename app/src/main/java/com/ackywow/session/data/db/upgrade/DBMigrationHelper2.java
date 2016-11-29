package com.ackywow.session.data.db.upgrade;

import com.ackywow.session.data.db.dao.NoteDao;
import org.greenrobot.greendao.database.Database;

/**
 * Created by Jiang on 16/1/19.
 */
public class DBMigrationHelper2 extends AbstractMigratorHelper {
  @Override
  public void onUpgrade(Database db) {
    MigrationHelper.migrate(db, NoteDao.class);
  }
}
