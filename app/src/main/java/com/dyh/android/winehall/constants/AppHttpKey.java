package com.dyh.android.winehall.constants;

/**
 * 作者：lblbh on 2016/12/5 16:20
 * 邮箱：lanbuhan@163.com
 * 功能：全局统一请求key管理
 */
public interface AppHttpKey {
    String PAGE = "page";               //页码，从1开始
    String PAGE_SIZE = "page_size";     //页大小，默认20

    String TYPE = "type";               //类型--参考ServerType类
    String GENRE_ID = "genre_id";       //类型id-单选
    String CATEGORY_ID = "category_id"; //分类id-单选
    String SUPER = "super"; //是否为超级播放器：1-是
    String SOURCE_ID = "source_id"; //课节uuid
    String TIME = "time"; //需要获取的起始时间，默认获取60s的长度，并返回下一次需要获取的时间
}
