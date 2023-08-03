package com.muz.cn.config;

import com.muz.framework.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@Component
@Slf4j
@ControllerAdvice
public class GlobalResult implements ResponseBodyAdvice<Object> {

    /**
     * 判断是否要执行 beforeBodyWrite
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return ApiResult.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * 在 HttpServletResponse 响应前统一处理
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ApiResult) {
            return body;
        }
        ApiResult<Object> apiResult = new ApiResult<>();
        apiResult.setData(body);
        return apiResult;
    }
}
