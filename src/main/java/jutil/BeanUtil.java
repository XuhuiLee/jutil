package jutil;

import java.lang.reflect.Method;
import java.util.Date;

/**
 *
 * @author lixuhui
 * @date 2018/2/6
 */
public class BeanUtil {

    public static <T> String genToString(T o) {
        if (o == null) {
            return null;
        }
        Class clazz = o.getClass();
        Method[] methods = clazz.getMethods();
        StringBuilder sb = new StringBuilder();
        if (methods != null) {
            for (Method method : methods) {
                String methodName = method.getName();
                if ("getClass".equals(methodName)) {
                    continue;
                }
                boolean isGetter = false;
                if (methodName.startsWith("get")) {
                    methodName = methodName.substring(3);
                    isGetter = true;
                } else if (methodName.startsWith("is")) {
                    methodName = methodName.substring(2);
                    isGetter = true;
                }
                if (isGetter) {
                    if (sb.length() > 0) {
                        sb.append(", ");
                    }
                    char c = Character.toLowerCase(methodName.charAt(0));
                    sb.append(c).append(methodName.substring(1)).append("=");
                    try {
                        Object value = method.invoke(o);
                        if (value instanceof String || value instanceof Date) {
                            sb.append("'").append(value).append("'");
                        } else {
                            sb.append(value);
                        }
                    } catch (Exception e) {
                        return "BeanUtil.genToString error, e:" + e;
                    }
                }
            }
        }
        return sb.insert(0, clazz.getSimpleName() + "{").append("}").toString();
    }

}
