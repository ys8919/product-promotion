package com.gxun.dao;

import com.gxun.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author 杨
 */
@Mapper
@Repository
public interface UserMapper {
    ArrayList<Users> queryUsersList(HashMap<String, Object> u);
    Users login(Users users);
    int addUsers(Users users);
    int updateUsers(Users users);
    int deleteUsers(Users users);
}
