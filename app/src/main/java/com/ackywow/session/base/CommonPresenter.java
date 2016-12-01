package com.ackywow.session.base;

import android.support.annotation.NonNull;
import android.util.Log;
import java.lang.ref.WeakReference;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class CommonPresenter<View extends BaseMVPView> implements BasePresenter<View> {

  protected final String TAG = getClass().getSimpleName();

  @NonNull
  private CompositeSubscription mSubscriptions;
  @NonNull
  private WeakReference<View> wrView;

  protected CommonPresenter() {
    mSubscriptions = new CompositeSubscription();
  }

  @Override
  public void addSubscription(Subscription subscription) {
    Log.e(TAG, "addSubscription:" + subscription.toString());
    mSubscriptions.add(subscription);
  }

  @Override
  public void clearSubscriptions() {
    Log.e(TAG, "clearSubscriptions:");
    mSubscriptions.clear();
  }

  @Override
  public View getView() {
    return wrView.get();
  }

  @Override
  public void setView(@NonNull View view) {
    Log.e(TAG, "setView:" + view.toString());
    wrView = new WeakReference<View>(view);
  }
}
