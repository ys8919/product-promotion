package com.gxun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨
 * 用户类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    private String uid;
    private String username;
    private String password;
    private String email;
    private int jction;
}
