package com.ackywow.session;

import android.app.Application;
import android.util.Log;
import com.ackywow.session.data.db.bean.User;
import com.ackywow.session.data.user.UserComponent;
import com.ackywow.session.data.user.UserModule;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by AckywOw on 2016/6/5.
 */
public class App extends Application {

  private static App application;
  private AppComponent applicationComponent;
  private UserComponent userComponent;

  public static App getApplication() {
    return application;
  }

  public static AppComponent getApplicationComponent() {
    return getApplication().applicationComponent;
  }

  public static UserComponent getUserComponent() {
    return getApplication().userComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    application = this;
    applicationComponent = DaggerAppComponent.builder()
                                             .appModule(new AppModule(this))
                                             .build();
    releaseUserComponent();
    setRxJavaErrorHandler();
  }

  public void createUserComponent(User user) {
    userComponent = applicationComponent.newUserComponent(new UserModule(user));
  }

  public void releaseUserComponent() {
    createUserComponent(null);
  }

  /**
   * RxJava2 当取消订阅后(dispose())，RxJava抛出的异常后续无法接收(此时后台线程仍在跑，可能会抛出IO等异常),全部由RxJavaPlugin接收，需要提前设置ErrorHandler
   * 详情：http://engineering.rallyhealth.com/mobile/rxjava/reactive/2017/03/15/migrating-to-rxjava-2.html#Error Handling
   */
  private void setRxJavaErrorHandler() {
    RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
      @Override
      public void accept(Throwable throwable) throws Exception {
        throwable.printStackTrace();
        Log.e("ErrorHandler", throwable.toString());
      }
    });
  }
}
