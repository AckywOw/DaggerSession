package com.ackywow.session.mvp;

import com.ackywow.session.base.scope.PresenterScope;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.MVP;

/**
 * Created by Jiang on 2016/11/29.
 */
@Module
public class MvpModule {
  @Provides
  @Named(MVP)
  @PresenterScope
  static String provideName() {
    return "MVP";
  }
}
