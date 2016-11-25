package com.ackywow.daggersession.base;

import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.daggersession.MyApp;
import com.ackywow.daggersession.data.source.TasksDataSource;
import com.ackywow.daggersession.net.ApiService;
import com.ackywow.daggersession.net.RequestUtil;
import java.lang.ref.WeakReference;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class CommonPresenter<View extends BaseView> implements BasePresenter<View> {

  @NonNull
  private TasksDataSource tasksDataSource;
  @NonNull
  private BaseSchedulerProvider schedulerProvider;
  @NonNull
  private ApiService apiService;
  @NonNull
  private RequestUtil requestUtil;
  @NonNull
  private CompositeSubscription mSubscriptions;
  @NonNull
  private WeakReference<View> wrView;

  protected CommonPresenter() {
    mSubscriptions = new CompositeSubscription();
  }

  @Override
  public void addSubscription(Subscription subscription) {
    mSubscriptions.add(subscription);
  }

  @Override
  public void clearSubscriptions() {
    mSubscriptions.clear();
  }

  @Override
  public View getView() {
    return wrView.get();
  }

  @Override
  public void setView(@NonNull View view) {
    wrView = new WeakReference<View>(view);
  }

  @NonNull
  public TasksDataSource getTasksDataSource() {
    tasksDataSource = MyApp.getApplication().getTasksDataSource();
    return tasksDataSource;
  }

  @NonNull
  public BaseSchedulerProvider getSchedulerProvider() {
    schedulerProvider = MyApp.getApplication().getSchedulerProvider();
    return schedulerProvider;
  }

  @NonNull
  public ApiService getApiService() {
    apiService = MyApp.getApplication().getApiService();
    return apiService;
  }

  @NonNull
  public RequestUtil getrequestUtil() {
    requestUtil = MyApp.getApplication().getRequestUtil();
    return requestUtil;
  }
}
