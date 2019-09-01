package com.test.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 冒泡排序+优化
 * 从前向后（从下标较小的元素开始）,依次比较相邻元素的值，若发现逆序则交换。
 * (1) 一共进行 数组的大小-1 次 大的循环
 * (2) 每一趟排序的次数在逐渐的减少
 * (3) 如果我们发现在某趟排序中，没有发生一次交换， 可以提前结束冒泡排序。
 *
 * Created by Mumuya on  2019/9/1
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, 20};
        System.out.println("排序前的数组：");
        System.out.println(Arrays.toString(arr));

        //冒泡排序
        bubbleSort(arr);

        System.out.println("排序后的数组：");
        System.out.println(Arrays.toString(arr));

        //测试80000条数据用的排序时间
        int arr2[] = new int[80000];
        //随机生成数据
        for(int i = 0 ; i < 80000; i++){
            arr2[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("80000个随机数字排序前的时间是："+date1Str);
        bubbleSort(arr2);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("80000个随机数字排序前的时间是："+date2Str);
    }

        //把冒泡排序封装成一个方法
        public static void bubbleSort(int[] arr){
            //用于交换的临时变量
            int temp = 0;
            //标识符，表示是否进行过交换
            boolean flag = false;

            for(int i = 0; i < arr.length - 1; i++){
                for(int j = 0; j < arr.length -1; j++){
                    //如果前面的数比后面的大，则交换
                    if(arr[j] > arr[j+1]){
                        temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                        flag = true;
                    }
                }
//                System.out.println("第"+ (i+1) + "趟排序后的数组：");
//                System.out.println(Arrays.toString(arr));

                //【优化】
                if(!flag ){
                    //如果某趟排序中，一次交换都没有发生过，说明排序已是有序的，结束循环
                    break;
                }else {
                    //重置flag，进行下一趟的判断
                    flag = false;
                }
        }
//        for(int i = 0; i < arr.length; i++){
//            System.out.print(arr[i] + " ");
//        }
    }
}
