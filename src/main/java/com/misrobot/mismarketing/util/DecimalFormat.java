package com.misrobot.mismarketing.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * bigdecimal格式化
 * Created by GYQ on 2017/8/10.
 */
public class DecimalFormat {

    /**
     * bigecimal保留2位小数返回
     *
     * @param decimal
     * @return
     */
    public static String getDecimalFormat(BigDecimal decimal) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        String result = df.format(decimal);
        if (".00".equals(result)) {
            result = "0.00";
        }
        return result;
    }

    public static BigDecimal format(BigDecimal value, int scale) {
        value = value.setScale(scale, RoundingMode.HALF_UP);
        return value;
    }

//    public static String getScaledDecimal(JsonObject jsonObject) {
//        ValueFilter filter = new ValueFilter() {
//
//            @Override
//            public Object process(Object object, String name, Object value) {
//                if (value instanceof BigDecimal || value instanceof Double || value instanceof Float) {
//                    return new BigDecimal(value.toString());
//                }
//                return value;
//            }
//        };
//
//        return JSON.toJSONString(jsonObject, filter, new SerializerFeature[0]);
//    }

}
