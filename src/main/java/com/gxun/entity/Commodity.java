package com.gxun.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.soap.SAAJResult;

/**
 * @author 杨
 * 商品类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Commodity {
    private String cid;
    private String cname;
    private Float price;
    private String information;
    private String detailed;
    private String photo;
    private String time;
    private Integer number;
    private Integer salesVolume;
    private Integer shelfState;
    private Integer classify;
}
