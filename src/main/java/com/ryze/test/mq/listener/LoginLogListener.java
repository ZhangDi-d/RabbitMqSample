package com.ryze.test.mq.listener;

import com.rabbitmq.client.Channel;
import com.ryze.test.config.RabbitConfig;
import com.ryze.test.mq.BaseConsumer;
import com.ryze.test.mq.BaseConsumerProxy;
import com.ryze.test.mq.consumer.LoginLogConsumer;
import com.ryze.test.service.MsgLogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
public class LoginLogListener {

    @Resource
    private LoginLogConsumer loginLogConsumer;

    @Resource
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitConfig.LOGIN_LOG_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws IOException {
        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(loginLogConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (null != proxy) {
            proxy.consume(message, channel);
        }
    }

}
