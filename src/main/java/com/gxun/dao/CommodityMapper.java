package com.gxun.dao;


import com.gxun.entity.Commodity;
import com.gxun.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
@Mapper
@Repository
public interface CommodityMapper {
    ArrayList<Commodity> queryCommodityList(HashMap<String, Object> u);
    int addCommodity(Commodity commodity);
    int updateCommodity(Commodity commodity);
    int deleteCommodity(Commodity commodity);
}
