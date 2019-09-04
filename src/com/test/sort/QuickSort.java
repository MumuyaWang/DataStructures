package com.test.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 【快速排序】
 * 通过一趟排序将要排序的数据分割成独立的两部分，
 * 其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，
 * 整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * Created by Mumuya on 2019/9/4
 */
public class QuickSort {
    public static void main(String[] args){
        int[] arr ={-9, 78, 0, 23, -567, 70};
        System.out.println("排序前的数组:" + Arrays.toString(arr));
        quickSort(arr, 0, arr.length-1);
        System.out.println("排序后的数组:" + Arrays.toString(arr));

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
        quickSort(arr2, 0 , arr2.length - 1);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("80000个随机数字排序前的时间是："+date2Str);
    }

    public static void quickSort(int[] arr, int left, int right){
        //左下标
        int l = left;
        //右下标
        int r = right;
        //中轴值
        int pivot = arr[(left + right)/ 2];
        //交换的临时变量
        int temp = 0;
        //while循环：让比pivot小的值放到左边，比pivot大的值放到右边
        while(l < r){
            //在pivot左边一直找，直到找到大于等于pivot的值，才退出
            while(arr[l] < pivot){
                l += 1;
            }
            //在pivot右边一直找，直到找小于等于pivot的值，才退出
            while(arr[r] > pivot){
                r -= 1;
            }
            //如果l >= r 说明pivot两边的值已经是左边都是小于pivot,右边都是大于pivot的了
            if(l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换后，发现这个arr[l]==pivot,r--前移；
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果交换后，发现这个arr[r]==pivot,l++后移；
            if(arr[r] == pivot){
                l += 1;
            }
        }
        //如果l==r，必须l++、r--,否则出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }
        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }

        //向右递归
        if(right > l){
            quickSort(arr, l, right);
        }
    }
}
