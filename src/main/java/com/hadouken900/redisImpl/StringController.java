package com.hadouken900.redisImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class StringController {

    private final ConcurrentHashMap<String, String> stringData = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadData() {
        stringData.put("1", "some");
        stringData.put("2", "body");
        stringData.put("3", "once");
    }

    @GetMapping("/strings")
    public Map<String, String> getAllStrings() {
        return stringData;
    }

    @GetMapping("/strings/{key}")
    public String getStringValue(@PathVariable String key) {
        return stringData.get(key);
    }

    @GetMapping("/strings/keys")
    public Enumeration<String> getStringKeys() {
        return stringData.keys();
    }

    @PostMapping("/strings/{key}/{value}")
    public void setStringValue(@PathVariable String key,
                         @PathVariable String value) {
        stringData.put(key,value);
    }

    @PostMapping("/strings/{key}/{value}/{ex}")
    public void setStringValueWithEx(@PathVariable String key,
                               @PathVariable String value,
                               @PathVariable int ex) {


        stringData.put(key, value);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(ex*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    stringData.remove(key);
                }
            }
        });
        t1.start();
    }

    @DeleteMapping("/strings/{key}")
    public void delStringKey(@PathVariable String key) {
        stringData.remove(key);
    }
}
