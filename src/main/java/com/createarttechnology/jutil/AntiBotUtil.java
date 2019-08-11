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
        String referer = request.getHeader("Referer");

        // 空ua
        boolean isAjaxRequest = uri.startsWith("/ajax/");
        if (StringUtil.isEmpty(userAgent) && !isAjaxRequest) {
            return setBotFlag(request);
        }

        // 包含特殊标识
        String uaLowerCase = userAgent.toLowerCase();
        if (uaLowerCase.length() < 8 || uaLowerCase.contains("bot") || uaLowerCase.contains("spider")) {
            return setBotFlag(request);
        }

        // 无referer，还未处理特殊情况
        if (uri.length() > 3 && StringUtil.isEmpty(referer)) {
            return setBotFlag(request);
        }

        // 请求频率过高的

        // 后续使用漏桶算法升级一下标记方法

        return false;
    }

    private static boolean setBotFlag(HttpServletRequest request) {
        request.setAttribute(StringConstant.REQUEST_ATTRIBUTE_IS_BOT, true);
        return true;
    }

}
