package com.ackywow.session.data.net;

import android.app.Application;
import com.ackywow.session.BuildConfig;
import com.ackywow.session.base.scope.ApplicationScope;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ackywow.session.constant.Nameds.BASE_URL;
import static com.ackywow.session.constant.Nameds.ConnectTimeout;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class NetModule {

  @Provides
  @ApplicationScope
  @Named(BASE_URL)
  static String providesBaseUrl() {
    return BuildConfig.BASE_URL;
  }

  @Provides
  @ApplicationScope
  static Cache provideOkHttpCache(Application application) {
    int cacheSize = 10 * 1024 * 1024;//10M
    Cache cache = new Cache(application.getCacheDir(), cacheSize);
    return cache;
  }

  @Provides
  @ApplicationScope
  static Gson provideGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return builder.create();
  }

  @Provides
  @ApplicationScope
  @Named(ConnectTimeout)
  static long provideConnectTimeout() {
    return 10L;
  }

  @Provides
  @ApplicationScope
  static OkHttpClient provideOkHttpClient(@Named(ConnectTimeout) long connectTimeout, Cache cache) {
    return new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS)
                                     .cache(cache)
                                     .build();
  }

  @Provides
  @ApplicationScope
  static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient,
      @Named(BASE_URL) String baseUrl) {
    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                              .baseUrl(baseUrl)
                              .client(okHttpClient)
                              .build();
    return retrofit;
  }

  @Provides
  @ApplicationScope
  static ApiService provideApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }
}
