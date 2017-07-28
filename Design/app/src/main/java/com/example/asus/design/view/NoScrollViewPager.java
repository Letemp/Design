package com.example.asus.design.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wing on 11/4/16.
 */

public class NoScrollViewPager extends ViewPager {

  private boolean isPagingEnabled = false;
  public NoScrollViewPager(Context context) {
    super(context);
  }

  public NoScrollViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
  }
  int lastX;
  int lastY;

  /*@Override
  public boolean onTouchEvent(MotionEvent event) {
    int action = event.getAction();


    return this.isPagingEnabled && super.onTouchEvent(event);

  }


  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    return this.isPagingEnabled && super.onInterceptTouchEvent(event);
  }

  @Override public void scrollTo(int x, int y) {
    super.scrollTo(x, y);
  }*/
  @Override
  public boolean dispatchTouchEvent(MotionEvent ev) {
    int x = (int) ev.getRawX();
    int y = (int) ev.getRawY();
    int dealtX = 0;
    int dealtY = 0;

    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        dealtX = 0;
        dealtY = 0;
        // 保证子View能够接收到Action_move事件
        getParent().requestDisallowInterceptTouchEvent(true);
        break;
      case MotionEvent.ACTION_MOVE:
        dealtX += Math.abs(x - lastX);
        dealtY += Math.abs(y - lastY);
        // 这里是够拦截的判断依据是左右滑动，读者可根据自己的逻辑进行是否拦截
        if (dealtX >= dealtY) {
          getParent().requestDisallowInterceptTouchEvent(true);
        } else {
          getParent().requestDisallowInterceptTouchEvent(false);
        }
        lastX = x;
        lastY = y;
        break;
      case MotionEvent.ACTION_CANCEL:
        break;
      case MotionEvent.ACTION_UP:
        break;

    }
    return super.dispatchTouchEvent(ev);
  }

  @Override
  public boolean onTouchEvent(MotionEvent ev) {
    return true;
  }
}
