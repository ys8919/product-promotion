package com.gxun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description:
 * @Author: yx8991
 * @Date: 2021/3/4 23:40
 * @param: null:
 * @return: null
 * @Info:
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Collection implements Serializable {
    private String id;
    private String uid;
    private String cid;
    private String time;
}
