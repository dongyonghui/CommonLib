package com.dyh.android.winehall.util;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/8 0008 15:10
 * description:时间格式化类
 */
public class MyTimeFormatUtil {
    /**
     * 获取格式化后的字符串
     *
     * @param timeSecond
     * @return
     */
    public static String getDurationTimeString(int timeSecond) {
        if (timeSecond >= 86400) {
            return (timeSecond / 86400) + "天"
                    + (timeSecond % 86400 / 3600) + "小时";
        } else if (timeSecond >= 3600) {
            return (timeSecond / 3600) + "小时"
                    + (timeSecond % 3600 / 60) + "分";
        } else if (timeSecond >= 60) {
            return (timeSecond / 60) + "分"
                    + (timeSecond % 60) + "秒";
        }
        return timeSecond + "秒";
    }
}
