package com.hadouken900.redisImpl.service;

import com.hadouken900.redisImpl.repo.MapRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
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
}
