package com.ackywow.daggersession.net;

import com.ackywow.daggersession.bean.HttpResult;
import com.ackywow.daggersession.bean.LoginInfo;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Jiang on 2016/11/24.
 */

public interface ApiService {

  @GET("login")
  Observable<HttpResult<LoginInfo>> login(@Query("username") String username,
      @Query("password") String password);
}
