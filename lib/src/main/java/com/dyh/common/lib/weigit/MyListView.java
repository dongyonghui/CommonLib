package com.dyh.common.lib.weigit;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

/**
 * 
 * @author baoyz
 * @date 2014-8-18
 * 
 */
public class MyListView extends ListView {


	public MyListView(Context context) {
		super(context);
	}

	public MyListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MyListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				View.MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
