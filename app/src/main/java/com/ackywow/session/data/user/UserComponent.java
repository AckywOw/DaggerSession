package com.ackywow.session.data.user;

import com.ackywow.session.base.component.ActivityComponent;
import com.ackywow.session.base.module.ActivityModule;
import com.ackywow.session.base.scope.UserScope;
import dagger.Subcomponent;

/**
 * Created by Jiang on 2016/11/30.
 */
@UserScope
@Subcomponent(
    modules = {
        UserModule.class, SPUserModule.class, DBUserModule.class
    })
public interface UserComponent {
  //MvpComponet newMvpComponet();

  ActivityComponent newActivityComponent(ActivityModule mainActivityModule);
}
