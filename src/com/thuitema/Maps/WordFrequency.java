package com.thuitema.Maps;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordFrequency {
    public static void main(String[] args) {
        String[] data = new String("Nothing is as easy as it looks").split(" ");
        Map<String, Integer> map = new HashMap<>();
        for(String key : data) {
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        System.out.println(map);
    }
}
