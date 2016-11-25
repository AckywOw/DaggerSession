package com.ackywow.daggersession.mvp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Jiang on 2016/11/17.
 */

public class MvpPresenter extends MVPContact.Presenter {

  @Override
  void loadNetDate() {
    try {
      Subscription subscription = getSubscription3();
      addSubscription(subscription);
    } catch (Exception e) {
      e.printStackTrace();
      getView().showErrowDialog();
    }
  }

  private Subscription getSubscription1() {
    return Observable.just("11111")
        .map(new Func1<String, Integer>() {
          @Override
          public Integer call(String s) {
            return s.hashCode();
          }
        })
        .delay(3, TimeUnit.SECONDS)
        .subscribeOn(schedulerProvider.computation())
        .observeOn(schedulerProvider.ui())
        .subscribe(new Subscriber<Integer>() {

          @Override
          public void onStart() {
            getView().showToast("onStart: ");
          }

          @Override
          public void onCompleted() {
            getView().showToast("onCompleted: ");
          }

          @Override
          public void onError(Throwable e) {
            getView().showToast("onError: " + e.toString());
          }

          @Override
          public void onNext(Integer s) {
            getView().showToast("onNext: " + s);
          }
        });
  }

  private Subscription getSubscription2() {
    return Observable.create(new Observable.OnSubscribe<String>() {
      @Override
      public void call(Subscriber<? super String> subscriber) {
        subscriber.onNext("first");
        subscriber.onNext("second");
        subscriber.onNext("third");
        subscriber.onCompleted();
      }
    })
        .map(new Func1<String, Integer>() {
          @Override
          public Integer call(String s) {
            return s.hashCode();
          }
        })
        .delay(3, TimeUnit.SECONDS)
        .subscribeOn(schedulerProvider.computation())
        .observeOn(schedulerProvider.ui())
        .subscribe(new Action1<Integer>() {
          @Override
          public void call(Integer s) {
            getView().showToast("Action1.call(): " + s);
          }
        }, new Action1<Throwable>() {
          @Override
          public void call(Throwable throwable) {

          }
        }, new Action0() {
          @Override
          public void call() {
            getView().showToast("onCompleted: ");
          }
        });
  }

  private Subscription getSubscription3() {

    List<String> list = new ArrayList();
    list.add("11111");
    list.add("2222");
    list.add("33333");
    list.add("44444");
    list.add("55555");
    return Observable.just(list)
        .map(new Func1<List<String>, List<String>>() { //修改数据并返回它
          @Override
          public List<String> call(List<String> strings) {
            return strings;
          }
        })
        .flatMap(new Func1<List<String>, Observable<String>>() { //修改数据并返回Observable
          @Override
          public Observable<String> call(List<String> strings) {
            return Observable.from(strings);
          }
        }).observeOn(schedulerProvider.ui()).doOnNext(new Action1<String>() {
          @Override
          public void call(String s) {
            getView().showToast("doOnNext: " + s);
          }
        }).observeOn(schedulerProvider.io())
        .filter(new Func1<String, Boolean>() { //过滤
          @Override
          public Boolean call(String s) {
            return !"2222".equals(s);
          }
        }).take(3) //截取数据数量
        .delay(2, TimeUnit.SECONDS) //延迟发射
        .subscribeOn(schedulerProvider.io())
        .observeOn(schedulerProvider.ui())
        .subscribe(new Subscriber<String>() {

          @Override
          public void onStart() {
            getView().showToast("onStart: ");
          }

          @Override
          public void onCompleted() {
            getView().showToast("onCompleted: ");
          }

          @Override
          public void onError(Throwable e) {
            getView().showToast("onError: " + e.toString());
          }

          @Override
          public void onNext(String s) {
            getView().showToast("onNext: " + s);
          }
        });
  }
}