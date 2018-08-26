package com.lambert.front.interceptor;

import com.lambert.common.utils.CookieUtils;
import com.lambert.manager.pojo.User;
import com.lambert.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = CookieUtils.getCookieValue(httpServletRequest, "YOUYIJIA_TICKET", true);
        if (StringUtils.isEmpty(ticket)) {
            httpServletResponse.sendRedirect("/user/login.html");
            return false;
        }
        User user = userService.queryByTicket(ticket);
        if (user != null) {
            return true;
        }
        httpServletResponse.sendRedirect("/user/login.html");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
