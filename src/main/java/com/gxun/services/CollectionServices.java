package com.gxun.services;

import com.gxun.entity.Collection;

import java.util.HashMap;

public interface CollectionServices {
    String queryCollectionList(HashMap<String, Object> u);
    String queryCollectionAndComList(HashMap<String, Object> u);
    String queryCollectionAndUserList(HashMap<String, Object> u);
    String addCollection(HashMap<String, Object> u);
    String updateCollection(Collection collection);
    String deleteCollection(Collection collection);
    String queryIsCollection(Collection collection);
}
