package com.thuitema.Unit2;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 6, 9, 10, 11, 13};

        System.out.println(binarySearch(nums, 3, 0, nums.length - 1));
    }

    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if(low > high) {
            return false;
        }
        else {
            int mid = (low + high) / 2;
            if(data[mid] == target) {
                return true;
            }
            else if (target < data[mid]) {
                return binarySearch(data, target, low, mid - 1);
            }
            else {
                return binarySearch(data, target, mid + 1, high);
            }
        }
    }
}
