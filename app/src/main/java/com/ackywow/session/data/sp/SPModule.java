package com.ackywow.session.data.sp;

import android.app.Application;
import com.ackywow.session.base.scope.ApplicationScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.MAIN_SPDATAUTIL;
import static com.ackywow.session.constant.Nameds.MAIN_SPUTIL;

/**
 * Created by Jiang on 2016/11/29.
 */
@Module
public class SPModule {

  @Provides
  @ApplicationScope
  @Named(MAIN_SPUTIL)
  static SPUtil provideSPUtil(Application application) {
    return new SPUtilImpl(application);
  }

  @Provides
  @ApplicationScope
  @Named(MAIN_SPDATAUTIL)
  static SPDataUtil provideSPDataUtil(@Named(MAIN_SPUTIL) SPUtil spUtil) {
    return new SPDataUtil(spUtil);
  }
}
