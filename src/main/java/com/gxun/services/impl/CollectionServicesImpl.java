package com.gxun.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxun.dao.CollectionMapper;
import com.gxun.dao.CommodityMapper;
import com.gxun.entity.Collection;
import com.gxun.entity.Commodity;
import com.gxun.services.CollectionServices;
import com.gxun.util.ConstantValueUtil;
import com.gxun.util.RandIdUtil;
import com.gxun.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

@Service
public class CollectionServicesImpl implements CollectionServices {
    @Autowired
    private CollectionMapper collectionMapper;
    @Autowired
    private CommodityMapper commodityMapper;
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
        msg.put("code", ConstantValueUtil.RESCODE_SUCCESS);
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String queryCollectionAndComList(HashMap<String, Object> u) {
        int limit=Integer.parseInt((String)u.get("limit").toString());
        int page=Integer.parseInt((String)u.get("page").toString());
        PageHelper.startPage(page,limit);
        ArrayList<LinkedHashMap> collectionList= collectionMapper.queryCollectionAndComList(u);
        PageInfo<LinkedHashMap> pageinfo= new PageInfo<>(collectionList);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("code", ConstantValueUtil.RESCODE_SUCCESS);
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String queryCollectionAndUserList(HashMap<String, Object> u) {
        int limit=Integer.parseInt((String)u.get("limit").toString());
        int page=Integer.parseInt((String)u.get("page").toString());
        PageHelper.startPage(page,limit);
        ArrayList<LinkedHashMap> collectionList= collectionMapper.queryCollectionAndUserList(u);
        PageInfo<LinkedHashMap> pageinfo= new PageInfo<>(collectionList);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("code", ConstantValueUtil.RESCODE_SUCCESS);
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String addCollection(HashMap<String, Object> u) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        Collection collection = new Collection();
        collection.setUid((String) u.get("uid"));
        collection.setCid((String) u.get("cid"));
        if(collectionMapper.queryIsCollection(collection)==null){
            collection.setId(RandIdUtil.getCollectionId());
            collection.setTime(TimeUtil.getTime());
            Commodity commodity1 = new Commodity();
            commodity1.setCid((String) u.get("cid"));
            Commodity commodity = commodityMapper.selectOneCommodity(commodity1);

            if(collectionMapper.addCollection(collection)>0)
            {
                commodity.setSalesVolume(commodity.getSalesVolume()+1);
                if(commodityMapper.updateCommodity(commodity)>0){
                    msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
                    msg.put("msg","添加收藏成功");
                    msg.put("flag",true);
                }else{
                    msg.put("msg","添加收藏失败，请重新尝试");
                    msg.put("flag",false);
                }
            }else
            {
                msg.put("msg","添加收藏失败，请重新尝试");
                msg.put("flag",false);
            }
        }else{
            msg.put("msg","添加收藏失败，已收藏");
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
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
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
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("msg","删除收藏成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","删除收藏失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String queryIsCollection(Collection collection) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        Collection collection1 = collectionMapper.queryIsCollection(collection);
        if(collection1!=null)
        {
            msg.put("data",collection1);
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("msg","已收藏");
            msg.put("flag",true);
            msg.put("IsCollection",true);

        }else
        {
            msg.put("msg","未收藏");
            msg.put("IsCollection",false);
            msg.put("flag",true);
        }
        return JSON.toJSONString(msg);
    }
}
