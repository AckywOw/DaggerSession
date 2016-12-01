package com.ackywow.session.data.user;

import com.ackywow.session.base.scope.UserScope;
import com.ackywow.session.data.db.bean.User;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

import static com.ackywow.session.constant.Nameds.UNIQUE_ID;

/**
 * Created by Jiang on 2016/11/30.
 */
@Module
public class UserModule {

  private User user;

  public UserModule(User user) {
    this.user = user;
  }

  @Provides
  @UserScope
  public boolean isLogged() {
    return null != user;
  }

  @Provides
  @UserScope
  public User provideUser() {
    return user;
  }

  @Provides
  @UserScope
  @Named(UNIQUE_ID)
  public String provideUniqueId() {
    return "user_" + user.getUserId();
  }
}
