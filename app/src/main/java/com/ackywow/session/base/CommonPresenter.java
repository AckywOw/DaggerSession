package com.ackywow.session.base;

import android.support.annotation.NonNull;
import android.util.Log;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.lang.ref.WeakReference;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class CommonPresenter<View extends BaseMVPView> implements BasePresenter<View> {

  protected final String TAG = getClass().getSimpleName();

  @NonNull
  private CompositeDisposable mDisposables;
  @NonNull
  private WeakReference<View> wrView;

  protected CommonPresenter() {
    mDisposables = new CompositeDisposable();
  }

  @Override
  public void addSubscription(Disposable disposable) {
    Log.e(TAG, "addSubscription:" + disposable.toString());
    mDisposables.add(disposable);
  }

  @Override
  public void clearSubscriptions() {
    Log.e(TAG, "clearSubscriptions:");
    mDisposables.clear();
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
