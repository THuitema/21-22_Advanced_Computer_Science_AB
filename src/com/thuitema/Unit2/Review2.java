package com.thuitema.Unit2;

public class Review2 {
    public static void main(String[] args) {
        char[] a = {'a', 'b', 'c', 'd'};
//        arrayCombos("", a, 2);

//        findPath(0, 0, 1, 2);
        System.out.println(logBase2(16));
    }

    public static void arrayCombos(String s, char[] a, int n) {
        if(n == 0) {
            System.out.println(s);
        }
        else {
            for(int i = 0; i < a.length; i++) {
                arrayCombos(s + a[i], a, n - 1);
            }
         }
    }

    public static void areaFill(char[][] a, int r, int c, char ch) {
        if(r >= 0 && r < a.length && c >= 0 && c < a[0].length && a[r][c] == ch) {
            a[r][c] = '*';
            areaFill(a, r + 1, c, ch);
            areaFill(a, r - 1, c, ch);
            areaFill(a, r, c + 1, ch);
            areaFill(a, r, c - 1, ch);
        }
    }

    public static void findPath(int startX, int startY, int endX, int endY) {
        findPathHelper(startX, startY, endX, endY, "");
    }
    private static void findPathHelper(int startX, int startY, int endX, int endY, String s) {
        if(startX == endX && startY == endY) {
            System.out.println(s);
        }
        else if(startX <= endX && startY <= endY) {
            findPathHelper(startX, startY + 1, endX, endY, s + " N");
            findPathHelper(startX + 1, startY + 1, endX, endY, s + " NE");
            findPathHelper(startX + 1, startY, endX, endY, s + " E");

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
