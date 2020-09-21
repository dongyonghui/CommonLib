package com.dyh.android.winehall.ui.pop;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dyh.common.lib.weigit.WrapLayout;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.local.LocalFilterItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选条件弹框
 */
public class FilterPopWindow extends PopupWindow {

    private final Activity context;
    private final List<LocalFilterItemBean> mFilterBeanList = new ArrayList<>();
    private final List<TextView> mTagTextViewList = new ArrayList<>();
    private WrapLayout mWarpLayout;
    private OnItemSelectedListener onItemSelectedListener;

    private int alpha = 0x3f000000;
    private int selectedColor;
    private int unselectedColor;
    private int selectedBgResId = R.drawable.question_item_type_bg_selected;
    private int unselectedBgResId = R.drawable.question_item_type_bg;

    public FilterPopWindow(Activity context) {
        this.context = context;
        selectedColor = context.getResources().getColor(R.color.text_0bb);
        unselectedColor = context.getResources().getColor(R.color.text_333);
        initView();
    }

    public void setFilterList(List<LocalFilterItemBean> list) {
        mFilterBeanList.clear();
        mFilterBeanList.addAll(list);
        resetTagViews();
    }

    public OnItemSelectedListener getOnItemSelectedListener() {
        return onItemSelectedListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    //设置阴影层的透明度
    public FilterPopWindow setAlpha(int mAlpha) {
        alpha = mAlpha;
        return this;
    }

    private void initView() {
        View popView = View.inflate(context, R.layout.pop_tag_filter, null);
        //设置view
        this.setContentView(popView);
        mWarpLayout = popView.findViewById(R.id.mWarpLayout);
        popView.findViewById(R.id.mEmptyView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的焦点
        this.setFocusable(true);

        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(alpha));
        resetTagViews();
    }

    private View.OnClickListener onTextClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            LocalFilterItemBean localFilterItemBean = (LocalFilterItemBean) v.getTag();
            if (null != onItemSelectedListener) {
                onItemSelectedListener.onItemClick(localFilterItemBean);
            }
            for (LocalFilterItemBean filterItemBean : mFilterBeanList) {
                filterItemBean.isSelected = false;
            }
            if (null != localFilterItemBean) {
                localFilterItemBean.isSelected = true;
            }
            refreshSelectedStatus();
        }
    };

    /**
     * 刷新UI选中状态
     */
    private void refreshSelectedStatus() {
        for (TextView textView : mTagTextViewList) {
            LocalFilterItemBean localFilterItemBean = (LocalFilterItemBean) textView.getTag();
            if (null != localFilterItemBean && localFilterItemBean.isSelected) {
                textView.setTextColor(selectedColor);
                textView.setBackgroundResource(selectedBgResId);
            } else {
                textView.setTextColor(unselectedColor);
                textView.setBackgroundResource(unselectedBgResId);
            }
        }
    }

    /**
     * 重新添加标签
     */
    private void resetTagViews() {
        //设置数据
        mTagTextViewList.clear();
        mWarpLayout.removeAllViews();
        for (LocalFilterItemBean localFilterItemBean : mFilterBeanList) {
            View rootView = LayoutInflater.from(context).inflate(R.layout.general_filter_item, null);
            TextView mTitleTextView = rootView.findViewById(R.id.mTitleTextView);
            mTitleTextView.setText(localFilterItemBean.name);
            mTitleTextView.setTag(localFilterItemBean);
            mTitleTextView.setOnClickListener(onTextClickListener);
            mTagTextViewList.add(mTitleTextView);

            mWarpLayout.addView(rootView);
        }
        refreshSelectedStatus();
    }

    /**
     * 单击子列表项目监听
     */
    public interface OnItemSelectedListener {
        void onItemClick(LocalFilterItemBean filterBean);
    }

}
