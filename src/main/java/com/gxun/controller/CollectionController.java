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
public class CollectionController {
    @Autowired
    private CollectionServices collectionServices;
    @RequestMapping("/collection/queryCollectionList")
    public  String queryCollectionList(@RequestBody HashMap<String,Object> u){
        return collectionServices.queryCollectionList(u);
    }

    @RequestMapping("/collection/queryCollectionAndComList")
    public  String queryCollectionAndComList(@RequestBody HashMap<String,Object> u){
        return collectionServices.queryCollectionAndComList(u);
    }

    @RequestMapping("/collection/queryCollectionAndUserList")
    public  String queryCollectionAndUserList(@RequestBody HashMap<String,Object> u){
        return collectionServices.queryCollectionAndUserList(u);
    }

    @RequestMapping("/collection/addCollection")
    public String addCollection(@RequestBody HashMap<String,Object> u){
        return collectionServices.addCollection(u);
    }
    @RequestMapping("/collection/updateCollection")
    public String updateCollection(@RequestBody Collection collection){
        return collectionServices.updateCollection(collection);
    }
    @RequestMapping("/collection/deleteCollection")
    public String deleteCollection(@RequestBody Collection collection){
        return collectionServices.deleteCollection(collection);
    }
    @RequestMapping("/collection/queryIsCollection")
    public String queryIsCollection(@RequestBody Collection collection){
        return collectionServices.queryIsCollection(collection);
    }
}
