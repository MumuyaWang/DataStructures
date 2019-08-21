package com.test.recursion;

/**
 * Created by Mumuya on 2019/8/21
 * 递归调用机制
 */
public class RecursionTest {
    public static void main(String[] args){

        //打印问题，递归调用机制
        test(4);

        //阶乘问题
        int res = factorial(4);
        System.out.println("res = " + res);

    }
    //打印
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
        System.out.println("n=" + n);    //如果这句话前面加else
    }

    //阶乘
    public static int factorial(int n) {
        if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
