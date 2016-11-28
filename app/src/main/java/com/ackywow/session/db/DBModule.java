package com.ackywow.session.db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.ackywow.session.db.upgrade.MainDBUpgradeOpenHelper;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

/**
 * Created by Jiang on 2016/11/28.
 */
@Module
public class DBModule {

  @Provides
  @Singleton
  static SQLiteDatabase getSQLiteDatabase(Application application) {
    return new MainDBUpgradeOpenHelper(application, DBConstants.getMainDBName(),
        null).getWritableDatabase();
  }

  @Provides
  @Singleton
  static DaoMaster getDaoMaster(SQLiteDatabase sqLiteDatabase) {
    return new DaoMaster(sqLiteDatabase);
  }

  @Provides
  @Singleton
  static DaoSession getDaoSession(DaoMaster daoMaster) {
    return daoMaster.newSession(IdentityScopeType.None);
  }
}
