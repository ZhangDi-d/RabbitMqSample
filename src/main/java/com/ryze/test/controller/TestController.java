package com.ryze.test.controller;

import com.ryze.test.annotation.AccessLimit;
import com.ryze.test.annotation.ApiIdempotent;
import com.ryze.test.common.ServerResponse;
import com.ryze.test.pojo.Mail;
import com.ryze.test.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Api(tags = "测试发送")
@RestController
@Slf4j
@Validated
@RequestMapping("/test")
public class TestController {

    @Resource
    private TestService testService;


    @ApiIdempotent
    @ApiOperation(value = "testIdempotence",notes = "testIdempotence",httpMethod = "POST")
    @PostMapping("/testIdempotence")
    public ServerResponse testIdempotence() {
        return testService.testIdempotence();
    }

    @AccessLimit(maxCount = 5, seconds = 5)
    @PostMapping("/accessLimit")
    @ApiOperation(value = "accessLimit",notes = "accessLimit",httpMethod = "POST")
    public ServerResponse accessLimit() {
        return testService.accessLimit();
    }

    @PostMapping("/send")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query",name= "to" ,value = "接收方",dataType = "string"),
            @ApiImplicitParam(paramType = "query",name= "title" ,value = "标题",dataType = "string") ,
            @ApiImplicitParam(paramType = "query",name= "content" ,value = "内容",dataType = "string")
    })
    public ServerResponse sendMail(@NotNull Mail mail, Errors errors) {
        if (errors.hasErrors()) {
            String msg = Objects.requireNonNull(errors.getFieldError()).getDefaultMessage();
            return ServerResponse.error(msg);
        }
        return testService.send(mail);
    }
}
