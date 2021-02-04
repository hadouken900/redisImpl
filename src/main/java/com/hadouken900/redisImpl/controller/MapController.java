package com.hadouken900.redisImpl.controller;

import com.hadouken900.redisImpl.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;
import java.util.Map;

@RestController
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/maps/{key}")
    public Map<String, String> get(@PathVariable String key) {
        return mapService.get(key);
    }

    @GetMapping("/maps")
    public Map<String,Map<String,String>> getAll() {
        return mapService.getAll();
    }

    @GetMapping("/maps/keys")
    public Enumeration<String> getKeys() {
        return mapService.getKeys();
    }

    @GetMapping("maps/{key}/{value}")
    public String getValueFromMap(@PathVariable String key,
                                  @PathVariable String value) {
        return mapService.getValueFromMap(key,value);
    }




}
