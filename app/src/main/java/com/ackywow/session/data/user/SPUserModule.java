package com.ackywow.session.data.user;

import android.app.Application;
import com.ackywow.session.base.scope.UserScope;
import com.ackywow.session.data.sp.SPDataUtil;
import com.ackywow.session.data.sp.SPUtil;
import com.ackywow.session.data.sp.SPUtilImpl;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.UNIQUE_ID;
import static com.ackywow.session.constant.Nameds.USER_SPDATAUTIL;
import static com.ackywow.session.constant.Nameds.USER_SPUTIL;

/**
 * Created by Jiang on 2016/11/30.
 */
@Module
public class SPUserModule {

  @Provides
  @UserScope
  @Named(USER_SPUTIL)
  static SPUtil provideSPUtil(Application application, @Named(UNIQUE_ID) String uniqueId) {
    return new SPUtilImpl(application, uniqueId);
  }

  @Provides
  @UserScope
  @Named(USER_SPDATAUTIL)
  static SPDataUtil provideSPDataUtil(@Named(USER_SPUTIL) SPUtil spUtil) {
    return new SPDataUtil(spUtil);
  }
}
