package com.dyh.common.lib.dw.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 作者：董永慧
 * 邮箱：648731994@qq.com
 * 时间：2019/5/7/007 10:55
 * 数据读取和设置值的工具类
 */
public class FieldValueUtil {
    private static class MyTextWatcher implements TextWatcher {

        private TextView textView;
        private Object bean;
        private String keyName;

        public MyTextWatcher(TextView textView, Object bean, String keyName) {
            this.textView = textView;
            this.bean = bean;
            this.keyName = keyName;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }


        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }


        @Override
        public void afterTextChanged(Editable goodName) {
            FieldValueUtil.setFieldValueByFieldName(keyName, bean, textView.getText().toString());
        }
    }

    /**
     * 当文本改变的时候，动态的改变数据对应的值
     *
     * @param textView 需要监听的文本组件
     * @param object   需要改变的数据Bean
     * @param keyName  需要改变的bean中的字段名
     */
    public static void addTextWatcherValue2Bean(TextView textView, Object object, String keyName) {
        textView.addTextChangedListener(new MyTextWatcher(textView, object, keyName));
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static String getFieldValueByFieldName(String fieldName, Object object) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            //设置对象的访问权限，保证对public static 的属性的访问
            field.setAccessible(true);
            return (String) field.get(object);
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }

    /**
     * 根据属性名设置属性值
     *
     * @param fieldName
     * @param object
     * @return
     */
    public static void setFieldValueByFieldName(String fieldName, Object object, String value) {
        try {
            // 获取obj类的字节文件对象
            Class c = object.getClass();
            // 获取该类的成员变量
            Field f = c.getDeclaredField(fieldName);
            // 取消语言访问检查
            f.setAccessible(true);
            // 给变量赋值
            f.set(object, value);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    /**
     * 根据方法名设置属性值
     *
     * @param methodName
     * @param object
     * @return
     */
    public static void setFieldValueByMethodName(String methodName, Object object, String value) {
        try {
            Method setReadOnly = object.getClass().getMethod(methodName, String.class);
            setReadOnly.invoke(object, value);
        } catch (Exception e) {
            System.out.print(e);
        }
    }
}
