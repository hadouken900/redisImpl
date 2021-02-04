package com.hadouken900.redisImpl.repo;

import org.springframework.stereotype.Repository;

import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class StringRepo {
    private final ConcurrentHashMap<String, String> stringData = new ConcurrentHashMap<>();

    public String get(String key) {
        return stringData.get(key);
    }

    public void put(String key, String value) {
        stringData.put(key,value);
    }

    public Enumeration<String> getKeys() {
        return stringData.keys();
    }

    public Map<String, String> getAll() {
        return stringData;
    }

    public String remove(String key) {
        return stringData.remove(key);
    }
}
