package com.ackywow.daggersession.base;

import android.support.annotation.NonNull;
import com.ackywow.base.util.schedulers.BaseSchedulerProvider;
import com.ackywow.daggersession.MyApp;
import com.ackywow.daggersession.data.source.TasksDataSource;
import java.lang.ref.WeakReference;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class CommonPresenter<View extends BaseView> implements BasePresenter<View> {

  @NonNull
  protected TasksDataSource tasksDataSource;
  @NonNull
  protected BaseSchedulerProvider schedulerProvider;
  @NonNull
  private CompositeSubscription mSubscriptions;
  @NonNull
  private WeakReference<View> wrView;

  protected CommonPresenter() {
    mSubscriptions = new CompositeSubscription();
    tasksDataSource = MyApp.getApplication().getTasksDataSource();
    schedulerProvider = MyApp.getApplication().getSchedulerProvider();
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
}
