package com.hadouken900.redisImpl.repo;

import org.springframework.stereotype.Repository;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapRepo {

    private final ConcurrentHashMap<String, Map<String,String>> mapData = new ConcurrentHashMap<>();

    public Map<String,String> get(String key){
        return mapData.get(key);
    }

    public Map<String,Map<String,String>> getAll() {
        return mapData;
    }

    public Enumeration<String> getKeys() {
        return mapData.keys();
    }

    public void put(String key, Map<String,String> map) {
        mapData.put(key,map );
    }

    public Map<String,String> remove(String key) {
        return mapData.remove(key);
    }
}
