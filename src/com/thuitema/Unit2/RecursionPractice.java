package com.thuitema.Unit2;

public class RecursionPractice {
    public static void main(String[] args) {

    }

    // 1. print an integer backward (done)
    public static String backwardInt(Integer n) {
        if(n < 10) {
            return n.toString();
        }
        else {
            Integer value = n % 10;
            return value.toString() + backwardInt(n / 10);
        }
    }

    // 2. find max value in a 1D array (done)
    public static int findMax(int[] n, int i, int max) {
        if(i == n.length - 1) {
            return max;
        }
        else {
            if(n[i] < n[i + 1]) {
                max = n[i + 1];
            }

            return findMax(n, i + 1, max);
        }
    }

    // 3. print contents of array backwards (done)
    public static void arrayBackwards(int[] n, int i) {
        if(i == 0) {
            System.out.println(n[0]);
        }
        else {
            System.out.println(n[i]);
            arrayBackwards(n, i - 1);
        }

    }

    // 4. check if a string is palindrome (done)
    public static boolean checkPalindrome(String s, int low, int high) {
        if(low >= high) {
            return true; // no more values to compare - all match and string is palindrome
        }
        else {
            // if opposite chars match, check the inner index
            if(s.substring(low, low + 1).equals(s.substring(high, high + 1))) {
                return checkPalindrome(s, low + 1, high - 1);
            }
            return false; // opposite chars aren't equal
        }
    }

    // 5. sum of all values in integer array (done)
    public static int arraySum(int[] n, int i) {
        if(i == n.length) {
            return 0;
        }
        else {
            return n[i] + arraySum(n, i + 1);
        }
    }

    // 6. log base 2 of a number (done)
    public static int logBase2(int n) {
        if(n <= 2) {
            return 1;
        }

        else {
            return 1 + logBase2(n / 2);
        }
    }

}
