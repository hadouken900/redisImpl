package com.hadouken900.redisImpl.repo;

import org.springframework.stereotype.Repository;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ListRepo {


    private final ConcurrentHashMap<String, List<String>> listData = new ConcurrentHashMap<>();


    public void put(String s, List<String> list) {
        listData.put(s,list);
    }

    public Map<String, List<String>> getAll() {
        return listData;
    }

    public List<String> get(String listName) {
       return  listData.get(listName);
    }

    public List<String> remove(String listName) {
        return listData.remove(listName);
    }

    public Enumeration<String> getKeys() {
        return listData.keys();
    }
}
