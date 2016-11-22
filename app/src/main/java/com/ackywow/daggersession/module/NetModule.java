package com.ackywow.daggersession.module;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.ackywow.daggersession.BuildConfig;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AckywOw on 2016/6/5.
 */
@Module
public class NetModule {

  @Provides
  @Singleton
  @Named("BASE_URL")
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
    int cacheSize = 10 * 1024 * 1024;
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
  static OkHttpClient provideOkHttpClient(Cache cache) {
    return new OkHttpClient.Builder().cache(cache).build();
  }

  @Provides
  @Singleton
  static Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient,
      @Named("BASE_URL") String baseUrl) {
    Retrofit retrofit =
        new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .build();
    return retrofit;
  }
}
