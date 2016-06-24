package com.ackywow.daggersession.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Named {
    String value() default "";
}
