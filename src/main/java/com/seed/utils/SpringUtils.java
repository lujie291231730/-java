package com.seed.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SpringUtils {

    /**
     * @return
     * Spring容器下获取Request
     */
    public static HttpServletRequest GetRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();

        return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
    }

    public static HttpSession GetSession() {
        HttpServletRequest request = GetRequest();
        return request == null ? null : request.getSession();
    }

}
