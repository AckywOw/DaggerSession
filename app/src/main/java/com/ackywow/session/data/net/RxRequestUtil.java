package com.ackywow.session.data.net;

import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.base.BaseView;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Func1;

/**
 * Created by Jiang on 2016/11/24.
 */

public class RxRequestUtil {

  public static <T> Subscription dealRxNetRequest(Observable<HttpResult<T>> observable,
      Subscriber<T> subscriber, @NonNull BaseSchedulerProvider schedulerProvider) {
    return dealRxNetRequest(observable, subscriber, new HttpResultFunc<T>(), schedulerProvider);
  }

  /**
   * 默认Rx方法发送网络请求及处理
   *
   * @param observable
   * @param subscriber
   * @param <T>
   * @return
   */
  public static <T> Subscription dealRxNetRequest(Observable<HttpResult<T>> observable,
      Subscriber<T> subscriber, Func1<HttpResult<T>, T> func1,
      BaseSchedulerProvider schedulerProvider) {
    return observable.subscribeOn(schedulerProvider.io())
                     .map(func1)
                     .observeOn(schedulerProvider.ui(), true)
                     .unsubscribeOn(schedulerProvider.io())
                     .subscribe(subscriber);
  }

  public static <T> Subscription dealRxNetRequestWithLoadingDialog(
      Observable<HttpResult<T>> observable, Subscriber<T> subscriber,
      @NonNull BaseSchedulerProvider schedulerProvider, @NonNull final BaseView view) {
    return dealRxNetRequestWithLoadingDialog(observable, subscriber, new HttpResultFunc<T>(),
        schedulerProvider, view);
  }

  /**
   * 默认Rx方法发送网络请求及处理(带loading框)
   *
   * @param observable
   * @param subscriber
   * @param <T>
   * @return
   */
  public static <T> Subscription dealRxNetRequestWithLoadingDialog(
      Observable<HttpResult<T>> observable, Subscriber<T> subscriber, Func1<HttpResult<T>, T> func1,
      BaseSchedulerProvider schedulerProvider, @NonNull final BaseView view) {
    return observable.map(func1)
                     .subscribeOn(schedulerProvider.io())
                     .doOnSubscribe(new Action0() {
                       @Override
                       public void call() {
                         view.showLoadingDialog();
                       }
                     })
                     .subscribeOn(schedulerProvider.ui())
                     .observeOn(schedulerProvider.ui(), true)
                     .doOnCompleted(new Action0() {
                       @Override
                       public void call() {
                         view.dismissLoadingDialog();
                       }
                     })
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
