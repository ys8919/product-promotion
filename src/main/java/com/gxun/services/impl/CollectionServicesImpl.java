package com.gxun.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxun.dao.CollectionMapper;
import com.gxun.entity.Collection;
import com.gxun.services.CollectionServices;
import com.gxun.util.RandIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
@Service
public class CollectionServicesImpl implements CollectionServices {
    @Autowired
    private CollectionMapper collectionMapper;
    @Override
    public String queryCollectionList(HashMap<String, Object> u) {
        int limit=Integer.parseInt((String)u.get("limit").toString());
        int page=Integer.parseInt((String)u.get("page").toString());
        PageHelper.startPage(page,limit);
        ArrayList<Collection> collectionList= collectionMapper.queryCollectionList(u);
        PageInfo<Collection> pageinfo=new PageInfo<Collection>(collectionList);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String addCollection(Collection collection) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        collection.setId(RandIdUtil.getCollectionId());
        if(collectionMapper.addCollection(collection)>0)
        {
            msg.put("msg","添加收藏成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","添加收藏失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String updateCollection(Collection collection) {
        HashMap<String,Object> msg=new HashMap<String,Object>();

        if(collectionMapper.updateCollection(collection)>0)
        {
            msg.put("msg","更新收藏成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","更新收藏失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String deleteCollection(Collection collection) {
        HashMap<String,Object> msg=new HashMap<String,Object>();

        if(collectionMapper.deleteCollection(collection)>0)
        {
            msg.put("msg","删除收藏成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","删除收藏失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }
}
