
package com.dyh.common.lib.weigit.viewpager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.dyh.common.lib.R;

import java.util.LinkedList;
import java.util.List;

public abstract class BaseViewPagerAdapter<T> extends PagerAdapter
        implements ViewPagerHolderCreator {

    private final List<T> list = new LinkedList<T>();

    /**
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    public Context mContext;

    public BaseViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        //重点就在这儿了，不再是把布局写死，而是用接口提供的布局
        // 也不在这里绑定数据，数据绑定交给Api调用者。
        View view = getView(position, null, container);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public T getItem(int position) {
        if (position < 0 || position >= list.size()) {
            return null;
        }
        return this.list.get(position);
    }

    /**
     * 获取viewPager 页面展示View
     *
     * @param position
     * @param view
     * @param container
     * @return
     */
    private View getView(int position, View view, ViewGroup container) {

        ViewPagerHolder holder = null;
        if (view == null) {
            //创建Holder
            holder = createViewHolder();
            view = holder.createView(container.getContext());
            view.setTag(R.id.common_view_pager_item_tag, holder);
        } else {
            holder = (ViewPagerHolder) view.getTag(R.id.common_view_pager_item_tag);
        }
        if (holder != null) {
            // 数据绑定
            holder.onBind(container.getContext(), position, getItem(position));
        }

        return view;
    }


    public void addListBottom(List<T> list) {
        if (list != null) {
            this.list.addAll(list);
        }
        this.notifyDataSetChanged();
    }

    public void addListBottom(T item) {
        this.list.add(item);
        this.notifyDataSetChanged();
    }

    public void removeItem(T item) {
        this.list.remove(item);
        this.notifyDataSetChanged();
    }

    public void clearList() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void delete(int position) {
        if (position >= 0 && position < list.size()) {
            list.remove(position);
        }
        notifyDataSetChanged();
    }

    public void addListTop(List<T> list) {
        this.list.addAll(0, list);
        notifyDataSetChanged();
    }

    public void addListTop(T item) {
        this.list.add(0, item);
        notifyDataSetChanged();
    }
}
