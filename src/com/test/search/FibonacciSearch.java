package com.test.search;

import java.util.Arrays;

/**
 * 斐波那契(黄金分割法)查找
 * 改变了中间结点（mid）的位置，mid不再是中间或插值得到，而是位于黄金分割点附近，
 * 即mid=low+F(k-1)-1（F代表斐波那契数列）
 * Created by Mumuya on  2019/9/22
 */
public class FibonacciSearch {
    public static int maxSize = 20;

    public FibonacciSearch() {
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 8, 10, 89, 1000, 1234};
        System.out.println("index=" + fibSearch(arr, 189));
    }

    public static int[] fib() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;

        for(int i = 2; i < maxSize; ++i) {
            f[i] = f[i - 1] + f[i - 2];
        }

        return f;
    }

    public static int fibSearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        int k = 0;
//        int mid = 0;

        int[] f;
        for(f = fib(); high > f[k] - 1; ++k) {
        }

        int[] temp = Arrays.copyOf(a, f[k]);

        for(int i = high + 1; i < temp.length; ++i) {
            temp[i] = a[high];
        }

        while(low <= high) {
            int mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                --k;
            } else {
                if (key <= temp[mid]) {
                    if (mid <= high) {
                        return mid;
                    }

                    return high;
                }
                low = mid + 1;
                k -= 2;
            }
        }
        System.out.println("没找到这个数");
        return -1;
    }
}
