package com.shadowxz.Interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by xz on 2017/6/11.
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestMethod = request.getMethod();
        String requestURI = request.getRequestURI();
        if(requestMethod.equals("GET") || (requestMethod.equals("POST") &&
                        (requestURI.equals("/api/user/session") || requestURI.equals("/api/user/user"))))
            return true;
        else {
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            if (userId == null){
                response.sendRedirect("/unlogin");
                return false;
            }
            return true;
        }
    }
}
