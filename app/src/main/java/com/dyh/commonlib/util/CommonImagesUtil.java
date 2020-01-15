package com.dyh.commonlib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;

import com.dyh.common.lib.picture.PictureSelector;
import com.dyh.common.lib.picture.config.PictureConfig;
import com.dyh.common.lib.picture.config.PictureMimeType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 创建于：2019/4/12/012
 * 创建者：DongYonghui
 * 描述：图片处理共通逻辑
 */
public class CommonImagesUtil {

    public static final int GOODS_IMAGE_ASPECT_RATIO_X = 1;//图片长宽比例
    public static final int GOODS_IMAGE_ASPECT_RATIO_Y = 1;//图片长宽比例

    public static final int BANNER_IMAGE_ASPECT_RATIO_X = 698;//图片长宽比例
    public static final int BANNER_IMAGE_ASPECT_RATIO_Y = 175;//图片长宽比例

    /**
     * @param activity 选择商品图片
     */
    public static void selectGoodsImage(Activity activity) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .enableCrop(true)
                .previewImage(false)
                .withAspectRatio(GOODS_IMAGE_ASPECT_RATIO_X, GOODS_IMAGE_ASPECT_RATIO_Y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    /**
     * @param activity 选择Banner图片
     */
    public static void selectBannerImage(Activity activity) {
        PictureSelector.create(activity)
                .openGallery(PictureMimeType.ofImage())
                .selectionMode(PictureConfig.SINGLE)
                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .enableCrop(true)
                .previewImage(false)
                .withAspectRatio(BANNER_IMAGE_ASPECT_RATIO_X, BANNER_IMAGE_ASPECT_RATIO_Y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .compress(true)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    public static String saveImageToGallery(Context context, Bitmap bmp, String fileName) {
        String imgpath = Environment.getExternalStorageDirectory().toString() + "/HeBeiNM/";
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory().toString(), "HeBeiNM");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        imgpath += fileName;
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + imgpath)));
        return imgpath;
    }

    /**
     * 获取图片全路径
     *
     * @param pictures
     * @return
     */
    public static String getFullImageHttpUrl(String pictures) {
        if (TextUtils.isEmpty(pictures)) {
            return "";
        }
        if ((pictures.startsWith("http://") || pictures.startsWith("https://"))) {
            return pictures;
        }
        return "https://image.goukugogo.com/" + pictures;
    }

    public static byte[] getBitmapByte(Bitmap bitmap) {   //将bitmap转化为byte[]类型也就是转化为二进制
        if (null == bitmap) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
        try {
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }
}
