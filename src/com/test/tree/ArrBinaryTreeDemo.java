package com.test.tree;

/**
 * 一个数组 {1,2,3,4,5,6,7}，要求以二叉树前序遍历的方式进行遍历。 前序遍历的结果应当为 1,2,4,5,3,6,7
 * 1.顺序二叉树通常只考虑完全二叉树
 * 2.第n个元素的左子节点为  第（2 * n + 1）个元素
 * 3.第n个元素的右子节点为  2 * n + 2
 * 4.第n个元素的父节点为  (n-1) / 2
 * n : 表示二叉树中的第几个元素(按0开始编号如图所示)
 * Created by Mumuya on  2019/10/13
 */
public class ArrBinaryTreeDemo {
    public static void main(String[] args){
        int[] arr = { 1, 2, 3, 4, 5, 6, 7};
        //创建一个ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

//编写一个ArrayBinaryTree，实现顺序存储二叉树遍历
class ArrBinaryTree{
    //存储数据节点的数组
    private int[] arr;

    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }

    //编写一个方法，完成顺序存储二叉树的前序遍历
    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){
        //如果数组为空，或者arr.length=0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向递归遍历
        if((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }

    }
}

