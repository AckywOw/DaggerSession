package com.ackywow.daggersession.component;

import android.support.v4.app.Fragment;

import com.ackywow.daggersession.module.AppModule;
import com.ackywow.daggersession.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Component(modules = {AppModule.class, NetModule.class})
@Singleton
public interface PicComponent {
    void inject(Fragment fragment);
}
