package com.createarttechnology.jutil;

import org.springframework.util.DigestUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lixuhui on 2018/2/9.
 */
public class CookieUtil {

    private static final int DEFAULT_MAX_AGE = 30 * 60;
    private static final String SESSION_COOKIE_KEY = "session_cookie";

    private static final String LOGIN_USER_COOKIE = "cat_lu";
    private static final int RANDOM_SUFFIX_LENGTH = 4;
    private static final String LOGIN_COOKIE_SPLITTER = "_";

    public static String getCookie(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(DEFAULT_MAX_AGE);
        response.addCookie(cookie);
    }

    public static void delCookie(HttpServletResponse response, String name) {
        addCookie(response, name, "", -1);
    }

    public static void setSessionCookie(HttpServletResponse response, long uid) {
        String value = uid + "_" + System.currentTimeMillis() + "_" + java.util.UUID.randomUUID();
        value = DigestUtils.md5DigestAsHex(value.getBytes());
        addCookie(response, SESSION_COOKIE_KEY, value);
    }

    public static void delSessionCookie(HttpServletResponse response) {
        delCookie(response, SESSION_COOKIE_KEY);
    }

    public static long getUidFromCookie(HttpServletRequest request) {
        String lu = getCookie(request, LOGIN_USER_COOKIE);
        if (StringUtil.isNotEmpty(lu) && lu.contains(LOGIN_COOKIE_SPLITTER)) {
            String[] kvPair = lu.split(LOGIN_COOKIE_SPLITTER);
            if (kvPair.length == 2) {
                long uid = StringUtil.convertLong(kvPair[0], 0);
                String randomStr = kvPair[1];
                randomStr = randomStr.substring(0, 16 - RANDOM_SUFFIX_LENGTH);
                uid = uid - Math.abs(randomStr.hashCode());
                return uid;
            }
        }
        return -1;
    }

    public static void setUidToCookie(HttpServletResponse response, long uid) {
        String randomStr = RandomUtil.randomString(16 - RANDOM_SUFFIX_LENGTH);
        String suffix = RandomUtil.randomString(RANDOM_SUFFIX_LENGTH);
        long hashCode = Math.abs(randomStr.hashCode());
        String value = String.valueOf(uid + hashCode) + "_" + randomStr + suffix;
        CookieUtil.addCookie(response, LOGIN_USER_COOKIE, value);
    }

    public static void delUidToCookie(HttpServletResponse response) {
        delCookie(response, LOGIN_USER_COOKIE);
    }

}
