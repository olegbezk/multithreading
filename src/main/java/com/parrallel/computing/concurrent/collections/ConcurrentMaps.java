package com.parrallel.computing.concurrent.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

class FirstWorkerCM implements Runnable {

    private ConcurrentHashMap<String, Integer> map;

    FirstWorkerCM(final ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            map.put("B", 1);
            map.put("H", 2);
            Thread.sleep(1000);
            map.put("F", 3);
            map.put("A", 4);
            Thread.sleep(1000);
            map.put("E", 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorkerCM implements Runnable {

    private ConcurrentHashMap<String, Integer> map;

    SecondWorkerCM(final ConcurrentHashMap<String, Integer> map) {
        this.map = map;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(map.get("A"));
            Thread.sleep(1000);
            System.out.println(map.get("E"));
            System.out.println(map.get("F"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class ConcurrentMaps {

    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        new Thread(new FirstWorkerCM(map)).start();
        new Thread(new SecondWorkerCM(map)).start();

        List<String> strings = new ArrayList<>();
        final List<String> synchronizedList = Collections.synchronizedList(strings);
    }
}
