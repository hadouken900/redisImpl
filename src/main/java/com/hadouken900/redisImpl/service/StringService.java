package com.hadouken900.redisImpl.service;

import com.hadouken900.redisImpl.repo.StringRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Enumeration;
import java.util.Map;

@Service
public class StringService {

    @Autowired
    private StringRepo stringRepo;

    public String get(String key) {
        return stringRepo.get(key);
    }

    public Map<String,String> getAll() {
        return stringRepo.getAll();
    }

    public void put(String key, String value) {
        stringRepo.put(key,value);
    }

    public void putWithEx(String key, String value, int ex) {
        stringRepo.put(key,value);
        Thread t1 = new Thread(new TTLRunnable(ex,key));
        t1.start();

    }
    public Enumeration<String> getKeys() {
        return stringRepo.getKeys();
    }

    public String remove(String key) {
        return stringRepo.remove(key);
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
