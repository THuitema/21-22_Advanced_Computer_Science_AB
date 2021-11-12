package com.thuitema.Unit2;

public class ExhaustiveSearch2 {
    public static void main(String[] args) {
        leftRight("", 3);
        System.out.println();
        oddDigits("", 2);
    }

    public static void leftRight(String s, int n) {
        if(s.length() == n) {
            System.out.println(s);
        }
        else {
            leftRight(s + "L", n);
            leftRight(s + "R", n);
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
}
