package com.dyh.common.lib.weigit.pop_filter.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.dyh.common.lib.R;
import com.dyh.common.lib.weigit.pop_filter.bean.FiltrateBean;
import com.dyh.common.lib.weigit.pop_filter.view.adapter.ScreenListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScreenPopWindow extends PopupWindow {

    private final Activity context;
    private final List<FiltrateBean> dictList;
    private CustomHeightListView mListView;
    private TextView tvReset, tvConfirm;
    private View nullView, topView, bottomView;
    private ScreenListViewAdapter adapter;
    private OnConfirmClickListener onConfirmClickListener;
    private OnItemClickListener onItemClickListener;

    private int alpha = 0x3f000000;

    public ScreenPopWindow(Activity context, List<FiltrateBean> dictList) {
        this.context = context;
        this.dictList = dictList;
        initView();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        if (null != adapter) {
            adapter.setOnItemClickListener(onItemClickListener);
        }
    }

    //设置顶部分割线是否显示，以及颜色。默认true,#f3f3f3
    public ScreenPopWindow setTopView(Boolean bl, int color) {
        if (bl) {
            topView.setVisibility(View.VISIBLE);
        } else {
            topView.setVisibility(View.GONE);
        }
        topView.setBackgroundColor(color);
        return this;
    }

    //设置底部分割线是否显示，以及颜色。默认true,#f3f3f3
    public ScreenPopWindow setBottomView(Boolean bl, int color) {
        if (bl) {
            bottomView.setVisibility(View.VISIBLE);
        } else {
            bottomView.setVisibility(View.GONE);
        }
        bottomView.setBackgroundColor(color);
        return this;
    }

    //设置确定按钮的文字，字体大小，字体颜色，背景颜色。默认“确定”，14，#ffffff，#0aa666，
    public ScreenPopWindow setConfirm(String text, int size, int textColor, int color) {
        tvConfirm.setText(text);
        tvConfirm.setTextSize(size);
        tvConfirm.setTextColor(textColor);
        tvConfirm.setBackgroundColor(color);
        return this;
    }

    //设置重置按钮的文字，字体大小，字体颜色，背景颜色。默认“重置”，#000000，#ffffff
    public ScreenPopWindow setReset(String text, int size, int textColor, int color) {
        tvReset.setText(text);
        tvReset.setTextSize(size);
        tvReset.setTextColor(textColor);
        tvReset.setBackgroundColor(color);
        return this;
    }

    //设置阴影层的透明度
    public ScreenPopWindow setAlpha(int mAlpha) {
        alpha = mAlpha;
        return this;
    }

    //设置title的字体颜色,默认#000000
    public ScreenPopWindow setTitleColor(int color) {
        adapter.setTitleColor(color);
        return this;
    }

    //设置title的字体大小,默认14
    public ScreenPopWindow setTitleSize(int size) {
        adapter.setTitleSize(size);
        return this;
    }

    //设置item圆角大小，默认12
    public ScreenPopWindow setRadius(int radius) {
        adapter.setRadius(radius);
        return this;
    }

    //设置item边框粗细，默认2
    public ScreenPopWindow setStrokeWidth(int width) {
        adapter.setStrokeWidth(width);
        return this;
    }

    //设置item边框颜色，默认#0aa666
    public ScreenPopWindow setStrokeColor(int color) {
        adapter.setStrokeColor(color);
        return this;
    }

    //设置item宽度，默认是200dp
    public ScreenPopWindow setBoxWidth(int width) {
        adapter.setBoxWidth(width);
        return this;
    }

    //设置item高度，默认是WRAP_CONTENT
    public ScreenPopWindow setBoxHeight(int height) {
        adapter.setBoxHeight(height);
        return this;
    }

    //设置item选中时的颜色，默认#0aa666
    public ScreenPopWindow setChecked(String color) {
        adapter.setChecked(color);
        return this;
    }

    //设置item未选中时的颜色，默认#000000
    public ScreenPopWindow setEnabled(String color) {
        adapter.setEnabled(color);
        return this;
    }

    //设置item字体大小，默认13
    public ScreenPopWindow setBoxSize(int size) {
        adapter.setBoxSize(size);
        return this;
    }

    //设置是否开启单选，默认单选
    public ScreenPopWindow setSingle(boolean bl) {
        adapter.setSingle(bl);
        return this;
    }

    //显示控件时数据重置
    public ScreenPopWindow reset() {
        for (int x = 0; x < dictList.size(); x++) {
            if (dictList.get(x).getType() == FiltrateBean.TYPE_INPUT) {
                dictList.get(x).setInputText(null);
            } else if (dictList.get(x).getType() == FiltrateBean.TYPE_LIST) {
                List<FiltrateBean.Children> childrenBeen = dictList.get(x).getChildren();
                for (int y = 0; y < childrenBeen.size(); y++) {
                    if (childrenBeen.get(y).isSelected())
                        childrenBeen.get(y).setSelected(false);
                }
            }
        }
        return this;
    }

    //刷新数据
    public ScreenPopWindow replaceOrAdd(FiltrateBean filtrateBean) {
        if (dictList.contains(filtrateBean)) {
            int index = dictList.indexOf(filtrateBean);
            dictList.set(index, filtrateBean);
        } else {
            dictList.add(filtrateBean);
        }
        adapter.notifyDataSetChanged();
        return this;
    }

    //参数设置完毕，一定要build一下
    public void build() {
        initPop();
    }

    private void initView() {
        View popView = View.inflate(context, R.layout.flow_pop_listview, null);
        //设置view
        this.setContentView(popView);
        //设置宽高（也可设置为LinearLayout.LayoutParams.MATCH_PARENT或者LinearLayout.LayoutParams.MATCH_PARENT）
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置PopupWindow的焦点
        this.setFocusable(true);
        adapter = new ScreenListViewAdapter(context, dictList, onItemClickListener);
        mListView = popView.findViewById(R.id.listview);
        tvReset = popView.findViewById(R.id.tv_reset);
        tvConfirm = popView.findViewById(R.id.tv_confirm);
        nullView = popView.findViewById(R.id.view_null);
        topView = popView.findViewById(R.id.topView);
        bottomView = popView.findViewById(R.id.bottomView);

        setStrokeColor(context.getResources().getColor(R.color.dividing_line));
    }

    private void initPop() {
        //设置背景透明
        this.setBackgroundDrawable(new ColorDrawable(alpha));
        mListView.setAdapter(adapter);
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
                adapter.notifyDataSetChanged();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConfirmClickListener.onConfirmClick(getFilterSelectedValue());
                dismiss();
            }
        });
        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    /**
     * @return 获取选中的选项
     */
    public Map<String, FiltrateBean> getFilterSelectedValue() {
        Map<String, FiltrateBean> map = new HashMap<>();
        for (FiltrateBean fb : dictList) {
            FiltrateBean filtrateBean = new FiltrateBean();
            filtrateBean.setType(fb.getType());
            filtrateBean.setTitleName(fb.getTitleName());
            filtrateBean.setKey(fb.getKey());
            filtrateBean.setInputText(fb.getInputText());
            if (filtrateBean.getType() == FiltrateBean.TYPE_INPUT) {
                filtrateBean.setInputText(fb.getInputText());
                if (!TextUtils.isEmpty(fb.getInputText())) {
                    map.put(filtrateBean.getKey(), filtrateBean);
                }
            } else if (filtrateBean.getType() == FiltrateBean.TYPE_LIST) {
                List<FiltrateBean.Children> cdList = fb.getChildren();
                List<FiltrateBean.Children> selectedChildrenList = new ArrayList<>();
                for (int x = 0; x < cdList.size(); x++) {
                    FiltrateBean.Children children = cdList.get(x);
                    if (children.isSelected()) {
                        selectedChildrenList.add(children);
                    }
                }
                filtrateBean.setChildren(selectedChildrenList);
                if (selectedChildrenList.size() > 0) {
                    map.put(filtrateBean.getKey(), filtrateBean);
                }
            }
        }
        return map;
    }

    public void setOnConfirmClickListener(OnConfirmClickListener onConfirmClickListener) {
        this.onConfirmClickListener = onConfirmClickListener;
    }

    /**
     * 确定按钮监听
     */
    public interface OnConfirmClickListener {
        void onConfirmClick(Map<String, FiltrateBean> selectedValues);
    }

    /**
     * 单击子列表项目监听
     */
    public interface OnItemClickListener {
        void onItemClick(FiltrateBean parentBean, FiltrateBean.Children clickedItemBean);
    }

}
