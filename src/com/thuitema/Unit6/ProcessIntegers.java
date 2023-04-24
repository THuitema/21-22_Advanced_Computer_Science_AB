package com.thuitema.Unit6;

//import java.util.Scanner;

import java.util.*;

public class ProcessIntegers {
    public static void main(String[] args) {
        Queue<Integer> q = new LinkedList<>();
        int[] nums = {2, -4, 34, 51, 0, -23, -67, 42, -188, 45};

        // print negative numbers, add positives to queue
        for (int val : nums) {
            if (val < 0) {
                System.out.print(val + " ");
            } else {
                q.add(val);
            }
        }

        // empty the queue
        while (!q.isEmpty()) {
            System.out.print(q.remove() + " ");
        }

    }
}
