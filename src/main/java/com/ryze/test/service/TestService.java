package com.ryze.test.service;

import com.ryze.test.common.ServerResponse;
import com.ryze.test.pojo.Mail;

public interface TestService {

    ServerResponse testIdempotence();

    ServerResponse accessLimit();

    ServerResponse send(Mail mail);
}
