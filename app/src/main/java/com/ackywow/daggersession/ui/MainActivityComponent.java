package com.ackywow.daggersession.ui;

import dagger.Subcomponent;

/**
 * Created by Jiang on 2016/11/21.
 */
@MainActivityScope
@Subcomponent(modules = MainActivityModule.class)
public interface MainActivityComponent {

  void inject(MainActivity activity);
}
