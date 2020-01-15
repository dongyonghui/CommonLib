package com.dyh.common.lib.dw.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Vondear
 * @date 2017/7/25
 * 震动帮助类
 * androidManifest.xml中加入 以下权限
 * <uses-permission android:name="android.permission.VIBRATE" />
 */
public class VibrateTool {
    private static Vibrator vibrator;
    private static Uri notification;
    private static Ringtone r;
    public static MediaPlayer mMediaPlayer;
    public static boolean mediaPlayerBoolean=true;

    /**
     * 简单震动
     * @param context     调用震动的Context
     * @param millisecond 震动的时间，毫秒
     */
    @SuppressWarnings("static-access")
    public static void vibrateOnce(Context context, int millisecond) {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(millisecond);
    }
//声音播放 millisecond 为播放时间，单位毫秒
    public static void defaultAlarmMediaPlayer(Context mContext) throws Exception {
        notification=  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(mContext, notification);
        r.play();
    }

    //声音一直播放
    public static void defaultAlarmMediaPlayer(Context mContext , int millisecond) throws Exception {
        notification=  RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        r = RingtoneManager.getRingtone(mContext, notification);
        r.play();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //延时执行的代码
                r.stop();
            }
        }, millisecond);
    }

    public static void defaultMediaPlayer(Context context, int i){
        Log.e("开始播放音频","开始播放音频");
        mMediaPlayer=MediaPlayer.create(context, i);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                mMediaPlayer.start();
            }
        },0,2000);





    }
    public static void stopMediaPlayer(){
        mediaPlayerBoolean=false;
        if(mMediaPlayer==null){
            return;
        }
        mMediaPlayer.stop();
    }



    /**
     * 复杂的震动
     * @param context 调用震动的Context
     * @param pattern 震动形式
     *                数组参数意义：
     *                      第一个参数为等待指定时间后开始震动，
     *                      震动时间为第二个参数。
     *                      后边的参数依次为等待震动和震动的时间
     * @param repeate 震动的次数，-1不重复，非-1为从pattern的指定下标开始重复 0为一直震动
     *
     *
     */
    @SuppressWarnings("static-access")
    public static void vibrateComplicated(Context context, long[] pattern, int repeate) {
        vibrator = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
        vibrator.vibrate(pattern, repeate);
    }

    /**
     * 停止震动
     */
    public static void vibrateStop() {
        if (vibrator != null) {
            vibrator.cancel();
        }
    }

    /**
     * 停止声音
     */
    public static void MediaPlayerStop() {
        if (r != null) {
            r.stop();
        }
    }
}