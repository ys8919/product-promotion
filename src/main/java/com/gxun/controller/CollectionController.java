package com.gxun.controller;

import com.gxun.entity.Collection;

import com.gxun.services.CollectionServices;

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
public class CollectionController {
    @Autowired
    private CollectionServices collectionServices;
    @RequestMapping("queryCollectionList")
    public  String queryCollectionList(@RequestBody HashMap<String,Object> u){
        return collectionServices.queryCollectionList(u);
    }
    @RequestMapping("addCollection")
    public String addCollection(@RequestBody Collection commodity){
        return collectionServices.addCollection(commodity);
    }
    @RequestMapping("updateCollection")
    public String updateCollection(@RequestBody Collection commodity){
        return collectionServices.updateCollection(commodity);
    }
    @RequestMapping("deleteCollection")
    public String deleteCollection(@RequestBody Collection commodity){
        return collectionServices.deleteCollection(commodity);
    }
}
