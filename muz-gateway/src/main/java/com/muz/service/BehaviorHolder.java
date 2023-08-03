package com.muz.service;

import com.muz.config.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

@Slf4j
@Component
public class BehaviorHolder {

    @Lazy
    @Autowired
    private Behavior behavior;

    @Async
    public Future<ApiResult> systemClientTest(String uid) {
        log.info("开始使用SystemClient...");
        ApiResult s = behavior.writeBehavior(uid);
        return new AsyncResult<>(s);
    }

}
