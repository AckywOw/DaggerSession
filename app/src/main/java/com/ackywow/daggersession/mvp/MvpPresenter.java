package com.ackywow.daggersession.mvp;

import rx.Subscription;

/**
 * Created by Jiang on 2016/11/17.
 */

public class MvpPresenter extends MVPContact.Presenter {

  @Override
  void loadNetDate() {
    Subscription subscription = null;
    // TODO: initTask
    subscribe(subscription);
  }
}
