package com.createarttechnology.jutil;


import com.createarttechnology.constant.StringConstant;

import javax.servlet.http.HttpServletRequest;

/**
 * 检测爬虫和脚本
 * Created by lixuhui on 2018/5/9.
 */
public final class AntiBotUtil {

    private AntiBotUtil() {}

    /**
     * 根据request信息判断是否来自爬虫或脚本
     */
    public static boolean isBot(HttpServletRequest request) {
        Boolean isBot = (Boolean) request.getAttribute(StringConstant.REQUEST_ATTRIBUTE_IS_BOT);
        if (isBot != null) {
            return isBot;
        }

        String userAgent = request.getHeader("User-Agent");
        String uri = request.getRequestURI();

        // 空ua
        boolean isAjaxRequest = uri.startsWith("/ajax/");
        if (StringUtil.isEmpty(userAgent) && !isAjaxRequest) {
            request.setAttribute(StringConstant.REQUEST_ATTRIBUTE_IS_BOT, true);
            return true;
        }

        // 包含特殊标识
        String uaLowerCase = userAgent.toLowerCase();
        if (uaLowerCase.length() < 8 || uaLowerCase.contains("bot") || uaLowerCase.contains("spider")) {
            request.setAttribute(StringConstant.REQUEST_ATTRIBUTE_IS_BOT, true);
            return true;
        }

        return false;
    }

}
