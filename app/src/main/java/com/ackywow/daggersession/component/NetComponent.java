package com.ackywow.daggersession.component;

import android.app.Activity;

import com.ackywow.daggersession.module.AppModule;
import com.ackywow.daggersession.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(Activity activity);
}
