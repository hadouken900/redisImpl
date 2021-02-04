package com.hadouken900.redisImpl.controller;
import com.hadouken900.redisImpl.service.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.Map;

@RestController
public class StringController {

    @Autowired
    private StringService stringService;

    @PostConstruct
    public void loadData() {
        stringService.put("1", "some");
        stringService.put("2", "body");
        stringService.put("3", "once");
    }

    @GetMapping("/strings")
    public Map<String, String> getAllStrings() {
        return stringService.getAll();
    }

    @GetMapping("/strings/{key}")
    public String getStringValue(@PathVariable String key) {
        return stringService.get(key);
    }

    @GetMapping("/strings/keys")
    public Enumeration<String> getStringKeys() {
        return stringService.getKeys();
    }

    @PostMapping("/strings/{key}/{value}")
    public void setStringValue(@PathVariable String key,
                         @PathVariable String value) {
        stringService.put(key,value);
    }

    @PostMapping("/strings/{key}/{value}/{ex}")
    public void setStringValueWithEx(@PathVariable String key,
                               @PathVariable String value,
                               @PathVariable int ex) {


        stringService.putWithEx(key, value,ex);

    }

    @DeleteMapping("/strings/{key}")
    public void delStringKey(@PathVariable String key) {
        stringService.remove(key);
    }
}
