package com.dyh.common.lib.weigit.pop_filter.view.adapter;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyh.common.lib.R;
import com.dyh.common.lib.weigit.pop_filter.bean.FiltrateBean;
import com.dyh.common.lib.weigit.pop_filter.view.ScreenPopWindow;
import com.dyh.common.lib.weigit.pop_filter.view.SkuFlowLayout;

import java.util.List;

public class ScreenListViewAdapter extends BaseAdapter {

    private Activity context;
    private List<FiltrateBean> dictList;

    private int radius = 12;
    private int strokeWidth = 2;
    private int strokeColor;
    private int boxWidth = 200;
    private int boxHeight = LinearLayout.LayoutParams.WRAP_CONTENT;
    private String checked = "#0aa666";
    private String enabled = "#000000";
    private int boxSize = 13;
    private int titleColor;
    private int titleSize = 14;
    private boolean isSingle = true;
    private ScreenPopWindow.OnItemClickListener onItemClickListener;

    public ScreenListViewAdapter(Activity context, List<FiltrateBean> dictList, ScreenPopWindow.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.context = context;
        this.dictList = dictList;
        strokeColor = context.getResources().getColor(R.color.btn_normal);
        titleColor = context.getResources().getColor(R.color.text_black);
    }

    public ScreenPopWindow.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(ScreenPopWindow.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void setStrokeColor(int strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setBoxWidth(int boxWidth) {
        this.boxWidth = boxWidth;
    }

    public void setBoxHeight(int boxHeight) {
        this.boxHeight = boxHeight;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
    }

    public void setTitleColor(int titleColor) {
        this.titleColor = titleColor;
    }

    public void setTitleSize(int titleSize) {
        this.titleSize = titleSize;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    @Override
    public int getCount() {
        return dictList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_listview_property, null);
            vh = new ViewHolder();
            vh.tvTypeName = convertView.findViewById(R.id.tv_type_name);
            vh.mEditText = convertView.findViewById(R.id.mEditText);
            vh.layoutProperty = convertView.findViewById(R.id.layout_property);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        FiltrateBean filtrateBean = dictList.get(position);
        vh.tvTypeName.setText(filtrateBean.getTitleName());

        switch (filtrateBean.getType()) {
            case FiltrateBean.TYPE_LIST:
                vh.mEditText.setVisibility(View.GONE);
                vh.layoutProperty.setVisibility(View.VISIBLE);
                setFlowLayoutData(filtrateBean, vh.layoutProperty);
                break;
            case FiltrateBean.TYPE_INPUT:
                vh.mEditText.setVisibility(View.VISIBLE);
                vh.mEditText.setHint(filtrateBean.getTitleName());
                vh.mEditText.setText(filtrateBean.getInputText());
                vh.mEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        filtrateBean.setInputText(vh.mEditText.getText().toString());
                    }
                });
                vh.layoutProperty.setVisibility(View.GONE);
                break;
        }

        vh.tvTypeName.setTextColor(titleColor);
        vh.tvTypeName.setTextSize(titleSize);

        return convertView;
    }

    private void setFlowLayoutData(FiltrateBean filtrateBean, final SkuFlowLayout flowLayout) {
        final List<FiltrateBean.Children> childrenList = filtrateBean.getChildren();
        flowLayout.removeAllViews();
        for (int x = 0; x < childrenList.size(); x++) {
            FiltrateBean.Children item = childrenList.get(x);
            CheckBox checkBox = (CheckBox) View.inflate(context, R.layout.item_flowlayout_bill, null);
            GradientDrawable drawable = new GradientDrawable();
            //设置圆角大小
            drawable.setCornerRadius(radius);
            //设置shape背景色
            drawable.setStroke(strokeWidth, strokeColor);
            //设置宽高
            drawable.setSize(boxWidth, boxHeight);
            checkBox.setBackground(drawable);

            checkBox.setText(childrenList.get(x).getShowText());
            checkBox.setTextSize(boxSize);
            checkBox.setTextColor(createColorStateList(checked, enabled));
            if (childrenList.get(x).isSelected()) {
                checkBox.setChecked(true);
                childrenList.get(x).setSelected(true);
            } else {
                checkBox.setChecked(false);
                childrenList.get(x).setSelected(false);
            }

            final int finalX = x;
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != onItemClickListener) {
                        onItemClickListener.onItemClick(filtrateBean, item);
                    }
                    refreshCheckBox(flowLayout, finalX, childrenList);
                }
            });
            flowLayout.addView(checkBox);
        }
    }

    private static ColorStateList createColorStateList(String checked, String enabled) {
        int[] colors = new int[]{Color.parseColor(checked), Color.parseColor(enabled)};
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_checked};
        states[1] = new int[]{android.R.attr.state_enabled};
        return new ColorStateList(states, colors);
    }

    private void refreshCheckBox(SkuFlowLayout flowLayout, int finalX, List<FiltrateBean.Children> propBeenList) {
        for (int y = 0; y < flowLayout.getChildCount(); y++) {
            CheckBox radio = (CheckBox) flowLayout.getChildAt(y);
            if (isSingle) {
                radio.setChecked(false);
                propBeenList.get(y).setSelected(false);
            }
            if (finalX == y) {
                if (propBeenList.get(y).isSelected()) {
                    radio.setChecked(false);
                    propBeenList.get(y).setSelected(false);
                } else {
                    radio.setChecked(true);
                    propBeenList.get(y).setSelected(true);
                }
            }
        }
    }

    class ViewHolder {
        private TextView tvTypeName;
        private EditText mEditText;
        private SkuFlowLayout layoutProperty;
    }
}
