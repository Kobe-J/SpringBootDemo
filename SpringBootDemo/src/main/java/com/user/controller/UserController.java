package com.user.controller;

import com.user.model.User;
import com.user.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserServiceImpl service;

    @ResponseBody
    @ApiOperation(value = "登陆接口",httpMethod = "POST")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Boolean login(HttpServletResponse response, @RequestBody Map user){
        response.addCookie((Cookie) service.setCookie());
        return service.userLogin(user.get("userName").toString(),user.get("password").toString());
    }

    @ResponseBody
    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public Object addUser(HttpServletRequest request, @RequestBody User user) {
        if (service.verifyCookies(request) != null) {
            return service.userInsert(user);
        }else {
            return "当前登陆状态已过期，请重新登陆";
        }
    }

    @ResponseBody
    @ApiOperation(value = "获取用户(列表)信息接口",httpMethod = "POST")
    @RequestMapping(value = "/getUser",method = RequestMethod.POST)
    public Object getUser(HttpServletRequest request, @RequestBody User user){
        if (service.verifyCookies(request) != null) {
            return service.getUserInfo(user);
        }else {
            return "当前登陆状态已过期，请重新登陆";
        }
    }

    @ResponseBody
    @ApiOperation(value = "更新/删除用户接口",httpMethod = "POST")
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    public Object updateUser(HttpServletRequest request,@RequestBody User user){
        if (service.verifyCookies(request) != null){
            return service.updateUserInfo(user);
        }
        return "当前登陆状态已过期，请重新登陆";
    }
}
