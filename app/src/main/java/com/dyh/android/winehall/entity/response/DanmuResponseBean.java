package com.dyh.android.winehall.entity.response;

import com.dyh.android.winehall.entity.BaseHttpResponseBean;

import java.util.List;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/17 0017
 * description:
 */
public class DanmuResponseBean extends BaseHttpResponseBean {
    public List<DanmuItemResponseBean> list;
    public String next_time;
}
