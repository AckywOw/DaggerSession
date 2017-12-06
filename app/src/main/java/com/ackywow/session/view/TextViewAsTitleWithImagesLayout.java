package com.ackywow.session.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ackywow.session.R;

/**
 * 用作带图片tag的标题布局，可以带多个图片tag，左右均可，右边tag可以一部分左对齐，一部分跟随标题文字
 * Created by Jiang on 2017/7/28.
 */

public class TextViewAsTitleWithImagesLayout extends RelativeLayout {

  private int target_id = NO_ID;

  public TextViewAsTitleWithImagesLayout(Context context) {
    this(context, null);
  }

  public TextViewAsTitleWithImagesLayout(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TextViewAsTitleWithImagesLayout(Context context, @Nullable AttributeSet attrs,
      int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray ta =
        context.obtainStyledAttributes(attrs, R.styleable.TextViewAsTitleWithImagesLayout,
            defStyleAttr, 0);
    for (int i = 0, count = ta.getIndexCount(); i < count; i++) {
      int attr = ta.getIndex(i);
      switch (attr) {
        case R.styleable.TextViewAsTitleWithImagesLayout_target:
          target_id = ta.getResourceId(attr, NO_ID);
          break;
        default:
          break;
      }
    }
    ta.recycle();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    int childAllWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
    TextView tv = null;
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      RelativeLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
      if (child instanceof TextView && child.getId() == target_id) {
        tv = (TextView) child;
        childAllWidth -= lp.leftMargin;
      } else if (child.getVisibility() != GONE) {
        childAllWidth -= child.getMeasuredWidth() + lp.leftMargin;
      }
    }
    if (tv != null && tv.getMeasuredWidth() >= childAllWidth) {
      tv.setMaxWidth(childAllWidth);
    } else {
      tv.setMaxWidth(Integer.MAX_VALUE);
    }
  }
}
