package com.thuitema.Unit1;

public class RationalNumDriver {
    public static void main(String[] args) {
        RationalNum r1 = new RationalNum(52, 36);
        System.out.println("r1: " + r1);

        RationalNum r2 = new RationalNum(7, 39);
        r2.setDenom(0);
        System.out.println("r2 = " + r2);
        r2.setDenom(14);

        System.out.println("r2: " + r2);
        System.out.println("r1 + r2: " + r1.add(r2));
        System.out.println("r1 - r2: " + r1.subtract(r2));
        System.out.println("r1 * r2: " + r1.multiply(r2));
        System.out.println("r1 / r2: " + r1.divide(r2));
        System.out.println("r1 equals r2: " + r1.equals(r2));
    }
}
