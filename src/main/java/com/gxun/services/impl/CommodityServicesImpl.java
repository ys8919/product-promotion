package com.gxun.services.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gxun.dao.CommodityMapper;
import com.gxun.entity.Commodity;
import com.gxun.entity.Users;
import com.gxun.services.CommodityServices;
import com.gxun.util.ConstantValueUtil;
import com.gxun.util.RandIdUtil;
import com.gxun.util.TencentCOSUtil;
import com.gxun.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
        msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String queryCommodityListIndex(HashMap<String, Object> u) {
        int limit=Integer.parseInt((String)u.get("limit").toString());
        int page=Integer.parseInt((String)u.get("page").toString());
        PageHelper.startPage(page,limit);
        ArrayList<Commodity> commodityList= commodityMapper.queryCommodityListIndex(u);
        PageInfo<Commodity> pageinfo=new PageInfo<Commodity>(commodityList);
        HashMap<String,Object> msg=new HashMap<String,Object>();
        msg.put("msg","查询成功");
        msg.put("flag",true);
        msg.put("data",pageinfo.getList());
        msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
        msg.put("count",pageinfo.getTotal());

        return JSON.toJSONString(msg);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        HashMap<String,Object> msg=new HashMap<String,Object>();

        String fileName = multipartFile.getOriginalFilename();

        //判断有无后缀
        assert fileName != null;
        if (fileName.lastIndexOf(".") < 0) {
            msg.put("msg","上传文件格式不正确");
            msg.put("flag",false);
        }

        //获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

//        //如果不是图片
//        if (!prefix.equalsIgnoreCase(".jpg") && !prefix.equalsIgnoreCase(".jpeg") && !prefix.equalsIgnoreCase(".svg") && !prefix.equalsIgnoreCase(".gif") && !prefix.equalsIgnoreCase(".png")) {
//            return new ForumResult(500, "上传图片格式不正确", null);
//        }

        //使用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile("ApplicationFile-" + System.currentTimeMillis(), prefix);
        //将Multifile转换成File
        multipartFile.transferTo(excelFile);

        //调用腾讯云工具上传文件
        String imageName = ConstantValueUtil.FILEURL+ TencentCOSUtil.uploadfile(excelFile, ConstantValueUtil.FILEFOLDER);
        //返回成功信息
        msg.put("msg","文件上传成功");
        msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
        msg.put("URL",imageName);
        msg.put("flag",true);
        return JSON.toJSONString(msg);

    }

    @Override
    public String addCommodity(Commodity commodity) {
        HashMap<String,Object> msg=new HashMap<String,Object>();
        commodity.setCid(RandIdUtil.rangCommodityId());
        commodity.setTime(TimeUtil.getTime());
        if(commodityMapper.addCommodity(commodity)>0)
        {
            msg.put("msg","添加商品成功");
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
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
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
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
        String[] keys=commodity.getPhoto().split("/");
        if(commodityMapper.deleteCommodity(commodity)>0)
        {
            String key=ConstantValueUtil.FILEFOLDER+"/"+keys[4];
            TencentCOSUtil.deletefile(key);
            msg.put("msg","删除商品成功");
            msg.put("code",ConstantValueUtil.RESCODE_SUCCESS);
            msg.put("flag",true);

        }else
        {
            msg.put("msg","删除商品失败，请重新尝试");
            msg.put("flag",false);
        }
        return JSON.toJSONString(msg);
    }
}
