package com.muz.cn.config.interceptor;

import com.muz.cn.config.LoginUser;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class GloBalInterceptor implements HandlerInterceptor {

    @Resource
    private LoginUser loginUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String authorization = request.getHeader("Authorization");
//        if (StringUtils.isEmpty(authorization)) {
////            response.setStatus(401);
//            return false;
//        }else {
//            loginUser.setUserId(Long.parseLong(authorization));
//            return true;
//        }
        loginUser.setUserId(100000000l);

        return  true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 在请求处理之后，渲染视图之前进行拦截，可以进行一些后置处理逻辑
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在视图渲染之后进行拦截，可以进行一些清理工作
        loginUser.remove();
    }
}
