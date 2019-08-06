package com.test.queue;

import java.util.Scanner;

/**
 * Created by Mumuya on 2019/8/2
 */
public class ArrayQueueDemo {

    public static void main(String[] args){
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';     //接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");

            //接收一个字符
            key = scanner.next().charAt(0);
            switch(key){
                case 's':            //显示队列
                    queue.showQueue();
                    break;
                case 'a':            //添加队列
                    System.out.println("输入一个要添加到队列的数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':            //从队列中取出数据
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':              //输出队列头数据
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':           //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }

    //使用数组模拟队列
    //编写一个ArrayQueue类
    static class ArrayQueue{
        private int maxSize;     //数组的最大容量
        private int front;       //队列头
        private int rear;        //队列尾
        private int[] arr;       //用于存放数据，模拟队列

        //创建队列的构造器
        public ArrayQueue(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];
            front = -1;     //指向队列头部，front指向队列头的前一个位置
            rear = -1;      //指向队列尾，rear指向队列尾的数据（队列的最后一个数据）
        }

        //判断队列是否已满
        public boolean isFull() {
            return rear == maxSize - 1;
        }

        //判断队列是否为空
        public boolean isEmpty(){
            return rear == front;
        }

        //添加数据到队列
        public void addQueue(int n){
            //判断队列是否已满
            if(isFull()){
                System.out.println("队列已满，不能加入数据");
                return;
            }
            rear++;      //rear 后移
            arr[rear] = n;
        }

        //获取队列的数据，出队列
        public int getQueue(){
            //判断队列是否已空
            if(isEmpty()){
//                System.out.println("队列已空，不能取数据");
                throw new RuntimeException("队列已空，不能取数据");
            }
            front++;     //队列头后移
            return arr[front];
        }

        //展示队列的所有数据
        public void showQueue(){
            //遍历队列
            if(isEmpty()){
                System.out.println("队列是空的，没有数据");
                return;
            }
            for(int i = 0; i < arr.length; i++){
                System.out.printf("arr[%d] = %d \n", i, arr[i]);
            }
        }

        //显示队列的头数据
        public int headQueue(){
            if(isEmpty()){
                throw new RuntimeException("队列已空，没有数据");
            }
            return arr[front+1];
        }
    }
}

