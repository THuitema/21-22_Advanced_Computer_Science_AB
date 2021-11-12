package com.thuitema.Unit1.Exceptions;

public class Exceptions {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};

        try {
            for(int i = 0; i < 10; i++) {
                System.out.println(nums[i]);
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Don’t try buffer overflow attacks in Java!");
        }

        double balance = 100;
        double amount = -10;
        if(amount < 0) {
            throw new IllegalArgumentException("Can't enter a negative amount");
        }
        else {
            balance -= amount;
        }
        System.out.println("Balance: " + balance);
    }


}
