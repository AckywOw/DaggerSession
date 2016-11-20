package com.ackywow.daggersession.mvp;

import android.support.annotation.NonNull;

import com.ackywow.daggersession.R;

public class MvpActivity extends MVPContact.View {

    @Override
    void showErrowDialog() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mvp;
    }

    @NonNull
    @Override
    public MVPContact.Presenter initPresenter() {
        return new MvpPresenter();
    }
}
