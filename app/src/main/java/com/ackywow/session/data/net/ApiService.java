package com.ackywow.session.data.net;

import com.ackywow.session.data.db.bean.User;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Jiang on 2016/11/24.
 */

public interface ApiService {

  @GET("login")
  Observable<HttpResult<User>> login(@Query("username") String username,
      @Query("password") String password);
}
