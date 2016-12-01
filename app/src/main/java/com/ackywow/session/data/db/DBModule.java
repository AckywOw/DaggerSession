package com.ackywow.session.data.db;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.ackywow.session.base.scope.ApplicationScope;
import com.ackywow.session.data.db.dao.DaoMaster;
import com.ackywow.session.data.db.dao.DaoSession;
import com.ackywow.session.data.db.upgrade.MainDBUpgradeOpenHelper;
import com.ackywow.session.data.db.util.NoteDaoUtil;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

import static com.ackywow.session.constant.Nameds.MAIN_DAOMASTER;
import static com.ackywow.session.constant.Nameds.MAIN_DAOSESSION;
import static com.ackywow.session.constant.Nameds.MAIN_SQLITEDATABASE;

/**
 * Created by Jiang on 2016/11/28.
 */
@Module
public class DBModule {

  @Provides
  @ApplicationScope
  @Named(MAIN_SQLITEDATABASE)
  static SQLiteDatabase provideSQLiteDatabase(Application application) {
    return new MainDBUpgradeOpenHelper(application, DBConstants.getMainDBName(),
        null).getWritableDatabase();
  }

  @Provides
  @ApplicationScope
  @Named(MAIN_DAOMASTER)
  static DaoMaster provideDaoMaster(@Named(MAIN_SQLITEDATABASE) SQLiteDatabase sqLiteDatabase) {
    return new DaoMaster(sqLiteDatabase);
  }

  @Provides
  @ApplicationScope
  @Named(MAIN_DAOSESSION)
  static DaoSession provideDaoSession(@Named(MAIN_DAOMASTER) DaoMaster daoMaster) {
    return daoMaster.newSession(IdentityScopeType.None);
  }

  @Provides
  @ApplicationScope
  static NoteDaoUtil provideNoteDaoUtil(@Named(MAIN_DAOSESSION) DaoSession daoSession) {
    return new NoteDaoUtil(daoSession.getNoteDao());
  }
}
