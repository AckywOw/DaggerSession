package com.ackywow.session.data.user;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import com.ackywow.session.base.scope.UserScope;
import com.ackywow.session.data.db.dao.DaoMaster;
import com.ackywow.session.data.db.dao.DaoSession;
import com.ackywow.session.data.db.upgrade.MainDBUpgradeOpenHelper;
import com.ackywow.session.data.db.util.UserDaoUtil;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import org.greenrobot.greendao.identityscope.IdentityScopeType;

import static com.ackywow.session.constant.Nameds.UNIQUE_ID;
import static com.ackywow.session.constant.Nameds.USER_DAOMASTER;
import static com.ackywow.session.constant.Nameds.USER_DAOSESSION;
import static com.ackywow.session.constant.Nameds.USER_SQLITEDATABASE;

/**
 * Created by Jiang on 2016/11/28.
 */
@Module
public class DBUserModule {

  @Provides
  @UserScope
  @Named(USER_SQLITEDATABASE)
  static SQLiteDatabase provideSQLiteDatabase(Application application,
      @Named(UNIQUE_ID) String uniqueId) {
    return new MainDBUpgradeOpenHelper(application, uniqueId, null).getWritableDatabase();
  }

  @Provides
  @UserScope
  @Named(USER_DAOMASTER)
  static DaoMaster provideDaoMaster(@Named(USER_SQLITEDATABASE) SQLiteDatabase sqLiteDatabase) {
    return new DaoMaster(sqLiteDatabase);
  }

  @Provides
  @UserScope
  @Named(USER_DAOSESSION)
  static DaoSession provideDaoSession(@Named(USER_DAOMASTER) DaoMaster daoMaster) {
    return daoMaster.newSession(IdentityScopeType.None);
  }

  @Provides
  @UserScope
  static UserDaoUtil provideNoteDaoUtil(@Named(USER_DAOSESSION) DaoSession daoSession) {
    return new UserDaoUtil(daoSession.getUserDao());
  }
}
