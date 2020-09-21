package com.dyh.android.winehall.entity;

import com.dyh.common.lib.dw.util.MathUtil;

import java.io.Serializable;

/**
 * 作者：Allan
 * 时间：2019/9/17/017
 * 邮箱：648731994@qq.com
 * 描述：网络请求分页基类
 */
public class BaseHttpPageResponseBean implements Serializable {

    private String totalPage;
    private String totalCount;

    public int getTotalPage() {
        return MathUtil.getIntegerNumber(totalPage);
    }

    public void setTotalPage(String totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return MathUtil.getIntegerNumber(totalCount);
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }
}
