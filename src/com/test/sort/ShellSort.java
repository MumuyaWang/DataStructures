package com.test.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 【希尔排序】
 * 是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；
 * 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
 *
 * Created by Mumuya on  2019/9/2
 */
public class ShellSort {
    public static void main(String[] args){
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        System.out.println("排序前的数组：" + Arrays.toString(arr));
        shellSort(arr);
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
        //移位法
        shellSort2(arr2);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("80000个随机数字排序前的时间是："+date2Str);

    }

    //对有序序列在插入时采用交换法
    public static void shellSort(int[] arr){
        int temp = 0;
        int count = 0;
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            count++;
            for(int i = gap; i < arr.length; i++){
                //遍历各组中所有的元素（共gap组，每组 个元素）步长gap
                for(int j = i - gap; j >= 0; j -= gap){
                    //如果当前元素大于加上步长后的那个元素，则交换
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("第" + count + "轮排序后的数组：" + Arrays.toString(arr));
        }
    }

    //对交换式的希尔排序进行优化--》移位法
    public static void shellSort2(int[] arr){
        //增加gap，并逐步缩小增量
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for(int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j - gap]){
                    while(j - gap >= 0 &&  temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
//            System.out.println("第" + count + "轮排序后的数组：" + Arrays.toString(arr));
        }

    }
}
