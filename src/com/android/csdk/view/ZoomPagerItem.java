package com.android.csdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.android.csdk.R;

public class ZoomPagerItem extends RelativeLayout {
	
	private ZoomImageView mImageView;
	private ProgressBar mProgressBar;
	private LinearLayout mErrorView;
	
	public ZoomPagerItem(Context context) {
		super(context);
		init(context);
	}

	public ZoomPagerItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public ZoomPagerItem(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	private void init(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.zoom_pager_item, this);
		mImageView = (ZoomImageView)view.findViewById(R.id.img);
		mProgressBar = (ProgressBar)view.findViewById(R.id.progressbar);
		mErrorView = (LinearLayout)view.findViewById(R.id.error_view);
		
		mProgressBar.setVisibility(View.GONE);
		mErrorView.setVisibility(View.GONE);
	}
	
	public ZoomImageView getImageView() {
		return mImageView;
	}
	
}
