package com.ackywow.daggersession.mvp;

import com.ackywow.base.BaseActivity;
import com.ackywow.base.CommonPresenter;

/**
 * Created by Jiang on 2016/11/17.
 */

public interface MVPContact {

    abstract class View extends BaseActivity<Presenter> {

        /**
         * 显示错误信息
         */
        abstract void showErrowDialog();
    }

    abstract class Presenter extends CommonPresenter<View> {

        /**
         * 加载数据
         */
        abstract void loadNetDate();
    }
}
