package com.dyh.android.winehall.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.android.winehall.entity.response.BannerItemResponseBean;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.weigit.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 首页Banner适配器
 */
public class HomeBannerImageAdapter extends BannerAdapter<BannerItemResponseBean, HomeBannerImageAdapter.ImageTitleHolder> {


    public HomeBannerImageAdapter(List<BannerItemResponseBean> mData) {
        super(mData);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image_title, parent, false));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, BannerItemResponseBean data, int position, int size) {
        EasyGlide.loadImage(MyApplication.getInstance(), data.getImage_show(), holder.imageView);
        holder.title.setText(data.getTitle());
    }

    public static class ImageTitleHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView title;

        public ImageTitleHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.mImageView);
            title = view.findViewById(R.id.mBannerTitleTextView);
        }
    }
}