package com.dyh.common.lib.weigit.pop_filter.bean;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 筛选条件数据
 */
public class FiltrateBean {

    public static final int TYPE_LIST = 0;//选择列表类型
    public static final int TYPE_INPUT = 1;//输入框类型

    private String key;
    private String titleName;//名称
    private int type = TYPE_LIST;//类型
    private List<Children> children;
    private String inputText;//输入的内容

    /**
     * @return 是否选中的是全部选项
     */
    public boolean isAllSelected() {
        if (type == TYPE_LIST && null != children) {
            //如果是列表，选中了全部或者一个没选中，都认为是全部
            boolean isHaveSelectedItem = false;
            for (Children child : children) {
                if (!child.isSelected) {
                    continue;
                }
                isHaveSelectedItem = true;
                if ("0".equals(child.getValue())) {
                    return true;
                }
            }
            if (!isHaveSelectedItem) {
                return true;
            }
        }

        if (type == TYPE_INPUT) {
            return TextUtils.isEmpty(inputText);
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FiltrateBean that = (FiltrateBean) o;

        if (type != that.type) return false;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + type;
        return result;
    }

    /**
     * @return 获取单个值，输入框类型或者单选的时候
     */
    public String getSelectedSingleValue() {
        if (TYPE_INPUT == type) {
            return inputText;
        }
        if (TYPE_LIST == type) {
            for (Children child : getChildren()) {
                if (child.isSelected()) {
                    return child.getValue();
                }
            }
        }
        return null;
    }

    /**
     * @return 获取单个值，输入框类型或者单选的时候
     */
    public String getSelectedSingleShowText() {
        if (TYPE_INPUT == type) {
            return inputText;
        }
        if (TYPE_LIST == type) {
            for (Children child : getChildren()) {
                if (child.isSelected()) {
                    return child.getShowText();
                }
            }
        }
        return null;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public List<Children> getChildren() {
        return children == null ? new ArrayList<>() : children;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    public static class Children {
        private String showText;
        private String value;
        private boolean isSelected = false;

        public Children(String showText, String value, boolean isSelected) {
            this.showText = showText;
            this.value = value;
            this.isSelected = isSelected;
        }

        public Children(String showText, String value) {
            this.showText = showText;
            this.value = value;
        }

        public Children() {
        }

        public String getShowText() {
            return showText;
        }

        public void setShowText(String showText) {
            this.showText = showText;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}
