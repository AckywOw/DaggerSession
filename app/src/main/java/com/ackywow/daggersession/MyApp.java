package com.ackywow.daggersession;

import android.app.Application;
import android.os.Looper;

import com.ackywow.daggersession.component.DaggerNetComponent;
import com.ackywow.daggersession.component.NetComponent;
import com.ackywow.daggersession.module.AppModule;
import com.ackywow.daggersession.module.NetModule;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class MyApp extends Application {

    private NetComponent mNetComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder().appModule(new AppModule(this)).netModule(new NetModule("a//a"))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
