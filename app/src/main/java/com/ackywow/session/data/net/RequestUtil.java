package com.ackywow.session.data.net;

import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.base.BaseView;
import com.ackywow.session.bean.HttpResult;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by Jiang on 2016/11/24.
 */

public class RequestUtil {

  @NonNull
  protected BaseSchedulerProvider schedulerProvider;

  @NonNull
  protected BaseView view;

  public RequestUtil(@NonNull BaseSchedulerProvider schedulerProvider) {
    this.schedulerProvider = schedulerProvider;
  }

  public void setView(@NonNull BaseView view) {
    this.view = view;
  }

  public <T> Subscription dealRxNetRequest(Observable<HttpResult<T>> observable,
      Subscriber<T> subscriber) {
    return dealRxNetRequest(observable, subscriber, new HttpResultFunc<T>());
  }

  /**
   * 默认Rx方法发送网络请求及处理
   *
   * @param observable
   * @param subscriber
   * @param <T>
   * @return
   */
  public <T> Subscription dealRxNetRequest(Observable<HttpResult<T>> observable,
      Subscriber<T> subscriber, Func1<HttpResult<T>, T> func1) {
    return observable.subscribeOn(schedulerProvider.io())
                     .map(func1)
                     .observeOn(schedulerProvider.ui())
                     .unsubscribeOn(schedulerProvider.io())
                     .subscribe(subscriber);
  }

  /**
   * 默认Rx方法发送网络请求及处理(带loading框)
   *
   * @param observable
   * @param subscriber
   * @param <T>
   * @return
   */
  public <T> Subscription dealRxNetRequestWithLoadingDialog(Observable<HttpResult<T>> observable,
      Subscriber<T> subscriber) {
    return observable.map(new HttpResultFunc<T>())
                     .subscribeOn(schedulerProvider.io())
                     .doOnSubscribe(new Action0() {
                       @Override
                       public void call() {
                         view.showLoadingDialog();
                       }
                     })
                     .subscribeOn(schedulerProvider.ui())
                     .doOnCompleted(new Action0() {
                       @Override
                       public void call() {
                         view.dismissLoadingDialog();
                       }
                     })
                     .observeOn(schedulerProvider.ui())
                     .unsubscribeOn(schedulerProvider.io())
                     .doOnUnsubscribe(new Action0() {
                       @Override
                       public void call() {
                         view.dismissLoadingDialog();
                       }
                     })
                     .unsubscribeOn(schedulerProvider.ui())
                     .subscribe(subscriber);
  }
}
