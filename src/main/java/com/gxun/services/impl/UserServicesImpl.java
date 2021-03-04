package com.gxun.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxun.dao.UserMapper;
import com.gxun.entity.Users;
import com.gxun.services.UserServices;
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
        ArrayList<Users> users= usersMapper.queryUsersList(u);
        PageInfo<Users> pageinfo=new PageInfo<Users>(users);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("count",pageinfo.getTotal());

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
    public String addUsers(Users users) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        users.setUid(RandIdUtil.randomUserId());
        if(usersMapper.addUsers(users)>0)
        {
            msg.put("msg","注册成功");
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
}
