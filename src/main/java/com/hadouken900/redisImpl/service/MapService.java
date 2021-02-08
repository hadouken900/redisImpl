package com.hadouken900.redisImpl.service;

import com.hadouken900.redisImpl.repo.MapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Service
public class MapService {

    @Autowired
    private MapRepo mapRepo;

    public Map<String, String> get(String key) {
        return mapRepo.get(key);
    }

    public Map<String, Map<String, String>> getAll() {
        return mapRepo.getAll();
    }

    public Enumeration<String> getKeys() {
        return mapRepo.getKeys();
    }

    public String getValueFromMap(String key, String value) {
        return mapRepo.get(key).get(value);
    }

    public String setValue(String key, String innerKey, String value) {
        Map<String, String> stringStringMap = mapRepo.get(key);
        String result = "";

        if (stringStringMap == null) {
            stringStringMap = new HashMap<>();
        }

        stringStringMap.put(innerKey,value);
        mapRepo.put(key,stringStringMap);
        result = "(integer) 1";

        return result;
    }

    public String delValue(String key) {
        mapRepo.remove(key);
        return "(integer) 1";
    }

    public String delInnerValue(String key, String innerKey) {
        mapRepo.get(key).remove(innerKey);
        return "(integer) 1";
    }
}
