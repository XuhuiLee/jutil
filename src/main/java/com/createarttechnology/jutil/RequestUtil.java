package com.createarttechnology.jutil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lixuhui on 2018/2/6.
 */
public class RequestUtil {

    private static final String LOGIN_USER_ATTR = "login_user";

    public static String getParameter(HttpServletRequest request, String parameterName) {
        if ("POST".equals(request.getMethod())) {
            return request.getParameter(parameterName);
        }
        String str = request.getQueryString();
        if (StringUtil.isNotEmpty(str) && StringUtil.isNotEmpty(parameterName)) {
            int start = str.indexOf(parameterName + "=");
            String subStr = str.substring(start + parameterName.length() + 1);
            int end = subStr.indexOf("&");
            if (end > 0) {
                return subStr.substring(0, end);
            } else {
                return subStr;
            }
        }
        return null;
    }

    public static String getStringParameter(HttpServletRequest request, String parameterName, String defaultValue) {
        String value = getParameter(request, parameterName);
        if (StringUtil.isNotEmpty(value)) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public static int getIntParameter(HttpServletRequest request, String parameterName, int defaultValue) {
        String value = getParameter(request, parameterName);
        return StringUtil.convertInt(value, defaultValue);
    }

    public static long getLongParameter(HttpServletRequest request, String parameterName, long defaultValue) {
        String value = getParameter(request, parameterName);
        return StringUtil.convertLong(value, defaultValue);
    }

    public static float getFloatParameter(HttpServletRequest request, String parameterName, float defaultValue) {
        String value = getParameter(request, parameterName);
        return StringUtil.convertFloat(value, defaultValue);
    }

    public static double getDoubleParameter(HttpServletRequest request, String parameterName, double defaultValue) {
        String value = getParameter(request, parameterName);
        return StringUtil.convertDouble(value, defaultValue);
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String parameterName, boolean defaultValue) {
        String value = getParameter(request, parameterName);
        return StringUtil.convertBoolean(value, defaultValue);
    }

}
