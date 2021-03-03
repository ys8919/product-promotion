package com.gxun.controller;

import com.gxun.entity.Commodity;
import com.gxun.entity.Users;
import com.gxun.services.CommodityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller
@ResponseBody
@CrossOrigin    //解决跨域问题
public class CommodityController {
    @Autowired
    private CommodityServices commodityServices;
    @RequestMapping("queryCommodityList")
    public  String queryCommodityList(@RequestBody HashMap<String,Object> u){
        return commodityServices.queryCommodityList(u);
    }
    @RequestMapping("addCommodity")
    public String addCommodity(@RequestBody Commodity commodity){
        return commodityServices.addCommodity(commodity);
    }
    @RequestMapping("updateCommodity")
    public String updateCommodity(@RequestBody Commodity commodity){
        return commodityServices.updateCommodity(commodity);
    }
    @RequestMapping("deleteCommodity")
    public String deleteCommodity(@RequestBody Commodity commodity){
        return commodityServices.deleteCommodity(commodity);
    }

}
