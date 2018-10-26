package com.comodin.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EmptyUtil {
    /**
     * 判断对象为空
     *
     * @param obj 对象名
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List)) {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String)) {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    public static  boolean isEmpty2(Object obj){
        if (obj == null)
            return true;
        else if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;
        else if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();
        else if (obj instanceof Map)
            return ((Map) obj).isEmpty();
        else if (obj.getClass().isArray())
            return Array.getLength(obj) == 0;
        return false;
    }

    /**
     * 判断对象不为空
     *
     * @param obj 对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }
}
