package com.test.recursion;

/**
 * 8皇后的问题
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法
 * 解题思路：
 * 1、第一个皇后先放第一行第一列
 * 2、第二个皇后放在第二行第一列、然后判断是否OK， 如果不OK，继续放在第二列、第三列、依次把所有列都放完，找到一个合适
 * 3、继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解
 * 4、当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后，放到第一列的所有正确解，全部得到.
 * 5、然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 *
 * Created by Mumuya on  2019/9/1
 */
public class Queen8 {

    //定义一个max表示有多少个皇后
    int max = 8;

    //定数组array,保存皇后放置的结果
    int[] array = new int[max];

    //统计有多少种解法
    static int count = 0;

    //统计一共判断了多少次
    static int judgeCount = 0;

    public static void main(String[] args){
        //测试一下8皇后是否正确
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.println("一共有 "+ count + " 种解法");
        System.out.println("一共判断冲突的次数：" + judgeCount);
    }

    //编写一个方法，放置第n个皇后
    //check 是每一次递归时，进入到check中都有for(int i = 0; i < max; i++)，因此有回溯
    private void check(int n){
        if(n == max){
            //n=8时，8个皇后都放置好了
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for(int i = 0; i < max; i++){
            //先把当前皇后n，放到该行的第一列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){
                //如果不冲突，继续放置第n+1个皇后
                check(n+1);
            }
            //如果冲突，就继续执行array[n]=i;将第n个皇后，放置在本行的后移的一个位置
        }
    }

    //查看当我们放置第n个皇后后，检测该皇后是否和前面已摆放的皇后冲突
    /**
     * 查看当我们放置第n个皇后后，检测该皇后是否和前面已摆放的皇后冲突
     * @param n 表示第几个皇后
     * @return
     */
    private boolean judge(int n){
        judgeCount++;
        for(int i = 0; i < n; i++){
            //array[i] == array[n]判断第n个皇后和前面的n-1个皇后在同一列
            //Math.abs(n-i) == Math.abs(array[n] - array[i]) 表示判断第n个皇后是否和第i个皇后在同一斜线上
            //不需要判断是否在同一行，n在递增
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return  false;
            }
        }
        return true;
    }

    //写一个方法，输出皇后摆放的位置
    private void print(){
        count++;
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
