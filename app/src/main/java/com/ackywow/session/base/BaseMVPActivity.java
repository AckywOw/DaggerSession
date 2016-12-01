package com.ackywow.session.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.ackywow.session.App;
import com.ackywow.session.base.component.ActivityComponent;
import com.ackywow.session.base.module.ActivityModule;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jiang on 2016/11/16.
 */
public abstract class BaseMVPActivity<Presenter extends CommonPresenter, Component>
    extends BaseActivity implements BaseMVPView<Presenter> {
  protected final String TAG = getClass().getSimpleName();

  protected ActivityComponent activityComponent;
  protected Component component;

  @Inject
  protected Presenter presenter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initActivityComponent();
    initComponent();
    inject();
    if (hasPresenter()) {
      setPresenter(presenter);
    }
  }

  protected void initActivityComponent() {
    activityComponent = App.getUserComponent()
                           .newActivityComponent(new ActivityModule(this));
  }

  protected void initComponent() {
    component = (Component) activityComponent;
  }

  protected abstract void inject();

  /**
   * 是否需要Presenter
   *
   * @return boolean
   */
  protected boolean hasPresenter() {
    return null != presenter;
  }

  @Override
  public void setPresenter(@NonNull Presenter presenter) {
    this.presenter = checkNotNull(presenter, presenter.getClass()
                                                      .getName() + " cannot be " +
        "null!");
    this.presenter.setView(this);
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (hasPresenter()) {
      presenter.clearSubscriptions();
    }
  }
}
