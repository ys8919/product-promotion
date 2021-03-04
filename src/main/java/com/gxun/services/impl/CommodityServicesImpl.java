package com.gxun.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxun.dao.CommodityMapper;
import com.gxun.entity.Commodity;
import com.gxun.entity.Users;
import com.gxun.services.CommodityServices;
import com.gxun.util.RandIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
@Service
public class CommodityServicesImpl implements CommodityServices {
    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public String queryCommodityList(HashMap<String, Object> u) {
        int limit=Integer.parseInt((String)u.get("limit").toString());
        int page=Integer.parseInt((String)u.get("page").toString());
        PageHelper.startPage(page,limit);
        ArrayList<Commodity> commodityList= commodityMapper.queryCommodityList(u);
        PageInfo<Commodity> pageinfo=new PageInfo<Commodity>(commodityList);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String addCommodity(Commodity commodity) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        commodity.setCid(RandIdUtil.rangCommodityId());
        if(commodityMapper.addCommodity(commodity)>0)
        {
            msg.put("msg","添加商品成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","添加商品失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String updateCommodity(Commodity commodity) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        if(commodityMapper.updateCommodity(commodity)>0)
        {
            msg.put("msg","更新商品成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","更新商品失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }

    @Override
    public String deleteCommodity(Commodity commodity) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        if(commodityMapper.deleteCommodity(commodity)>0)
        {
            msg.put("msg","删除商品成功");
            msg.put("flag",true);

        }else
        {
            msg.put("msg","删除商品失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }
}
