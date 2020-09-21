package com.dyh.common.lib.download;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

/**
 * 类功能描述：下载器后台服务</br>
 *
 * @author zhuiji7  (470508081@qq.com)
 * @version 1.0
 * </p>
 */

public class FileDownLoadService extends Service {
    private static FileDownLoadManager fileDownLoadManager;
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    
    public static FileDownLoadManager getFileDownLoadManager(){
        return fileDownLoadManager;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            //数字是随便写的“40”，
            nm.createNotificationChannel(new NotificationChannel("40", "App Service", NotificationManager.IMPORTANCE_DEFAULT));
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "40");

            //其中的2，是也随便写的，正式项目也是随便写
            startForeground(2 ,builder.build());
        }
        fileDownLoadManager = new FileDownLoadManager(FileDownLoadService.this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放downLoadManager
        fileDownLoadManager.stopAllTask();
        fileDownLoadManager = null;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        if(fileDownLoadManager == null){
            fileDownLoadManager = new FileDownLoadManager(FileDownLoadService.this);
        }
    }
    
    
    
    
    

}
