package com.thuitema.Hashing;

import java.util.HashSet;
import java.util.Set;

public class HashingWS {
    public static void main(String[] args) {
        Set<Double> nums = new HashSet<>();
        for(int i=0; i<10; i++) {
            nums.add(5.2);
        }
        System.out.println(nums);
    }
}
