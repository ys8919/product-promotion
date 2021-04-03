package com.gxun.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxun.dao.UserMapper;
import com.gxun.entity.Users;
import com.gxun.services.UserServices;
import com.gxun.util.ConstantValueUtil;
import com.gxun.util.RandIdUtil;
import com.gxun.util.UserTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 杨
 */
@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    UserMapper usersMapper;
    @Override
    public String queryUsersList(HashMap<String, Object> u) {
        int limit=Integer.parseInt((String)u.get("limit").toString());
        int page=Integer.parseInt((String)u.get("page").toString());
        PageHelper.startPage(page,limit);
        //u.putIfAbsent("Jction", 0);//如果Jction为空则赋值
        System.out.println(u.toString());
        ArrayList<Users> users= usersMapper.queryUsersList(u);
        PageInfo<Users> pageinfo=new PageInfo<Users>(users);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("count",pageinfo.getTotal());
        msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
        return JSON.toJSONString(msg);
    }

    @Override
    public String login(Users u) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        Users user=usersMapper.login(u);
        //System.out.println(user.toString());
        if(user!=null)
        {
            String token= RandIdUtil.rangId();
            HashMap<String, Object> userinfomaintion=new HashMap<String, Object>();
            userinfomaintion.put("uid", u.getUid());
            userinfomaintion.put("Jction", user.getJction());
            UserTokenUtil.setUserSession(token,userinfomaintion);
            msg.put("token",token);
            msg.put("msg","登录成功");
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("flag",true);
            msg.put("uid",user.getUid());
            msg.put("data",user);
        }else
        {
            msg.put("msg","登录失败");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String register(Users users) {

        Users u=new Users();
        u.setUsername(users.getUsername());
        if(usersMapper.selectOneUser(u)==null){
            users.setUid(RandIdUtil.rangId());
            users.setJction(ConstantValueUtil.ORDINARY_JCTION);
            if(usersMapper.addUsers(users)>0)
            {
                HashMap<String, Object> msg=new HashMap<String, Object>();
                msg.put("msg", "注册成功，您的账号是"+users.getUid());
                msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
                msg.put("uid",users.getUid());
                msg.put("flag",true);
                return JSON.toJSONString(msg);
            }else
            {
                HashMap<String, Object> msg=new HashMap<String, Object>();

                msg.put("msg", "注册失败");
                msg.put("flag",false);
                return JSON.toJSONString(msg);
            }
        }else{
            HashMap<String, Object> msg=new HashMap<String, Object>();

            msg.put("msg", "用户名已存在");
            msg.put("flag",false);
            return JSON.toJSONString(msg);
        }

    }

    @Override
    public String forgetPassword(Users users) {
        HashMap<String, Object> msg=new HashMap<String, Object>();
        if(usersMapper.forgetPassword(users)>0)
        {
            msg.put("msg", "找回密码成功");
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("flag", true);
            return JSON.toJSONString(msg);
        }else
        {
            msg.put("msg","找回密码失败，用户名或者邮箱不对");
            msg.put("flag", false);
            return JSON.toJSONString(msg);
        }
    }

    @Override
    public String updatePassword(HashMap<String, Object> u) {
        HashMap<String, Object> msg=new HashMap<String, Object>();
        Users users = new Users();
        users.setUid((String) u.get("uid"));
        users.setUsername((String) u.get("username"));
        users.setPassword((String) u.get("password"));
        System.out.println(users);
        Users user=usersMapper.login(users);
        if(user!=null)
        {
            user.setPassword((String) u.get("newPasswd"));
            if(usersMapper.updateUsers(user)>0){
                msg.put("msg", "修改密码成功");
                msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
                msg.put("flag", true);
                return JSON.toJSONString(msg);
            }else {
                msg.put("msg","修改密码失败");
                msg.put("flag", false);
                return JSON.toJSONString(msg);
            }

        }else
        {
            msg.put("msg","修改密码失败，原密码不正确");
            msg.put("flag", false);
            return JSON.toJSONString(msg);
        }
    }

    /*暂时不用*/
    @Override
    public String addUsers(Users users) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        users.setUid(RandIdUtil.randomUserId());
        users.setJction(ConstantValueUtil.ORDINARY_JCTION);
        if(usersMapper.addUsers(users)>0)
        {
            msg.put("msg","注册成功");
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("flag",true);

        }else
        {
            msg.put("msg","注册成功，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String updateUsers(Users users) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        if(usersMapper.updateUsers(users)>0)
        {
            msg.put("msg","修改成功");
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("flag",true);

        }else
        {
            msg.put("msg","修改失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }


    @Override
    public String deleteUsers(Users users) {
        return null;
    }

    @Override
    public String selectOneUser(Users users) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        Users user=usersMapper.selectOneUser(users);
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",user);
        return JSON.toJSONString(msg);
    }
}
