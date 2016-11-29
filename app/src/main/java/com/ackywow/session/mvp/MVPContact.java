package com.ackywow.session.mvp;

import com.ackywow.session.base.BaseActivity;
import com.ackywow.session.base.CommonPresenter;

/**
 * Created by Jiang on 2016/11/17.
 */

public interface MvpContact {

  abstract class View extends BaseActivity<Presenter> {

    /**
     * 显示错误信息
     */
    abstract void showErrowDialog();

    abstract void showToast(String str);

    @Override
    protected boolean hasPresenter() {
      return true;
    }
  }

  abstract class Presenter extends CommonPresenter<View> {

    /**
     * 加载数据
     */
    abstract void loadNetDate();
  }
}
