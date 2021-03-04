package com.gxun.dao;

import com.gxun.entity.Collection;
import com.gxun.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

@Mapper
@Repository
public interface CollectionMapper {
    ArrayList<Collection> queryCollectionList(HashMap<String, Object> u);
    int addCollection(Collection collection);
    int updateCollection(Collection collection);
    int deleteCollection(Collection collection);
}
