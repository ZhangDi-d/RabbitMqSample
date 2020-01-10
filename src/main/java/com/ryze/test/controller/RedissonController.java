package com.ryze.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Api(tags = "redisson")
@Slf4j
@RestController
@RequestMapping("/redisson")
public class RedissonController {

    @Resource
    private RedissonClient redissonClient;

    @ApiOperation(value = "testRedisson",notes = "testRedisson")
    @PostMapping("/testRedisson")
    public String testRedisson() {
        RLock lock = redissonClient.getLock("testRedissonLock");
        boolean locked = false;
        try {
            locked = lock.tryLock(0, 10, TimeUnit.SECONDS);
            if (locked) {
                Thread.sleep(30000);
                return "ok.......................................";
            } else {
                return "获取锁失败.......................................";
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "获取锁异常.......................................";
        } finally {
            if (!locked) {
                log.info("获取锁失败");
            }
            lock.unlock();
        }
    }

}
