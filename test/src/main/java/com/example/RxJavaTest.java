package com.example;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.observers.DisposableObserver;
import java.util.concurrent.TimeUnit;
import org.junit.Test;

/**
 * Created by mingxxx on 2018/1/17.
 */

public class RxJavaTest {

  @Test
  public void test1() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
        emitter.onNext(1);
        emitter.onNext(2);
        try {
          Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        if (true) {
          throw new IllegalArgumentException("error");
        }
        emitter.onNext(3);
        emitter.onNext(4);
        emitter.onComplete();
      }
    })
              .onErrorReturnItem(9999)
              //.onErrorReturn(new Function<Throwable, Integer>() {
              //  @Override
              //  public Integer apply(Throwable throwable) throws Exception {
              //    return 999;
              //  }
              //})
              //.onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
              //  @Override
              //  public ObservableSource<? extends Integer> apply(Throwable throwable)
              //      throws Exception {
              //    return Observable.just(99);
              //  }
              //})
              //.onErrorResumeNext(new ObservableSource<Integer>() {
              //  @Override
              //  public void subscribe(Observer<? super Integer> observer) {
              //    observer.onNext(9);
              //    observer.onComplete();
              //  }
              //})
              .timeout(3, TimeUnit.SECONDS, Observable.<Integer>empty())
              //.subscribeOn(Schedulers.io())
              //.observeOn(ImmediateThinScheduler.INSTANCE)
              .subscribeWith(new DisposableObserver<Integer>() {
                @Override
                public void onNext(Integer integer) {
                  System.out.println("onNext:" + integer);
                }

                @Override
                public void onError(Throwable e) {
                  System.out.println("onError:" + e.getMessage());
                }

                @Override
                public void onComplete() {
                  System.out.println("onComplete");
                }
              });
  }
}
