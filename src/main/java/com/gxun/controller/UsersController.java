package com.gxun.controller;

import com.gxun.entity.Users;
import com.gxun.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author 杨
 */
@Controller
@ResponseBody
@CrossOrigin    //解决跨域问题
public class UsersController {
    @Autowired
    private UserServices userServices;
    @RequestMapping("login")
    public String login(@RequestBody Users u){
        return userServices.login(u);
    }

    @RequestMapping("queryUserList")
    public  String queryUserList(@RequestBody HashMap<String,Object> u){
        return userServices.queryUsersList(u);
    }
    @RequestMapping("addUsers")
    public String addUsers(@RequestBody Users users){
        return userServices.addUsers(users);
    }
    @RequestMapping("updateUsers")
    public String updateUsers(@RequestBody Users users){
        return userServices.updateUsers(users);
    }
    @RequestMapping("deleteUsers")
    public String deleteUsers(@RequestBody Users users){
        return userServices.deleteUsers(users);
    }
}
