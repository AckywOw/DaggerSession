package com.ackywow.daggersession.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class ContextModule {
    private Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    public Context getContext() {
        return mContext;
    }
}
