package com.test.search;

/**
 * 插值查找算法
 * 插值查找每次从自适应mid处开始查找
 *
 * Created by Mumuya on  2019/9/22
 */
public class InsertValueSearch {
    public InsertValueSearch() {
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 10, 89, 1000, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        System.out.println("index = " + index);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        System.out.println("二分查找被调用~");
        if (left > right) {
            return -1;
        } else {
            int mid = (left + right) / 2;
            int midVal = arr[mid];
            if (findVal > midVal) {
                return binarySearch(arr, mid + 1, right, findVal);
            } else {
                return findVal < midVal ? binarySearch(arr, left, mid - 1, findVal) : mid;
            }
        }
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal) {
        System.out.println("插值查找次数~~");
        if (left <= right && findVal >= arr[0] && findVal <= arr[arr.length - 1]) {
            int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
            int midVal = arr[mid];
            if (findVal > midVal) {
                return insertValueSearch(arr, mid + 1, right, findVal);
            } else {
                return findVal < midVal ? insertValueSearch(arr, left, mid - 1, findVal) : mid;
            }
        } else {
            return -1;
        }
    }
}

