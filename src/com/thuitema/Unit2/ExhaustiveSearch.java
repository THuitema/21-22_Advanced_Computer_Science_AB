package com.thuitema.Unit2;

public class ExhaustiveSearch {
    public static void main(String[] args) {
        findPath(0, 0, 3, 5);
    }

    public static void findPath(int startX, int startY, int endX, int endY) {
        makePath(startX, startY, endX, endY, "");
    }

    public static void makePath(int startX, int startY, int endX, int endY, String path) {
        if(startX == endX && startY == endY) {
            System.out.println(path);
        }
        else if (startX == endX) {
            // can't go any more east, only north
            makePath(startX, startY + 1, endX, endY, path + "N ");
        }
        else if (startY == endY) {
            // can't go any more north, only east
            makePath(startX + 1, startY, endX, endY, path + "E ");
        }
        else {
            // can make any move
            makePath(startX + 1, startY, endX, endY, path + "E "); // E
            makePath(startX, startY + 1, endX, endY, path + "N "); // N
            makePath(startX + 1, startY + 1, endX, endY, path + "NE "); // NE
        }
    }

}
