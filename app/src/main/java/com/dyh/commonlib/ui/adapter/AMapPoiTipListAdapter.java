package com.dyh.commonlib.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.amap.api.services.help.Tip;
import com.dyh.commonlib.R;
import com.dyh.common.lib.dw.listview.BaseListViewAdapter;

import java.util.ArrayList;

/**
 * 作者：DongYonghui
 * 邮箱：648731994@qq.com
 * 创建时间：2019/12/3/003 17:24
 * 描述：POI搜索列表
 */
public class AMapPoiTipListAdapter extends BaseListViewAdapter<Tip> implements Filterable {
    public AMapPoiTipListAdapter(Context context) {
        super(context);
    }

    @Override
    public View createView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_poi, null);
            viewHolder = new ViewHolder();
            viewHolder.mNameTextView = convertView.findViewById(R.id.mNameTextView);
            viewHolder.mAddressTextView = convertView.findViewById(R.id.mAddressTextView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.mNameTextView.setText(getItem(position).getName());
        viewHolder.mAddressTextView.setText(getItem(position).getAddress());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<CharSequence> newData = new ArrayList<>();
                if (!TextUtils.isEmpty(constraint)) {
                    newData.add(constraint);
                }
                results.values = newData;
                results.count = newData.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
            }
        };
        return filter;
    }

    static class ViewHolder {
        public TextView mNameTextView;
        public TextView mAddressTextView;
    }


}
