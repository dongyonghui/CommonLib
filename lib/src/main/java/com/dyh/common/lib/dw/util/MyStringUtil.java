package com.dyh.common.lib.dw.util;

import java.util.Objects;

/**
 * author: Allan
 * email: 648731994@qq.com
 * created on: 2020/9/7 0007 17:37
 * description:
 */
public class MyStringUtil {
    public static String join(CharSequence delimiter,
                              Iterable<? extends CharSequence> elements) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(elements);
        StringJoiner joiner = new StringJoiner(delimiter);
        for (CharSequence cs : elements) {
            joiner.add(cs);
        }
        return joiner.toString();
    }
}
