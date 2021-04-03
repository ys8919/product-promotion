package com.gxun.services;

import com.gxun.entity.Collection;

import java.util.HashMap;

public interface CollectionServices {
    String queryCollectionList(HashMap<String, Object> u);
    String queryCollectionAndComList(HashMap<String, Object> u);
    String addCollection(Collection collection);
    String updateCollection(Collection collection);
    String deleteCollection(Collection collection);
}
