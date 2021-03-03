package com.gxun.services;

import com.gxun.entity.Users;


import java.util.HashMap;
import java.util.List;

/**
 * @author 杨
 */


public interface UserServices {
    String queryUsersList(HashMap<String, Object> u);
    String login(Users users);
    String addUsers(Users users);
    String updateUsers(Users users);
    String deleteUsers(Users users);
}
