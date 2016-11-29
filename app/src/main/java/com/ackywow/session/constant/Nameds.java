package com.ackywow.session.constant;

import android.support.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.ackywow.session.constant.Nameds.Activity_Name;
import static com.ackywow.session.constant.Nameds.BASE_URL;
import static com.ackywow.session.constant.Nameds.ConnectTimeout;

/**
 * Created by Jiang on 2016/11/24.
 */
@StringDef({ BASE_URL, Activity_Name, ConnectTimeout })
@Retention(RetentionPolicy.SOURCE)
public @interface Nameds {
  String BASE_URL = "BASE_URL";
  String Activity_Name = "Activity_Name";
  String ConnectTimeout = "connectTimeout";

  String MVP = "MVP";
}
