package com.netho.yunpolice.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xsiry on 2017/4/20.
 */
public class SessionInterceptor implements HandlerInterceptor {

    private final String TOKEN = "token";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    // 拦截前处理
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
        Object sessionObj = request.getSession().getAttribute(TOKEN);
        if (sessionObj == null) {
            String requestUrl = request.getRequestURI();
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) { // ajax
                // 超时处理
                response.setHeader("sessionstatus", "timeout");
                PrintWriter out = response.getWriter();
                out.print("{timeout:true}");
                out.flush();
                out.close();
                logger.info("Login Timeout Request_Url:" + requestUrl);
            } else { // http 超时处理
                response.sendRedirect(request.getContextPath() + "/login.html");
            }
            return false;
        }
        return true;
    }

    // 拦截后处理
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
    }

    // 全部完成后处理
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
    }
}

