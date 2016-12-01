package com.ackywow.session.base.component;

import com.ackywow.session.base.module.ActivityModule;
import com.ackywow.session.base.scope.ActivityScope;
import com.ackywow.session.mvp.MvpComponet;
import dagger.Subcomponent;

/**
 * Created by Jiang on 2016/12/1.
 */
@ActivityScope
@Subcomponent(
    modules = ActivityModule.class)
public interface ActivityComponent {
  MvpComponet newMvpComponet();
}
