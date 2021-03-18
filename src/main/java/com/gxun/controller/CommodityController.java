package com.gxun.controller;

import com.gxun.entity.Commodity;
import com.gxun.entity.Users;
import com.gxun.services.CommodityServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Controller
@ResponseBody
public class CommodityController {
    @Autowired
    private CommodityServices commodityServices;

    //上传申请表接口

    @RequestMapping(value="/commodity/uploadFile",method= RequestMethod.POST)
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return commodityServices.uploadFile(multipartFile);
    }
    @RequestMapping("/commodity/queryCommodityList")
    public  String queryCommodityList(@RequestBody HashMap<String,Object> u){
        return commodityServices.queryCommodityList(u);
    }
    @RequestMapping("/commodity/queryCommodityListIndex")
    public  String queryCommodityListIndex(@RequestBody HashMap<String,Object> u){
        return commodityServices.queryCommodityListIndex(u);
    }
    @RequestMapping("/commodity/addCommodity")
    public String addCommodity(@RequestBody Commodity commodity){
        return commodityServices.addCommodity(commodity);
    }
    @RequestMapping("/commodity/updateCommodity")
    public String updateCommodity(@RequestBody Commodity commodity){
        return commodityServices.updateCommodity(commodity);
    }
    @RequestMapping("/commodity/deleteCommodity")
    public String deleteCommodity(@RequestBody Commodity commodity){
        return commodityServices.deleteCommodity(commodity);
    }

}
