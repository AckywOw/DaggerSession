package com.ackywow.session;

import android.app.Application;
import com.ackywow.session.data.db.bean.User;
import com.ackywow.session.data.user.UserComponent;
import com.ackywow.session.data.user.UserModule;

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
  }

  public void createUserComponent(User user) {
    userComponent = applicationComponent.newUserComponent(new UserModule(user));
  }

  public void releaseUserComponent() {
    createUserComponent(null);
  }
}
