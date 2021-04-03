package com.gxun.config;

import com.alibaba.fastjson.JSON;
import com.gxun.util.UserTokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;

public class AdminHandlerInterceter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HashMap<String, Object> msg=new HashMap<String, Object>();
        String token = request.getHeader("token");
        System.out.println("管理员：token:"+token);
        if(UserTokenUtil.isUserSession(token)) {
            if(UserTokenUtil.administratorsUser(token))
            {
                response.reset();
                return true;
            }else {

                response.setContentType("application/json;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                PrintWriter pw=response.getWriter();
                msg.put("msg", "没有权限");
                msg.put("jctionFlag", false);
                pw.write(JSON.toJSONString(msg));
                pw.flush();
                return false;
            }
        }else {

            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw=response.getWriter();
            msg.put("msg", "用户未登录");
            msg.put("loginFlag", false);
            pw.write(JSON.toJSONString(msg));
            pw.flush();
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
