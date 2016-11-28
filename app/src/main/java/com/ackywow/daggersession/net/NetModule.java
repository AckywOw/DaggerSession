package com.ackywow.daggersession.net;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.daggersession.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.ackywow.daggersession.constant.Nameds.BASE_URL;
import static com.ackywow.daggersession.constant.Nameds.ConnectTimeout;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class NetModule {

  @Provides
  @Singleton
  @Named(BASE_URL)
  static String providesBaseUrl() {
    return BuildConfig.BASE_URL;
  }

  @Provides
  @Singleton
  static SharedPreferences providesSharedPreferences(Application application) {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides
  @Singleton
  static Cache provideOkHttpCache(Application application) {
    int cacheSize = 10 * 1024 * 1024;//10M
    Cache cache = new Cache(application.getCacheDir(), cacheSize);
    return cache;
  }

  @Provides
  @Singleton
  static Gson provideGson() {
    GsonBuilder builder = new GsonBuilder();
    builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
    return builder.create();
  }

  @Provides
  @Singleton
  @Named(ConnectTimeout)
  static long provideConnectTimeout() {
    return 10L;
  }

  @Provides
  @Singleton
  static OkHttpClient provideOkHttpClient(@Named(ConnectTimeout) long connectTimeout, Cache cache) {
    return new OkHttpClient.Builder().connectTimeout(connectTimeout, TimeUnit.SECONDS)
                                     .cache(cache)
                                     .build();
  }

  @Provides
  @Singleton
  static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient,
      @Named(BASE_URL) String baseUrl) {
    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
                              .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                              .baseUrl(baseUrl)
                              .client(okHttpClient)
                              .build();
    return retrofit;
  }

  @Provides
  @Singleton
  static ApiService provideApiService(Retrofit retrofit) {
    return retrofit.create(ApiService.class);
  }

  @Provides
  @Singleton
  static RequestUtil provideApiServiceImpl(BaseSchedulerProvider schedulerProvider) {
    return new RequestUtil(schedulerProvider);
  }
}
