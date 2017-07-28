package com.ackywow.session.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 用作带图片tag的标题布局，可以带多个图片tag，左右均可，右边tag可以一部分左对齐，一部分跟随标题文字
 * Created by Jiang on 2017/7/28.
 */

public class GreatLayout extends RelativeLayout {

  public GreatLayout(Context context) {
    this(context, null);
  }

  public GreatLayout(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public GreatLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int childAllWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    TextView tv = null;
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      RelativeLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
      if (child instanceof TextView) {
        tv = (TextView) child;
        childAllWidth -= lp.leftMargin;
      } else {
        childAllWidth -= child.getMeasuredWidth() + lp.leftMargin;
      }
    }
    if (tv != null && tv.getMeasuredWidth() > childAllWidth) {
      tv.setWidth(childAllWidth);
    }
  }
}
