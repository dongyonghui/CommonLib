package com.dyh.common.lib.dw.util;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.FileProvider;

import java.io.File;

/**
 * 作者：Allan
 * 时间：2019/9/18/018
 * 邮箱：648731994@qq.com
 * 描述：设备操作类
 */
public class DeviceUtil {
    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    public static void openFile(Context context, String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        Intent myIntent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri uri = FileProvider
                .getUriForFile(context,
                        context.getApplicationContext().getPackageName() + ".provider",
                        file);
        String extension = android.webkit.MimeTypeMap.getFileExtensionFromUrl(uri.toString());
        String mimetype = android.webkit.MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        myIntent.setDataAndType(uri, mimetype);
        context.startActivity(myIntent);
    }

    /**
     * 打开指定文件夹
     *
     * @param context
     * @param path
     */
    public static void openAssignFolder(Context context, String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setDataAndType(FileProvider
                        .getUriForFile(context,
                                context.getApplicationContext().getPackageName() + ".provider",
                                file)
                , "file/*");
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }
}
