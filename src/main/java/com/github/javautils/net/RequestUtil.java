package com.github.javautils.net;

import com.github.javautils.string.StringUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tomoya on 16/9/27.
 */
public class RequestUtil {

    /**
     * 获取重定向之前的请求完整路径
     * @param request
     * @return
     */
    public static String getBeforeUrl(HttpServletRequest request) {
        String url = request.getRequestURL().toString();
        String query = request.getQueryString();
        if(StringUtil.notBlank(query)) {
            url += "?" + query;
        }
        return url;
    }
}
