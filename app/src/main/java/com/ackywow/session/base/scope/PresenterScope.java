package com.ackywow.session.base.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.inject.Scope;

/**
 * Created by Jiang on 2016/11/29.
 */
@Documented
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PresenterScope {
}
