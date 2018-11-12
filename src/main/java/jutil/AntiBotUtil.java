package jutil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by lixuhui on 2018/5/9.
 */
public class AntiBotUtil {

    /**
     * 根据request信息判断是否来自爬虫或脚本
     */
    public static boolean isBot(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent");
        String uri = request.getRequestURI();

        // 空ua
        boolean isAjaxRequest = uri.startsWith("/ajax/");
        if (StringUtil.isEmpty(userAgent) && !isAjaxRequest) {
            return true;
        }

        // 包含特殊标识
        if (userAgent.length() < 8 || userAgent.contains("bot")) {
            return true;
        }

        return false;
    }






}
