package com.muz.cn.config;

import com.muz.cn.pojo.bo.LoginUser;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginUserContext {
    public ThreadLocal<LoginUser> context = new ThreadLocal<>();

    public void remove() {
        context.remove();
    }

    public Long getUserId() {
        return context.get().getUserId();
    }

    public String getIp() {
        return context.get().getIp();
    }
    public String getCity() {
        return context.get().getCity();
    }

    public void setUserId(Long userId) {
        if(context.get() == null){
            LoginUser loginUser = new LoginUser();
            loginUser.setUserId(userId);
            context.set(loginUser);
        }else{
            LoginUser loginUser = context.get();
            loginUser.setUserId(userId);
            context.set(loginUser);
        }
    }

    public void setIp(String ip) {
        if(context.get() == null){
            LoginUser loginUser = new LoginUser();
            loginUser.setIp(ip);
            context.set(loginUser);
        }else{
            LoginUser loginUser = context.get();
            loginUser.setIp(ip);
            context.set(loginUser);
        }
    }
    public void setCity(String city) {
        if(context.get() == null){
            LoginUser loginUser = new LoginUser();
            loginUser.setIp(city);
            context.set(loginUser);
        }else{
            LoginUser loginUser = context.get();
            loginUser.setCity(city);
            context.set(loginUser);
        }
    }
}