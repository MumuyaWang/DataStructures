package com.test.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 【选择排序】
 * 1. 选择排序一共有 数组大小 - 1 轮排序
 * 2. 每1轮排序，又是一个循环, 循环的规则
 * 2.1先假定当前这个数是最小数
 * 2.2 然后和后面的每个数进行比较，如果发现有比当前数更小的数，就重新确定最小数，并得到下标
 * 2.3 当遍历到数组的最后时，就得到本轮最小数和下标
 * 2.4 交换
 *
 * Created by Mumuya on  2019/9/1
 */
public class SelectSort {
    public static void main(String[] args){
        int[] arr ={101, 34, 119, 1};
        System.out.println("排序前的数组：" + Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后的数组：" + Arrays.toString(arr));

        //测试80000条数据的排序速度，比冒泡快
        int arr2[] = new int[80000];
        //随机生成数据
        for(int i = 0 ; i < 80000; i++){
            arr2[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("80000个随机数字排序前的时间是："+date1Str);
        selectSort(arr2);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("80000个随机数字排序前的时间是："+date2Str);
    }

    public static void selectSort(int[] arr){
        for(int i = 0; i < arr.length - 1; i++){
            //假定是最小值
            int min = arr[i];
            int index = i;
            for(int j = i + 1; j < arr.length; j++){
                //如果不是最小值，那就取出最小值和最小值的下标
                if(arr[j] < min){
                    index = j;
                    min = arr[j];
                }
            }
            //如果当前次的比较后，发现最小值下标不是i，将最小值放到对应i位置上
            if(index != i){
                arr[index] = arr[i];
                arr[i] = min;
            }
//            System.out.println("第" + (i+1) + "轮排序后的数组：" + Arrays.toString(arr));
        }
    }
}
