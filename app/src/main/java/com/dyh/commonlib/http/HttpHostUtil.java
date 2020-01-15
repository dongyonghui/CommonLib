package com.dyh.commonlib.http;

public class HttpHostUtil {

    public static boolean IS_TEST_SERVICE = true;//是否使用测试环境

    public static String getPassportRootUrl() {
        return "https://" + (IS_TEST_SERVICE ? "fat-" : "") + "passport.goukugogo.com";
    }

    public static String getShopRootUrl() {
        return "https://" + (IS_TEST_SERVICE ? "fat-" : "") + "shop.goukugogo.com";
    }

    public static String getTradeRootUrl() {
        return "https://" + (IS_TEST_SERVICE ? "fat-" : "") + "trade.goukugogo.com";
    }

}
