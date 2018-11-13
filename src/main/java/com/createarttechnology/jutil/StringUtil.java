package com.createarttechnology.jutil;

/**
 * Created by lixuhui on 2018/2/6.
 */
public class StringUtil {

    public static boolean isEmpty(String s) {
        return s == null || "".equals(s);
    }

    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    public static int convertInt(String s, int defaultValue) {
        if (isNotEmpty(s)) {
            try {
                return Integer.valueOf(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static long convertLong(String s, long defaultValue) {
        if (isNotEmpty(s)) {
            try {
                return Long.valueOf(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static float convertFloat(String s, float defaultValue) {
        if (isNotEmpty(s)) {
            try {
                return Float.valueOf(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static double convertDouble(String s, double defaultValue) {
        if (isNotEmpty(s)) {
            try {
                return Double.valueOf(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static boolean convertBoolean(String s, boolean defaultValue) {
        if (isNotEmpty(s)) {
            try {
                return Boolean.valueOf(s);
            } catch (Exception e) {
            }
        }
        return defaultValue;
    }

    public static int getLength(String s) {
        return s == null ? -1 : s.length();
    }

    public static String getTrim(String s) {
        return s == null ? null : s.trim();
    }

    public static boolean isSafetyCharOnly(String s) {
        if (isEmpty(s)) {
            return true;
        }
        s = s.replaceAll("[0-9a-zA-Z_]", "");
        return isEmpty(s);
    }

    public static boolean isHexCharOnly(String s) {
        if (isEmpty(s)) {
            return true;
        }
        s = s.toLowerCase();
        s = s.replaceAll("[0-9a-f]", "");
        return isEmpty(s);
    }

    public static String substring(String s, String from, String to) {
        if (isEmpty(s)) {
            return null;
        }
        int fromIdx = 0;
        if (isNotEmpty(from)) {
            fromIdx = s.indexOf(from);
            if (fromIdx < 0) {
                return null;
            }
            fromIdx += from.length();
        }
        int toIdx = s.length();
        if (isNotEmpty(to)) {
            toIdx = s.indexOf(to, fromIdx + 1);
            if (toIdx < 0) {
                return null;
            }
        }
        return s.substring(fromIdx, toIdx);
    }

}
