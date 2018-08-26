package com.lambert.front.interceptor;

import com.lambert.common.utils.CookieUtils;
import com.lambert.front.bean.UserThreadLocal;
import com.lambert.manager.pojo.User;
import com.lambert.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInterceptor implements HandlerInterceptor{

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String ticket = CookieUtils.getCookieValue(httpServletRequest, "YOUYIJIA_TICKET", true);
        if (ticket == null) {
            UserThreadLocal.set(null);
            return true;
        }
        User user = userService.queryByTicket(ticket);
        if (user == null) {
            UserThreadLocal.set(null);
            return true;
        }
        UserThreadLocal.set(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
