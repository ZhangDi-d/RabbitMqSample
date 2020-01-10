package com.ryze.test.mq.consumer;

import com.rabbitmq.client.Channel;
import com.ryze.test.exception.ServiceException;
import com.ryze.test.mq.BaseConsumer;
import com.ryze.test.mq.MessageHelper;
import com.ryze.test.pojo.Mail;
import com.ryze.test.util.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class MailConsumer implements BaseConsumer {

    @Resource
    private MailUtil mailUtil;

    @Override
    public void consume(Message message, Channel channel) {
        Mail mail = MessageHelper.msgToObj(message, Mail.class);
        log.info("收到消息: {}", mail.toString());

        boolean success = mailUtil.send(mail);
        if (!success) {
            throw new ServiceException("send mail error");
        }
    }

}
