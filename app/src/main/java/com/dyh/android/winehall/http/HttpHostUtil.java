package com.dyh.android.winehall.http;

public class HttpHostUtil {

    public static boolean IS_TEST_SERVICE = true;//是否使用测试环境


    /**
     * @return 返回默认的服务器host
     */
    public static String getDefaultRootUrl() {
        //正式环境地址
        String NET_DSN_PATH = "http://api.iservice.name:7020";

        //测试地址
        String NET_DSN_PATH_TEST = "http://api.iservice.name:7020";//融科北京

        return IS_TEST_SERVICE ?
                NET_DSN_PATH_TEST :
                NET_DSN_PATH;
    }
}
