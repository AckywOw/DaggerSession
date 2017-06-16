package com.ackywow.session.mvp;

import android.util.Log;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.data.db.bean.User;
import com.ackywow.session.data.db.util.NoteDaoUtil;
import com.ackywow.session.data.db.util.UserDaoUtil;
import com.ackywow.session.data.net.ApiService;
import com.ackywow.session.data.net.RxRequestUtil;
import com.ackywow.session.data.sp.SPDataUtil;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.MAIN_SPDATAUTIL;
import static com.ackywow.session.constant.Nameds.MVP;
import static com.ackywow.session.constant.Nameds.UNIQUE_ID;
import static com.ackywow.session.constant.Nameds.USER_SPDATAUTIL;

/**
 * Created by Jiang on 2016/11/17.
 */
public class MvpPresenter extends MvpContact.Presenter {

  @Inject
  ApiService apiService;
  @Inject
  BaseSchedulerProvider schedulerProvider;
  @Inject
  NoteDaoUtil noteDaoUtil;
  @Inject
  @Named(MAIN_SPDATAUTIL)
  SPDataUtil spDataUtil;

  @Inject
  @Named(MVP)
  String name;

  @Inject
  boolean isLogged;
  @Inject
  @Named(UNIQUE_ID)
  Lazy<String> uniqueIdLazy;
  @Inject
  @Named(USER_SPDATAUTIL)
  Lazy<SPDataUtil> spDataUtilLazy;
  @Inject
  Lazy<UserDaoUtil> userDaoUtilLazy;

  @Inject
  public MvpPresenter() {
  }

  @Override
  void loadNetDate() {
    try {
      Log.e(TAG, "isLogged:" + isLogged);
      Log.e(TAG, name);
      Log.e(TAG, noteDaoUtil.toString());
      Log.e(TAG, spDataUtil.toString());
      Disposable subscription = getSubscription2();
      addSubscription(subscription);
    } catch (Exception e) {
      e.printStackTrace();
      getView().showErrowDialog();
    }
  }

  public Disposable login(String username, String password, DisposableObserver<User> observer) {
    return RxRequestUtil.dealRxNetRequest(apiService.login(username, password), observer,
        schedulerProvider);
  }

  private Disposable getSubscription1() {
    return Observable.just("11111")
                     .map(new Function<String, Integer>() {
                       @Override
                       public Integer apply(@NonNull String s) throws Exception {
                         return s.hashCode();
                       }
                     })
                     .delay(3, TimeUnit.SECONDS)
                     .subscribeOn(schedulerProvider.computation())
                     .observeOn(schedulerProvider.ui(), true)
                     .subscribeWith(new DisposableObserver<Integer>() {
                       @Override
                       protected void onStart() {
                         getView().showToast("onStart: ");
                       }

                       @Override
                       public void onNext(@NonNull Integer integer) {
                         getView().showToast("onNext: " + integer);
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                         getView().showToast("onError: " + e.toString());
                       }

                       @Override
                       public void onComplete() {
                         getView().showToast("onCompleted: ");
                       }
                     });
  }

  private Disposable getSubscription2() {
    return Observable.create(new ObservableOnSubscribe<String>() {

      @Override
      public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
        e.onNext("first");
        e.onNext("second");
        e.onNext("third");
        //e.onCompleted();
      }
    })
                     .map(new Function<String, Integer>() {
                       @Override
                       public Integer apply(@NonNull String s) throws Exception {
                         return s.hashCode();
                       }
                     })
                     .delay(3, TimeUnit.SECONDS)
                     .subscribeOn(schedulerProvider.computation())
                     .observeOn(schedulerProvider.ui(), true)
                     .subscribeWith(new DisposableObserver<Integer>() {
                       @Override
                       public void onNext(@NonNull Integer integer) {
                         getView().showToast("Action1.call(): " + integer);
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                         getView().showToast("onError: " + e.toString());
                       }

                       @Override
                       public void onComplete() {
                         getView().showToast("onCompleted: ");
                       }
                     });
  }

  private Disposable getSubscription3() {
    List<String> list = new ArrayList();
    list.add("11111");
    list.add("2222");
    list.add("33333");
    list.add("44444");
    list.add("55555");
    return Observable.just(list)
                     .map(new Function<List<String>, List<String>>() {//修改数据并返回它
                       @Override
                       public List<String> apply(@NonNull List<String> strings) throws Exception {
                         return strings;
                       }
                     })
                     .flatMap(new Function<List<String>, Observable<String>>() { //修改数据并返回Observable
                       @Override
                       public Observable<String> apply(List<String> strings) {
                         return Observable.fromIterable(strings);
                       }
                     })
                     .observeOn(schedulerProvider.ui())
                     .doOnNext(new Consumer<String>() {
                       @Override
                       public void accept(@NonNull String s) throws Exception {
                         getView().showToast("doOnNext: " + s);
                       }
                     })
                     .observeOn(schedulerProvider.io())
                     .filter(new Predicate<String>() { //过滤
                       @Override
                       public boolean test(@NonNull String s) throws Exception {
                         return !"2222".equals(s);
                       }
                     })
                     .take(3) //截取数据数量
                     .delay(2, TimeUnit.SECONDS) //延迟发射
                     .subscribeOn(schedulerProvider.io())
                     .observeOn(schedulerProvider.ui(), true)
                     .subscribeWith(new DisposableObserver<String>() {

                       @Override
                       protected void onStart() {
                         getView().showToast("onStart: ");
                       }

                       @Override
                       public void onNext(@NonNull String s) {
                         getView().showToast("onNext: " + s);
                       }

                       @Override
                       public void onError(@NonNull Throwable e) {
                         getView().showToast("onError: " + e.toString());
                       }

                       @Override
                       public void onComplete() {
                         getView().showToast("onCompleted: ");
                       }
                     });
  }

  private Disposable getSubscription4() {
    return getObservable().subscribeWith(getSubscriber());
  }

  private Observable<String> getObservable() {
    List<String> list = new ArrayList();
    list.add("11111");
    list.add("22222");
    list.add("33333");
    list.add("44444");
    list.add("55555");
    return Observable.just(list)
                     .map(new Function<List<String>, List<String>>() {//修改数据并返回它
                       @Override
                       public List<String> apply(@NonNull List<String> strings) throws Exception {
                         return strings;
                       }
                     })
                     .flatMap(
                         new Function<List<String>, ObservableSource<String>>() {//修改数据并返回Observable
                           @Override
                           public ObservableSource<String> apply(@NonNull List<String> strings)
                               throws Exception {
                             return Observable.fromIterable(strings);
                           }
                         })
                     .observeOn(schedulerProvider.ui())
                     .doOnNext(new Consumer<String>() {
                       @Override
                       public void accept(@NonNull String s) throws Exception {
                         Log.i(TAG, "doOnNext:" + s);
                         getView().showToast("doOnNext: " + s);
                       }
                     })
                     .observeOn(schedulerProvider.io())
                     .filter(new Predicate<String>() {//过滤
                       @Override
                       public boolean test(@NonNull String s) throws Exception {
                         return !"11111".equals(s);
                       }
                     })
                     .take(2) //截取数据数量
                     .delay(3, TimeUnit.SECONDS) //延迟发射
                     .subscribeOn(schedulerProvider.io())
                     .observeOn(schedulerProvider.ui(), true);
  }

  private DisposableObserver<String> getSubscriber() {
    return new DisposableObserver<String>() {
      @Override
      protected void onStart() {
        Log.i(TAG, "onStart:");
        getView().showToast("onStart: ");
      }

      @Override
      public void onNext(@NonNull String s) {
        Log.i(TAG, "onNext:" + s);
        getView().showToast("onNext: " + s);
      }

      @Override
      public void onError(@NonNull Throwable e) {
        getView().showToast("onError: " + e.toString());
      }

      @Override
      public void onComplete() {
        Log.i(TAG, "onCompleted:");
        getView().showToast("onCompleted: ");
        //loadNetDate();
      }
    };
  }
}