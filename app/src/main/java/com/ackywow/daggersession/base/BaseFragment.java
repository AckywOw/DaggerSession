package com.ackywow.daggersession.base;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Jiang on 2016/11/16.
 */

public abstract class BaseFragment extends Fragment {

  protected Activity activity;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    activity = (Activity) context;
  }
}
