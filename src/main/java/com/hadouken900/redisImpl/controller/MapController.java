package com.hadouken900.redisImpl.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class MapController {

    private final ConcurrentHashMap<String, Map<String,String>> mapData = new ConcurrentHashMap<>();
}
