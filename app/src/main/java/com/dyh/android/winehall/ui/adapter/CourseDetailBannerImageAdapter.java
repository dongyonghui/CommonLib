package com.dyh.android.winehall.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dyh.android.winehall.MyApplication;
import com.dyh.android.winehall.R;
import com.dyh.common.lib.glide.EasyGlide;
import com.dyh.common.lib.weigit.banner.adapter.BannerAdapter;

import java.util.List;

/**
 * 课程详情Banner适配器
 */
public class CourseDetailBannerImageAdapter extends BannerAdapter<String, CourseDetailBannerImageAdapter.ImageTitleHolder> {


    public CourseDetailBannerImageAdapter(List<String> mData) {
        super(mData);
    }

    @Override
    public ImageTitleHolder onCreateHolder(ViewGroup parent, int viewType) {
        return new ImageTitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner_image, parent, false));
    }

    @Override
    public void onBindView(ImageTitleHolder holder, String data, int position, int size) {
        EasyGlide.loadImage(MyApplication.getInstance(), data, holder.imageView);
    }

    public static class ImageTitleHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ImageTitleHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.mImageView);
        }
    }
}