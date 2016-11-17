package com.ackywow.base;

import android.support.annotation.NonNull;

import rx.Subscription;

/**
 * Created by Jiang on 2016/11/16.
 */
public interface BasePresenter<View extends BaseView> {

    /**
     * 开始执行Task
     * @param subscription
     */
    void subscribe(@NonNull Subscription subscription);

    /**
     * 解绑所有task
     */
    void unSubscribe();

    /**
     * 设置View
     * @param view
     */
    void setView(@NonNull View view);

    /**
     * 获取View
     * @return View
     */
    View getView();
}
