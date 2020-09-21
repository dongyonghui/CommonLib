package com.dyh.common.lib.dw.util;

import com.dyh.common.lib.weigit.tablayout.SimpleTabEntity;
import com.dyh.common.lib.weigit.tablayout.listener.CustomTabEntity;

import java.util.ArrayList;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/4/26 0026 11:02
 * description:
 */
public class CommonTabLayoutUtil {
    /**
     * 获取纯文本tab实体
     *
     * @param messageArray
     * @return
     */
    public static ArrayList<CustomTabEntity> getCustomTabEntityList(String[] messageArray) {
        if (null == messageArray) {
            return null;
        }
        ArrayList<CustomTabEntity> tabEntities = new ArrayList<CustomTabEntity>();
        for (String message : messageArray) {
            tabEntities.add(new SimpleTabEntity(message));
        }
        return tabEntities;
    }
}
