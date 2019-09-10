package com.test.search;

import java.util.ArrayList;

/**
 * 二分查找法
 * 使用前提：该数组是有序的
 * Created by Mumuya on 2019/9/10
 */
public class binarySearch {
    public static void main(String[] args){
        int arr[] = {1,8, 10, 89, 1000, 1000, 1000, 1234};
        //二分查找
//        int resIndex = binarySearch(arr, 0, arr.length -1 ,88);
//        System.out.println("resIndex = " + resIndex);
        //优化后的二分查找
        ArrayList<Integer> resIndex = binarySearch2(arr, 0, arr.length -1 ,1000);
        System.out.println("resIndex = " + resIndex);
    }

    /**
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要找到的值
     * @return 如果找到就返回下标，没找到就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){
        // 当 left > right，说明递归了整个数组，但是没找到。这时就需要退出
        if(left > right){
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(midVal < findVal){
            //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        }else if(midVal > findVal){
            //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
    }




    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        // 当 left > right，说明递归了整个数组，但是没找到。这时就需要退出
        if(left > right){
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(midVal < findVal){
            //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        }else if(midVal > findVal){
            //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        }else{
            //思路分析：
            //1、在找到mid索引值，不要马上返回
            //2、向mid索引值的左边扫描，将所有满足1000的元素的下标，加入到ArrayList
            //3、向mid索引值右边扫描，将所有满足1000的元素的下标，加入到ArrayList
            //4、将ArrayList返回

            ArrayList<Integer> resIndexList = new ArrayList<Integer>();
            //向mid索引值的左边扫描，将所有满足1000的元素下标都加入到集合中
            int temp = mid - 1;
            while(true){
                if(temp < 0 || arr[temp] != findVal){
                    //退出
                    break;
                }
                //否则就把temp放到resIndexList中去
                resIndexList.add(temp);
                //temp左移
                temp -= 1;
            }
            resIndexList.add(mid);

            //向mid索引值的右边扫描，将所有满足1000的元素下标都加入到集合中
            temp = mid + 1;
            while(true){
                if(temp > arr.length -1 || arr[temp] != findVal){
                    //退出
                    break;
                }
                //否则就把temp放到resIndexList中去
                resIndexList.add(temp);
                //temp右移
                temp += 1;
            }

            return resIndexList;
        }
    }

    //思考题： {1,8, 10, 89, 1000, 1000，1234}
    // 当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
    //思路分析：
    //1、在找到mid索引值，不要马上返回
    //2、向mid索引值的左边扫描，将所有满足1000的元素的下标，加入到ArrayList
    //3、向mid索引值右边扫描，将所有满足1000的元素的下标，加入到ArrayList
    //4、将ArrayList返回

}
