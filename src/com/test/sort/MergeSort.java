package com.test.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 【归并排序】
 *
 * Created by Mumuya on 2019/9/4
 */
public class MergeSort {
    public static void main(String[] args){
        int[] arr = {9,8,7,6,5,4,3,2,1};
        int[] temp = new int[arr.length];
        System.out.println("排序前的数组：" + Arrays.toString(arr));
        mergeSort(arr, 0, arr.length -1, temp);
        System.out.println("排序后的数组：" + Arrays.toString(arr));

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
        mergeSort(arr2, 0, arr.length -1, temp);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("80000个随机数字排序前的时间是："+date2Str);

    }

    //分+合的方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //向左递归分解
            mergeSort(arr, left, mid, temp);
            //向右递归分解
            mergeSort(arr, mid+1, right, temp);
            //到合并
            merge(arr, left, mid, right, temp);
        }
    }

    //合并的方法
    /**
     *
     * @param arr 待排序的数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp){
        //初始化i，左边有序序列的初始索引
        int i= left;
        //初始化j，右边有序序列的初始索引
        int j = mid + 1;
        //指向temp数组的当前索引
        int t = 0;

        //(一)
        // 先把两边的有序数据按照规则填充到temp数组中
        //直到有一遍的先处理完毕为止
        while(i <= mid && j <= right){
            //如果左边序列的当前元素小于右边序列的当前元素
            //把左边的当前元素，填充到temp中
            //然后t++,i++
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else{
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //(二)
        //把剩余数据的一遍一次全部填充到temp中
        //如果左边序列有剩余，则把左边序列全部填充到temp
        while(i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }
        //如果右边序列有剩余，则把右边序列全部填充到temp
        while(j <= right){
            temp[j] = arr[j];
            t++;
            j++;
        }

        //（三）
        //把temp数组的值填充到arr中
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft += 1;
        }
    }
}
