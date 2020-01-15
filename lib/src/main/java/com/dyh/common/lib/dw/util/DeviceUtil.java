package com.dyh.common.lib.dw.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 作者：DongYonghui
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
}
