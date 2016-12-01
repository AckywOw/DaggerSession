package com.ackywow.session.mvp;

import com.ackywow.session.base.scope.PresenterScope;
import dagger.Subcomponent;

/**
 * Created by Jiang on 2016/11/29.
 */
@PresenterScope
@Subcomponent(modules = MvpModule.class)
public interface MvpComponet {
  void inject(MvpActivity mvpActivity);
}
