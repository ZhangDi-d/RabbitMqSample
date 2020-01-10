package com.ryze.test.controller;

import com.ryze.test.common.ServerResponse;
import com.ryze.test.pojo.User;
import com.ryze.test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "用户")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation(value = "getall",notes = "getall")
    @ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
    @GetMapping("/users")
    public String getAll() {
        List<User> users = userService.getAll();
        return users.toString();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "getOne",notes = "getOne")
    @ApiImplicitParam(dataType = "int",name = "id",value = "id",required = true)
    public String getOne(@PathVariable Integer id) {
        User user = userService.getOne(id);
        if (null != user) {
            return user.toString();
        } else {
            return "not exists";
        }
    }

    @PostMapping
    @ApiOperation(value = "getOne",notes = "getOne")
    @ApiImplicitParam(dataType = "int",name = "id",value = "id",required = true)
    public String add(User user) {
        userService.add(user);
        return "nice";
    }

    @PutMapping
    public String update(User user) {
        userService.update(user);
        return "nice";
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "delete",notes = "delete")
    @ApiImplicitParam(dataType = "int",name = "id",value = "id",required = true)
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "nice";
    }

    @PostMapping("/login")
    @ApiOperation(value = "login",notes = "login")
    public ServerResponse login(String username, String password) {
        return userService.login(username, password);
    }

}
