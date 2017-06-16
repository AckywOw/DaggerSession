package com.ackywow.session.data.net;

import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.base.BaseView;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Jiang on 2016/11/24.
 */

public class RxRequestUtil {

  public static <T> Disposable dealRxNetRequest(Observable<HttpResult<T>> observable,
      DisposableObserver<T> observer, @NonNull BaseSchedulerProvider schedulerProvider) {
    return dealRxNetRequest(observable, observer, new HttpResultFunc<T>(), schedulerProvider);
  }

  /**
   * 默认Rx方法发送网络请求及处理
   *
   * @param observable
   * @param observer
   * @param <T>
   * @return
   */
  public static <T> Disposable dealRxNetRequest(Observable<HttpResult<T>> observable,
      DisposableObserver<T> observer, Function<HttpResult<T>, T> func1,
      BaseSchedulerProvider schedulerProvider) {
    return observable.subscribeOn(schedulerProvider.io())
                     .map(func1)
                     .observeOn(schedulerProvider.ui(), true)
                     .unsubscribeOn(schedulerProvider.io())
                     .subscribeWith(observer);
  }

  public static <T> Disposable dealRxNetRequestWithLoadingDialog(
      Observable<HttpResult<T>> observable, DisposableObserver<T> observer,
      @NonNull BaseSchedulerProvider schedulerProvider, @NonNull final BaseView view) {
    return dealRxNetRequestWithLoadingDialog(observable, observer, new HttpResultFunc<T>(),
        schedulerProvider, view);
  }

  /**
   * 默认Rx方法发送网络请求及处理(带loading框)
   *
   * @param observable
   * @param observer
   * @param <T>
   * @return
   */
  public static <T> Disposable dealRxNetRequestWithLoadingDialog(
      Observable<HttpResult<T>> observable, final DisposableObserver<T> observer,
      Function<HttpResult<T>, T> func1, BaseSchedulerProvider schedulerProvider,
      @NonNull final BaseView view) {
    return observable.map(func1)
                     .subscribeOn(schedulerProvider.io())
                     .doOnSubscribe(new Consumer<Disposable>() {
                       @Override
                       public void accept(@io.reactivex.annotations.NonNull Disposable disposable)
                           throws Exception {
                         view.showLoadingDialog();
                       }
                     })
                     .subscribeOn(schedulerProvider.ui())
                     .observeOn(schedulerProvider.ui(), true)
                     .doOnComplete(new Action() {
                       @Override
                       public void run() throws Exception {
                         view.dismissLoadingDialog();
                       }
                     })
                     .unsubscribeOn(schedulerProvider.io())
                     .doOnDispose(new Action() {
                       @Override
                       public void run() throws Exception {
                         view.dismissLoadingDialog();
                       }
                     })
                     .unsubscribeOn(schedulerProvider.ui())
                     .subscribeWith(observer);
  }
}
