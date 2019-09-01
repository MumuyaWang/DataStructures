package com.test.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序（Insertion Sorting）
 * 把n个待排序的元素看成为一个有序表和一个无序表，
 * 开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 * 排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，
 * 将它插入到有序表中的适当位置，使之成为新的有序表。
 *
 * Created by Mumuya on  2019/9/2
 */
public class InsertSort {
    public static void main(String[] args){
        int[] arr = {101, 34, 119, 1, -1, 89};
        System.out.println("排序前的数组：" + Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后的数组：" + Arrays.toString(arr));

        //测试80000个随机数的排序
        int[] arr2 = new int[80000];
        for(int i = 0; i < 80000; i++){
            arr2[i] = (int)(Math.random()*8000000);
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("80000个随机数字排序前的时间是："+date1Str);
        insertSort(arr2);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("80000个随机数字排序前的时间是："+date2Str);

    }

    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;

        for(int i = 1; i < arr.length; i++){
            //定义待插入的数据
            insertVal = arr[i];
            //要插入的数之前的下标
            insertIndex = i - 1;

            //给inserIndex找到插入的位置
            //1、inserIndex >= 0 保证给inserVal找插入位置，不越界
            //2、inserVal < arr[inserIndex] 待插入的数，还没有找到插入位置
            //3、就需要把arr[inserIndex]后移
            while(insertIndex >=0 && insertVal <arr[insertIndex]){
                arr[insertIndex+1] = arr[insertIndex];
                insertIndex --;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex+1
            //判断是否需要赋值
            if(insertIndex + 1 != i ){
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第" + i + "轮的插入结果是：" + Arrays.toString(arr));
        }
    }
}
