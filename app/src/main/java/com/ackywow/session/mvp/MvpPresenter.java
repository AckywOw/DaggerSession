package com.ackywow.session.mvp;

import android.util.Log;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.MyApp;
import com.ackywow.session.bean.LoginInfo;
import com.ackywow.session.data.db.util.NoteDaoUtil;
import com.ackywow.session.data.net.ApiService;
import com.ackywow.session.data.net.RequestUtil;
import com.ackywow.session.data.sp.SPDataUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

import static com.ackywow.session.constant.Nameds.MVP;

/**
 * Created by Jiang on 2016/11/17.
 */

public class MvpPresenter extends MvpContact.Presenter {

  @Inject
  RequestUtil requestUtil;

  @Inject
  ApiService apiService;

  @Inject
  BaseSchedulerProvider schedulerProvider;

  @Inject
  NoteDaoUtil noteDaoUtil;

  @Inject
  SPDataUtil spDataUtil;

  @Inject
  @Named(MVP)
  String name;

  public MvpPresenter() {
    MyApp.getApplicationComponent()
         .plusMvpComponet()
         .inject(this);
  }

  @Override
  void loadNetDate() {
    try {
      Log.e(TAG, name);
      Log.e(TAG, noteDaoUtil.toString());
      Log.e(TAG, spDataUtil.toString());
      Subscription subscription = getSubscription4();
      addSubscription(subscription);
    } catch (Exception e) {
      e.printStackTrace();
      getView().showErrowDialog();
    }
  }

  public Subscription login(String username, String password, Subscriber<LoginInfo> subscriber) {
    return requestUtil.dealRxNetRequest(apiService.login(username, password), subscriber);
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
                     })
                     .observeOn(schedulerProvider.ui())
                     .doOnNext(new Action1<String>() {
                       @Override
                       public void call(String s) {
                         getView().showToast("doOnNext: " + s);
                       }
                     })
                     .observeOn(schedulerProvider.io())
                     .filter(new Func1<String, Boolean>() { //过滤
                       @Override
                       public Boolean call(String s) {
                         return !"2222".equals(s);
                       }
                     })
                     .take(3) //截取数据数量
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

  private Subscription getSubscription4() {
    return getObservable().subscribe(getSubscriber());
  }

  private Observable<String> getObservable() {
    List<String> list = new ArrayList();
    list.add("11111");
    list.add("22222");
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
                     })
                     .observeOn(schedulerProvider.ui())
                     .doOnNext(new Action1<String>() {
                       @Override
                       public void call(String s) {
                         Log.i(TAG, "doOnNext:" + s);
                         getView().showToast("doOnNext: " + s);
                       }
                     })
                     .observeOn(schedulerProvider.io())
                     .filter(new Func1<String, Boolean>() { //过滤
                       @Override
                       public Boolean call(String s) {
                         return !"11111".equals(s);
                       }
                     })
                     .take(2) //截取数据数量
                     .delay(3, TimeUnit.SECONDS) //延迟发射
                     .subscribeOn(schedulerProvider.io())
                     .observeOn(schedulerProvider.ui());
  }

  private Subscriber<String> getSubscriber() {
    return new Subscriber<String>() {

      @Override
      public void onStart() {
        Log.i(TAG, "onStart:");
        getView().showToast("onStart: ");
      }

      @Override
      public void onCompleted() {
        Log.i(TAG, "onCompleted:");
        getView().showToast("onCompleted: ");
        //loadNetDate();
      }

      @Override
      public void onError(Throwable e) {
        getView().showToast("onError: " + e.toString());
      }

      @Override
      public void onNext(String s) {
        Log.i(TAG, "onNext:" + s);
        getView().showToast("onNext: " + s);
      }
    };
  }
}