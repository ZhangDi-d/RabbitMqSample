package com.ryze.test.mq.consumer;

import com.rabbitmq.client.Channel;
import com.ryze.test.mq.BaseConsumer;
import com.ryze.test.mq.MessageHelper;
import com.ryze.test.pojo.LoginLog;
import com.ryze.test.service.LoginLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class LoginLogConsumer implements BaseConsumer {

    @Resource
    private LoginLogService loginLogService;

    @Override
    public void consume(Message message, Channel channel) {
        log.info("收到消息: {}", message.toString());
        LoginLog loginLog = MessageHelper.msgToObj(message, LoginLog.class);
        loginLogService.insert(loginLog);
    }
}
