package com.user.service;

import com.user.mapper.userMapper;
import com.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static sun.security.ssl.SSLLogger.info;

@Controller
public class UserServiceImpl {

    @Autowired
    private userMapper mapper;

    //设置COOKIE
    public Object setCookie(){
        Cookie cookie = new Cookie("login","true");
        return cookie;
    }


    //检查请求是否携带cookie
    public Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(Objects.isNull(cookies)){
            //info("cookies为空");
            return false;
        }
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")){
                info("cookies验证通过");
                return true;
            }
        }
        return false;
    }

    //登录接口
    public boolean userLogin(String userName, String password){
        if (mapper.userLogin(userName,password)==1){
            System.out.println(true);
            return true;
        }
        System.out.println(false);
        return false;
    }

    //注册接口
    public boolean userInsert(User user){
        if (mapper.userInsert(user)>0){
            System.out.println(mapper.addUser(user));
            return true;
        }
        return false;
    }

    //查询用户信息接口
    public Object getUserInfo(User user){
        List<User> userList = new ArrayList(mapper.getUserInfo(user));
        if (userList.size()>0 && userList.size() == 1){
            System.out.println(userList.get(0));
            return userList.get(0);
        }else if (userList.size()>1){
            return userList;
        }
        return "未查询到相关信息";
    }

    public Object updateUserInfo(User user){
        int i = mapper.updateUserInfo(user);
        if (i>0){
            return "修改成功!";
        }
        return false;
    }
}
