package com.ryze.test.controller;

import com.ryze.test.common.ServerResponse;
import com.ryze.test.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "Token")
@RestController
@RequestMapping("/token")
public class TokenController {

    @Resource
    private TokenService tokenService;

    @GetMapping
    @ApiOperation(value = "token",notes = "token",httpMethod = "POST")
    @PostMapping("/token")
    public ServerResponse token() {
        return tokenService.createToken();
    }

}
