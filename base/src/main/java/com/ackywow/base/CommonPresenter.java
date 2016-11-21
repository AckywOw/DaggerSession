package com.ackywow.base;

import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class CommonPresenter<View extends BaseView> implements BasePresenter<View> {

  private CompositeSubscription mSubscriptions;

  private WeakReference<View> wrView;

  protected CommonPresenter() {
    mSubscriptions = new CompositeSubscription();
  }

  @Override
  public void subscribe(Subscription subscription) {
    mSubscriptions.add(subscription);
  }

  @Override
  public void unSubscribe() {
    mSubscriptions.clear();
  }

  @Override
  public View getView() {
    return wrView.get();
  }

  @Override
  public void setView(@NonNull View view) {
    wrView = new WeakReference<View>(view);
  }
}
