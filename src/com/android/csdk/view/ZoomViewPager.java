package com.android.csdk.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ZoomViewPager extends ViewPager {

	public ZoomViewPager(Context context) {
		super(context);
	}
	
	public ZoomViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {		//fix event index out of range error
		boolean ret = false;
		try {
			ret = super.onInterceptTouchEvent(arg0);
		} catch (Exception e) {
			
		}
		return ret;
	}
}
