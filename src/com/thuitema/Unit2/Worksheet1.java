package com.thuitema.Unit2;

public class Worksheet1 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 4, 5, 6};
//        printArray(nums);
        shrinkingHashes(5);
        System.out.println();
        growingHashes(5, 5);
    }

    public static void shrinkingHashes(int n) {
        if(n == 0) {
            return;
        }
        else {
            for (int i = 0; i < n; i++) {
                System.out.print('#');
            }
            System.out.println();
            shrinkingHashes(n - 1);
        }
    }

    public static void growingHashes(int r, int n)  {
        if(r == 0) {
            return;
        }
        else {
            for (int i = n; i >= r; i--) {
                System.out.print('#');
            }
            System.out.println();
            growingHashes(r - 1, n);
        }
    }





    public static void printArray(int[] v) {
        System.out.println(printArrayRecur(v.length - 1, v));
    }

    public static String printArrayRecur(int n, int[] a) {
        if(n == 0) {
            return "" + a[0];
        }
        else {
            return printArrayRecur(n - 1, a) + "\n" + a[n];
        }
    }
}
