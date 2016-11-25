package com.ackywow.daggersession.net;

import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.daggersession.base.BaseView;
import com.ackywow.daggersession.bean.HttpResult;
import com.ackywow.daggersession.bean.LoginInfo;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;

/**
 * Created by Jiang on 2016/11/24.
 */

public class ApiServiceImpl {

  @NonNull
  protected ApiService apiService;

  @NonNull
  protected BaseSchedulerProvider schedulerProvider;

  @NonNull
  protected BaseView view;

  public ApiServiceImpl(@NonNull ApiService apiService,
      @NonNull BaseSchedulerProvider schedulerProvider) {
    this.apiService = apiService;
    this.schedulerProvider = schedulerProvider;
  }

  public void setView(@NonNull BaseView view) {
    this.view = view;
  }

  public Subscription login(@Query("username") String username, @Query("password") String password,
      Subscriber<LoginInfo> subscriber) {
    return dealRxNetRequest(apiService.login(username, password), subscriber);
  }

  /**
   * 默认Rx方法发送网络请求及处理
   *
   * @param observable
   * @param subscriber
   * @param <T>
   * @return
   */
  private <T> Subscription dealRxNetRequest(Observable<HttpResult<T>> observable,
      Subscriber<T> subscriber) {
    return observable.subscribeOn(schedulerProvider.io())
        .map(new HttpResultFunc<T>())
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
  private <T> Subscription dealRxNetRequestWithLoadingDialog(Observable<HttpResult<T>> observable,
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
