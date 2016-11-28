package com.ackywow.session.base;

import android.support.annotation.NonNull;
import android.util.Log;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.session.MyApp;
import com.ackywow.session.data.source.TasksDataSource;
import com.ackywow.session.net.ApiService;
import com.ackywow.session.net.RequestUtil;
import java.lang.ref.WeakReference;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class CommonPresenter<View extends BaseView> implements BasePresenter<View> {

  public static final String TAG = "CommonPresenter";

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
    Log.e(TAG, "addSubscription:" + subscription.toString());
    mSubscriptions.add(subscription);
  }

  @Override
  public void clearSubscriptions() {
    Log.e(TAG, "clearSubscriptions:");
    mSubscriptions.clear();
  }

  @Override
  public View getView() {
    return wrView.get();
  }

  @Override
  public void setView(@NonNull View view) {
    Log.e(TAG, "setView:" + view.toString());
    wrView = new WeakReference<View>(view);
  }

  @NonNull
  public TasksDataSource getTasksDataSource() {
    tasksDataSource = MyApp.getApplication()
                           .getTasksDataSource();
    return tasksDataSource;
  }

  @NonNull
  public BaseSchedulerProvider getSchedulerProvider() {
    schedulerProvider = MyApp.getApplication()
                             .getSchedulerProvider();
    return schedulerProvider;
  }

  @NonNull
  public ApiService getApiService() {
    apiService = MyApp.getApplication()
                      .getApiService();
    return apiService;
  }

  @NonNull
  public RequestUtil getrequestUtil() {
    requestUtil = MyApp.getApplication()
                       .getRequestUtil();
    return requestUtil;
  }
}
