package com.createarttechnology.jutil;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created by lixuhui on 2018/2/26.
 */
public class TextUtil {

    private static final String LABEL_START = "<[^<^>]*>";
    private static final String LABEL_END = "</[^<^>]*>";
    private static final String LT_HALF = "<";
    private static final String GT_HALF = ">";
    private static final String LT_SAFE = "&lt;";
    private static final String GT_SAFE = "&gt;";
    private static final String N = "\n";
    private static final String R = "\r";
    private static final String S = "\\s+";

    private static final List<String> SAFE_LABEL_LIST = Lists.newArrayList(
            "<p>",
            "</p>",
            "<div[^<^>]*>",
            "</div>",
            "<img[^<^>]*/?>"
    );

    private static final String EMPTY_STRING = "";
    private static final String SPACE_STRING = " ";

    /**
     * 去掉所有标签，并转义尖括号
     */
    public static String toSimpleText(String src) {
        if (StringUtil.isEmpty(src)) {
            return src;
        }
        String dst = src;
        dst = dst.replaceAll(LABEL_START, EMPTY_STRING);
        dst = dst.replaceAll(LABEL_END, EMPTY_STRING);
        dst = dst.replaceAll(LT_HALF, LT_SAFE);
        dst = dst.replaceAll(GT_HALF, GT_SAFE);
        dst = dst.replaceAll(N, EMPTY_STRING);
        dst = dst.replaceAll(R, EMPTY_STRING);
        dst = dst.replaceAll(S, EMPTY_STRING);
        return dst;
    }

    /**
     * 尖括号转义
     */
    public static String toSafeText(String src) {
        if (StringUtil.isEmpty(src)) {
            return src;
        }
        String dst = src;
        dst = dst.replaceAll(LT_HALF, LT_SAFE);
        dst = dst.replaceAll(GT_HALF, GT_SAFE);
        dst = dst.replaceAll(N, EMPTY_STRING);
        dst = dst.replaceAll(R, EMPTY_STRING);
        return dst;
    }

    /**
     * 只保留允许保留的标签
     */
    public static String removeInvalidHTML(String src) {
        if (StringUtil.isEmpty(src)) {
            return src;
        }
        StringBuilder sb = new StringBuilder();
        int ltIndex = src.indexOf(LT_HALF);
        int gtIndex = 0;
        while(ltIndex > -1) {
            String safe = src.substring(gtIndex, ltIndex);
            if (StringUtil.isNotEmpty(safe)) {
                sb.append(safe);
            }
            gtIndex = src.indexOf(GT_HALF, ltIndex);
            if (gtIndex > -1) {
                gtIndex++;
                String label = src.substring(ltIndex, gtIndex);
                for (String labelRegex : SAFE_LABEL_LIST) {
                    if (label.matches(labelRegex)) {
                        sb.append(label);
                        break;
                    }
                }
            }
            ltIndex = src.indexOf(LT_HALF, gtIndex);
        }
        sb.append(src.substring(gtIndex));
        String dst = sb.toString();
        dst = dst.replaceAll(N, EMPTY_STRING);
        dst = dst.replaceAll(R, EMPTY_STRING);
        return dst;
    }

    public static void main(String[] args) {
        String s = "<div class=\"content_data_head\">介绍</div>\n" +
                "<p>周年庆小游戏第二弹。</p>\n" +
                "<div class=\"content_data_head\">说明</div>\n" +
                "<div class=\"game_body\">\n" +
                "    <img src=\"article/36/background.png\" class=\"background\" width=\"700\" height=\"500\"/>\n" +
                "    <img src=\"article/36/penguin.png\" class=\"penguin\" width=\"130\"/>\n" +
                "    <img src=\"article/36/upchunk.png\" class=\"upchunk\" width=\"240\"/>\n" +
                "    <div class=\"hint\">0m</div>\n" +
                "</div>\n" +
                "<script src=\"article/36/game.js\"></script>\n" +
                "<link rel=\"stylesheet\" href=\"article/36/game.css\">";
        System.out.println(toSafeText(s));
    }

}
