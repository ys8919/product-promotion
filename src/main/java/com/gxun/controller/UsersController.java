package com.gxun.controller;

import com.gxun.entity.Users;
import com.gxun.services.UserServices;
import com.gxun.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author 杨
 */
@Controller
@ResponseBody
public class UsersController implements Serializable {
    @Autowired
    private UserServices userServices;
    @RequestMapping("/user/login")
    public String login(@RequestBody Users u){
        String login = userServices.login(u);
        System.out.println(login);
        return login;
    }
    @RequestMapping("/users/register")
    public String register(@RequestBody Users u){
        return userServices.register(u);
    }
    @RequestMapping("/users/forgetPassword")
    public String forgetPassword(@RequestBody Users u){ return userServices.forgetPassword(u); }
    @RequestMapping("/users/updatePassword")
    public String updatePassword(@RequestBody HashMap<String,Object> u){ return userServices.updatePassword(u); }
    @RequestMapping("/users/queryUserList")
    public  String queryUserList(@RequestBody HashMap<String,Object> u){
        return userServices.queryUsersList(u);
    }
    @RequestMapping("/users/selectOneUser")
    public String selectOneUser(@RequestBody Users users){ return userServices.selectOneUser(users); }
    @RequestMapping("/users/addUsers")
    public String addUsers(@RequestBody Users users){ return userServices.addUsers(users); }
    @RequestMapping("/users/updateUsers")
    public String updateUsers(@RequestBody Users users){
        return userServices.updateUsers(users);
    }
    @RequestMapping("/users/deleteUsers")
    public String deleteUsers(@RequestBody Users users){
        return userServices.deleteUsers(users);
    }

    /*退出登录*/
    @RequestMapping("/users/logout")
    public void logout(@RequestHeader("token") String token) {

        UserTokenUtil.delUserSession(token);
    }
}
