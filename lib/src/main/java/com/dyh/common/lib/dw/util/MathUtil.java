package com.dyh.common.lib.dw.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * User: DongYonghui(404638723@qq.com)
 * Date: 2015-11-27
 * Time: 15:29
 */
public class MathUtil {
    /**
     * 默认计算方法
     */
    public static final int ROUNDING_MODE = BigDecimal.ROUND_UP;
    /**
     * 默认保留两位小数
     */
    public static final int SCALE = 2;

    /**
     * 对double数据进行取精度.
     *
     * @param value double数据.
     * @return 精度计算后的数据.
     */
    public static double round(double value) {

        return round(value, SCALE);
    }

    /**
     * 对double数据进行取精度.
     *
     * @param value double数据.
     * @param scale 精度位数(保留的小数位数).
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale) {

        return round(value, scale, ROUNDING_MODE);
    }

    public static long getLongNumber(String number) {
        if (!TextUtils.isEmpty(number)) {
            try {
                return Long.parseLong(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static int getIntegerNumber(String number) {
        if (!TextUtils.isEmpty(number)) {
            try {
                return Integer.parseInt(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }


    public static String getFc820Price(double price) {
        DecimalFormat df = new DecimalFormat("000000000000");//要多少位就些多少个0
        return df.format(((int) MathUtil.mul(price, 100)));
    }

    public static double getDoubleNumber(String number) {
        if (!TextUtils.isEmpty(number)) {
            try {
                return Double.parseDouble(number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public final static DecimalFormat TWO_PRICE_COUNT = new DecimalFormat("0.00");

    public static String getMoneyNumber(String number) {
        return TWO_PRICE_COUNT.format(getDoubleNumber(number));
    }

    public static String getMoneyNumber(double number) {
        return TWO_PRICE_COUNT.format(number);
    }

    /**
     * 对double数据进行取精度.
     *
     * @param value        double数据.
     * @param scale        精度位数(保留的小数位数).
     * @param roundingMode 精度取值方式.
     * @return 精度计算后的数据.
     */
    public static double round(double value, int scale,
                               int roundingMode) {
        BigDecimal bd = new BigDecimal(String.valueOf(value));
        bd = bd.setScale(scale, roundingMode);
        return bd.doubleValue();
    }


    /**
     * double 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sum(String d1, String d2) {
        BigDecimal bd1 = new BigDecimal(d1);
        BigDecimal bd2 = new BigDecimal(d2);
        return bd1.add(bd2).setScale(SCALE, ROUNDING_MODE).doubleValue();
    }

    /**
     * double 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sum(double d1, double d2) {
        BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
        BigDecimal bd2 = new BigDecimal(String.valueOf(d2));
        return bd1.add(bd2).setScale(SCALE, ROUNDING_MODE).doubleValue();
    }

    /**
     * double 相加，无模式，无保留几位的限制
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sumNoScaleAndMode(double d1, double... d2) {
        BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
        for (double item : d2) {
            BigDecimal bd2 = new BigDecimal(String.valueOf(item));
            bd1 = bd1.add(bd2);
        }
        return bd1.doubleValue();
    }

    public static double sum(double... number) {
        BigDecimal bigDecimal = new BigDecimal("0");
        bigDecimal.setScale(SCALE, ROUNDING_MODE);
        if (null != number) {
            for (double d : number) {
                bigDecimal = bigDecimal.add(new BigDecimal(String.valueOf(d)));
            }
        }
        return bigDecimal.doubleValue();
    }


    /**
     * double 相减
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double sub(double d1, double d2) {
        return sub(d1, d2, SCALE);
    }

    public static double sub(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
        BigDecimal bd2 = new BigDecimal(String.valueOf(d2));
        return bd1.subtract(bd2).setScale(scale, ROUNDING_MODE).doubleValue();
    }

    /**
     * double 乘法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double mul(double d1, double d2) {
        return mul(d1, d2, SCALE);
    }

    public static double mul(double d1, double d2, int scale) {
        BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
        BigDecimal bd2 = new BigDecimal(String.valueOf(d2));
        return bd1.multiply(bd2).setScale(scale, ROUNDING_MODE).doubleValue();
    }

    public static double mul(String d1, String d2) {
        try {
            BigDecimal bd1 = new BigDecimal(d1);
            BigDecimal bd2 = new BigDecimal(d2);
            return bd1.multiply(bd2).setScale(SCALE, ROUNDING_MODE).doubleValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * double 除法
     *
     * @param d1
     * @param d2
     * @param scale 四舍五入 小数点位数
     * @return
     */
    public static double div(String d1, String d2, int scale, int round) {
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理

        BigDecimal bd1 = new BigDecimal(String.valueOf(d1));
        BigDecimal bd2 = new BigDecimal(String.valueOf(d2));
        return bd1.divide
                (bd2, scale, round).setScale(scale, round).doubleValue();
    }

    /**
     * double 除法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(double d1, double d2) {
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理

        return div(String.valueOf(d1), String.valueOf(d2), SCALE, ROUNDING_MODE);
    }

    /**
     * double 除法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(String d1, String d2) {
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理

        return div(d1, d2, SCALE, ROUNDING_MODE);
    }

    /**
     * double 除法
     *
     * @param d1
     * @param d2
     * @return
     */
    public static double div(String d1, String d2, int round) {
        //  当然在此之前，你要判断分母是否为0，
        //  为0你可以根据实际需求做相应的处理

        return div(d1, d2, SCALE, round);
    }

    /**
     * 截取掉小数点后面无用的0
     *
     * @param s
     * @return
     */
    public static String cutNumberEnd0(String s) {
        if (null != s && s.indexOf(".") > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }

    /**
     * 去掉后面无用的零
     * 如小数点后面全是零则去掉小数点
     *
     * @param number
     * @return
     */
    public static String cutNumberEnd0(double number) {
        String s = String.valueOf(number);
        if (null != s && s.indexOf(".") > 0) {
            //正则表达
            s = s.replaceAll("0+?$", "");//去掉后面无用的零
            s = s.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return s;
    }
}
