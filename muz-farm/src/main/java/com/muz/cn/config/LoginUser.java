package com.muz.cn.config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginUser {
    public ThreadLocal<Long> context = new ThreadLocal<>();

    public void remove() {
        context.remove();
    }

    public Long getUserId() {
        return context.get();
    }

    public void setUserId(Long userId) {
        context.set(userId);
    }
}