package com.dyh.common.lib.dw.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.dyh.common.lib.R;


/**
 * 作者：DongYonghui
 * 时间：2019/11/1/001
 * 邮箱：648731994@qq.com
 * 描述：空页面工具类
 */
public class EmptyViewUtil {
    public interface OnEmptyViewClicked {
        void onEmptyViewClicked(View view);
    }

    public static View getEmptyView(Context context, final OnEmptyViewClicked onEmptyViewClicked) {
        //初始化空数据页面
        final View mEmptyView = LayoutInflater.from(context).inflate(R.layout.layout_info_view, null);
        TextView infoText = mEmptyView.findViewById(R.id.mInfoTextView);
        infoText.setText(R.string.noDataClick2Refresh);
        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onEmptyViewClicked) {
                    onEmptyViewClicked.onEmptyViewClicked(mEmptyView);
                }
            }
        });
        return mEmptyView;
    }
}
