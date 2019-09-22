package cn.seppel.guiliproducttools.utils;


import cn.seppel.guiliproducttools.enums.CodeEnum;

/**
 * @description: 枚举工具类
 * @author: Huangsp
 * @create: 2019-03-16
 **/
public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass) {
        for (T each : enumClass.getEnumConstants()) {
            if (each.getCode() == code) {
                return each;
            }
        }
        return null;
    }
}
