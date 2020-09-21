package com.dyh.android.winehall.entity.common;

import java.util.List;

public class CourseDetailChildBean {
    private String node;
    private String tag;
    private String text;
    private List<CourseDetailChildBean> child;
    public Attr attr;

    public static class Attr{
        public String src;
    }

    public List<CourseDetailChildBean> getChild() {
        return child;
    }

    public void setChild(List<CourseDetailChildBean> child) {
        this.child = child;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}