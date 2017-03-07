package com.ackywow.session.mvp;

import com.ackywow.session.base.BaseMVPActivity;
import com.ackywow.session.base.CommonPresenter;

/**
 * Created by ackywow on 2017/3/7.
 */

public interface MvpContact {

  abstract class View<P extends Presenter, Component> extends BaseMVPActivity<P, Component> {

    /**
     * 显示错误信息
     */
    abstract void showErrowDialog();

    abstract void showToast(String str);
  }

  abstract class Presenter extends CommonPresenter<View> {

    /**
     * 加载数据
     */
    abstract void loadNetDate();
  }
}
