package com.ackywow.session.data.sp;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Jiang on 2016/11/29.
 */
@Module
public class SPModule {

  @Provides
  @Singleton
  public SPUtil provideSPUtil(Application application) {
    return new SPUtilImpl(application);
  }

  @Provides
  @Singleton
  public SPDataUtil provideSPDataUtil(SPUtil spUtil) {
    return new SPDataUtil(spUtil);
  }
}
