package com.gxun.services;

import com.gxun.entity.Commodity;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.HashMap;

/**
 * @author 杨
 */
public interface CommodityServices {
    String queryCommodityList(HashMap<String, Object> u);
    String queryCommodityListIndex(HashMap<String, Object> u);
    String uploadFile(MultipartFile multipartFile) throws IOException;
    String addCommodity(Commodity commodity);
    String updateCommodity(Commodity commodity);
    String deleteCommodity(Commodity commodity);
}
