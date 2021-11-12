package com.thuitema.Unit2;

public class Review {
    public static void main(String[] args) {
        char[] arr = {'a', 'b', 'c', 'd'};
//        combos("", arr, 2);
//        oddDigits("", 2);
//        int[] nums = {1, 2, 3, 4, 5};
//        reverse1D(nums, nums.length - 1);
//        substringWithoutSubstring("hello world", 2, 6);

        System.out.println(logBase2(8));
    }

    // print all n-letter combinations in arr
    public static void combos(String s, char[] arr, int n) {
        if(s.length() == n) {
            System.out.println(s);
        }
        else {
            for(int i = 0; i < arr.length; i++) {
                combos(s + arr[i], arr, n);
            }
        }
    }

    public static void oddDigits(String s, int n) {
        if(n < 1) {
            System.out.println(s);
        }
        else {
            for(int i = 1; i <= 9; i += 2) {
                oddDigits(s + i, n - 1);
            }
        }
    }

    public static void reverse1D(int[] nums, int i) {
        if(i == 0) {
            System.out.println(nums[0]);
        }
        else {
            System.out.println(nums[i]);
            reverse1D(nums, i - 1);
        }
    }

    // end is exclusive, start is inclusive
    public static void substringWithoutSubstring(String s, int start, int end) {
        if(start != end) {
            System.out.print(s.charAt(start));
            substringWithoutSubstring(s, start + 1, end);
        }
    }

    public static int logBase2(int n) {
        if(n < 2) {
            return 0;
        }
        else {
            return 1 + logBase2(n / 2);
        }
    }
}
