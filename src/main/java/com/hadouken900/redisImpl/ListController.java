package com.hadouken900.redisImpl;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class ListController {

    private final ConcurrentHashMap<String, List<String>> listData = new ConcurrentHashMap<>();

    @PostConstruct
    public void loadData() {
        List<String> l1 = new ArrayList<>();
        l1.add("vasya");
        l1.add("petya");
        l1.add("vanya");

        List<String> l2 = new ArrayList<>();
        l2.add("myaso");
        l2.add("ryba");
        l2.add("kurica");
        listData.put("1",l1);
        listData.put("2",l2);
    }

    @GetMapping("/lists")
    public Map<String, List<String>> getAllLists() {
        return listData;
    }

    @GetMapping("/lists/{listName}")
    public List<String> getList(@PathVariable String listName) {
        return listData.get(listName);
    }

    @GetMapping("/lists/{listName}/{index}")
    public String getValueFromList(@PathVariable String listName,
                                   @PathVariable int index) {


        return listData.get(listName).size() > index ? listData.get(listName).get(index) : null;
    }

    @PostMapping("/lists/{listName}/{index}/{value}")
    public String setValue(@PathVariable String listName,
                         @PathVariable int index,
                         @PathVariable String value) {
        List<String> stringList = listData.get(listName);
        if (stringList != null) {
            if (stringList.size() > index) {
                stringList.set(index,value);
                return "(integer) "+stringList.size();
            } else if (stringList.size() == index) {
                stringList.add(index, value);
                return "(integer) " +stringList.size();
            }
            else {
                return "wrong index - " + index;
            }
        } else {
            listData.put(listName, new ArrayList<>());
            List<String> stringList1 = listData.get(listName);
            if (index == 0) {
                stringList1.add(value);
                return "(integer) " +stringList1.size();
            } else {
                return "wrong index - " + index;
            }
        }
    }

    @DeleteMapping("/lists/{listName}")
    public void delList(@PathVariable String listName) {
        listData.remove(listName);
    }

    @DeleteMapping("/lists/{listName}/{index}")
    public String delValueFromList(@PathVariable String listName,
                                 @PathVariable int index) {
        List<String> stringList = listData.get(listName);
        if (stringList != null) {
            if (stringList.size() > index) {
                stringList.remove(index);
                return "(integer) " + stringList.size();
            } else {
                return "wrong index - " + index;
            }
        }
        else {
            return "wrong index - " + index;
        }
    }

}
