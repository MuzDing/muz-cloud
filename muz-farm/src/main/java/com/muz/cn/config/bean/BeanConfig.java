package com.muz.cn.config.bean;

import com.muz.framework.utils.IdUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

@Configuration
public class BeanConfig {
    @Bean
    public IdUtils getIdUtils(){
        return new IdUtils();
    }
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // 连接工厂配置，可根据需要选择合适的连接工厂
        // 如JedisConnectionFactory或LettuceConnectionFactory
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        // 设置序列化器，可以根据需要选择合适的序列化器
        template.setDefaultSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        return template;
    }
}
