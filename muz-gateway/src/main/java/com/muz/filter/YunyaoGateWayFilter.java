package com.muz.filter;

import com.muz.config.ApiResult;
import com.muz.service.BehaviorHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.concurrent.Future;

@Component
@Slf4j
public class YunyaoGateWayFilter implements GlobalFilter, Ordered {

    @Resource
    private BehaviorHolder behaviorHolder;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("1");

        Future<ApiResult> stringFuture = behaviorHolder.systemClientTest("123");
//            behavior.writeBehavior("123");
        try {
            log.info("stringFuture.rs :{}", stringFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String behaviorUrl = "http://yunyao-behavior-service/behavior/handle";
//
//        String authUrl = "http://yunyao-authentication-service/handle/auth";
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("uid","1");


//        ApiResult apiResult = restTemplate.postForObject(authUrl, map, ApiResult.class);

//        String method = request.getMethodValue();
//        String contentType = request.getHeaders().getFirst("Content-Type");
//        if ("POST".equals(method) && contentType.startsWith("application/x-www-form-urlencoded")){
//            return DataBufferUtils.join(exchange.getRequest().getBody())
//                    .flatMap(dataBuffer -> {
//                        byte[] bytes = new byte[dataBuffer.readableByteCount()];
//                        dataBuffer.read(bytes);
//                        try {
//                            String bodyString = new String(bytes, "utf-8");
//                            log.info(bodyString);
//                            exchange.getAttributes().put("POST_BODY",bodyString);
//                        } catch (UnsupportedEncodingException e) {
//                            e.printStackTrace();
//                        }
//                        DataBufferUtils.release(dataBuffer);
//                        Flux<DataBuffer> cachedFlux = Flux.defer(() -> {
//                            DataBuffer buffer = exchange.getResponse().bufferFactory()
//                                    .wrap(bytes);
//                            return Mono.just(buffer);
//                        });
//
//                        ServerHttpRequest mutatedRequest = new ServerHttpRequestDecorator(
//                                exchange.getRequest()) {
//                            @Override
//                            public Flux<DataBuffer> getBody() {
//                                return cachedFlux;
//                            }
//                        };
//                        return chain.filter(exchange.mutate().request(mutatedRequest)
//                                .build());
//                    });
//        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -200;
    }
}
