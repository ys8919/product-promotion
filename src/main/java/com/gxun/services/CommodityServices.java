package com.gxun.services;

import com.gxun.entity.Commodity;


import java.util.HashMap;

/**
 * @author 杨
 */
public interface CommodityServices {
    String queryCommodityList(HashMap<String, Object> u);
    String addCommodity(Commodity commodity);
    String updateCommodity(Commodity commodity);
    String deleteCommodity(Commodity commodity);
}
