package com.hadouken900.redisImpl.service;

import com.hadouken900.redisImpl.repo.MapRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@SpringBootTest
class MapServiceTest {

    private static final ConcurrentHashMap<String,Map<String,String>> data = new ConcurrentHashMap<>();

    @Autowired
    private MapService mapService;

    @MockBean
    private MapRepo mapRepo;


    @BeforeAll
    static void init() {
        Map<String,String> innerData1 = new HashMap<>();
        innerData1.put("innerKey1", "innerValue1");
        innerData1.put("innerKey2", "innerValue2");
        Map<String,String> innerData2 = new HashMap<>();
        innerData2.put("innerKey3", "innerValue3");
        innerData2.put("innerKey4", "innerValue4");

        data.put("first",innerData1  );
        data.put("second",innerData2);
    }

    @Test
    void get() {
        String key = "first";
        Mockito.doReturn(data.get(key)).when(mapRepo).get(key);
        Map<String, String> first = mapService.get(key);
        assertTrue(first.containsKey("innerKey1"));

    }

    @Test
    void getAll() {
        Mockito.doReturn(data).when(mapRepo).getAll();
        Map<String, Map<String, String>> all = mapService.getAll();
        assertEquals(data.keySet(), all.keySet());


    }

    @Test
    void getKeys() {
//        Mockito.doReturn(data.keys()).when(mapRepo).getKeys();
//        assertEquals(mapService.getKeys(), data.keys());
    }

    @Test
    void getValueFromMap() {
        String key = "first";
        String value = "innerKey1";
        String innerValue = "innerValue1";

        Mockito.doReturn(data.get(key)).when(mapRepo).get(key);
        assertEquals(mapService.getValueFromMap(key,value), innerValue);
    }
}