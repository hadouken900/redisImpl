package com.hadouken900.redisImpl.controller;

import com.hadouken900.redisImpl.service.ListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@RestController
public class ListController {

    @Autowired
    private ListService listService;

    @PostConstruct
    public void loadData() {
        List<String> l1 = new ArrayList<>();
        l1.add("one");
        l1.add("two");
        l1.add("three");

        List<String> l2 = new ArrayList<>();
        l2.add("a");
        l2.add("b");
        l2.add("c");
        listService.put("1",l1);
        listService.put("2",l2);
    }

    @GetMapping("/lists")
    public Map<String, List<String>> getAllLists() {
        return listService.getAll();
    }

    @GetMapping("/lists/keys")
    public Enumeration<String> getKeys() {return listService.getKeys();}

    @GetMapping("/lists/{listName}")
    public List<String> getList(@PathVariable String listName) {
        return listService.get(listName);
    }

    @GetMapping("/lists/{listName}/{index}")
    public String getValueFromList(@PathVariable String listName,
                                   @PathVariable int index) {
        return listService.getValue(listName, index);
    }

    @PostMapping("/lists/{listName}/{index}/{value}")
    public String setValue(@PathVariable String listName,
                         @PathVariable int index,
                         @PathVariable String value) {
        return listService.setValue(listName, index, value);
    }

    @PostMapping("/lists/{listName}/{index}/{value}/{ex}")
    public void setValueWithListEx(@PathVariable String listName,
                                   @PathVariable int index,
                                   @PathVariable String value,
                                   @PathVariable int ex) {
        listService.setValueWithTTL(listName,index,value,ex);
    }

    @DeleteMapping("/lists/{listName}")
    public void delList(@PathVariable String listName) {
        listService.remove(listName);
    }

    @DeleteMapping("/lists/{listName}/{index}")
    public String delValueFromList(@PathVariable String listName,
                                 @PathVariable int index) {
        return listService.delValue(listName, index);
    }

}
