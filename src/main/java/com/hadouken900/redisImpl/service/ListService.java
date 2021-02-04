package com.hadouken900.redisImpl.service;

import com.hadouken900.redisImpl.repo.ListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Service
public class ListService {

    @Autowired
    private ListRepo listRepo;

    public void put(String s, List<String> list) {
        listRepo.put(s,list);
    }

    public Map<String, List<String>> getAll() {
        return listRepo.getAll();
    }

    public List<String> get(String listName) {
        return listRepo.get(listName);
    }

    public String getValue(String listName, int index) {

        return listRepo.get(listName).size() > index ? listRepo.get(listName).get(index) : "wrong index - " + index;
    }

    public void remove(String listName) {
        listRepo.remove(listName);
    }

    public String setValue(String listName, int index, String value) {
        List<String> stringList = listRepo.get(listName);
        String result = "";

        if (stringList != null) {
            if (stringList.size() > index) {
                stringList.set(index,value);
                result = "(integer) "+stringList.size();
            } else if (stringList.size() == index) {
                stringList.add(index, value);
                result = "(integer) " +stringList.size();
            }
            else {
                result = "wrong index - " + index;
            }
        } else {
            listRepo.put(listName, new ArrayList<>());
            List<String> stringList1 = listRepo.get(listName);
            if (index == 0) {
                stringList1.add(value);
               result = "(integer) " +stringList1.size();
            } else {
                result = "wrong index - " + index;
            }
        }
        return result;
    }

    public String delValue(String listName, int index) {
        List<String> stringList = listRepo.get(listName);
        String result = "";

        if (stringList != null) {
            if (stringList.size() > index) {
                stringList.remove(index);
                result =  "(integer) " + stringList.size();
            } else {
                result = "wrong index - " + index;

            }
        }
        else {
            result =  "wrong index - " + index;
        }
        return result;
    }

    public Enumeration<String> getKeys() {
        return listRepo.getKeys();
    }

    public void setValueWithTTL(String listName, int index, String value, int ex) {
        List<String> stringList = listRepo.get(listName);
        if (stringList == null) {
            setValue(listName,index,value);
        }
        Thread thread1 = new Thread(new TTLRunnable(ex,listName));
        thread1.start();
    }

    class TTLRunnable implements Runnable {

        private final int ex;
        private final String key;

        public TTLRunnable(int ex, String key) {
            this.ex = ex;
            this.key = key;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(ex* 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                remove(key);
            }
        }
    }
}
