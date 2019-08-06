package com.test.queue;

import java.util.Scanner;

/**
 * Created by Mumuya on  2019/8/6
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列");

        //创建一个环形的队列
        CircleArray queue = new CircleArray(4);    //队列的有效数据最大是3
        char key = ' ';     //接收用户的输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");

            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':            //显示队列
                    queue.showQueue();
                    break;
                case 'a':            //添加队列
                    System.out.println("输入一个要添加到队列的数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':            //从队列中取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':              //输出队列头数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
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


    //使用数组模拟循环队列
    //编写一个CircleQueue类
    static class CircleArray{
        private int maxSize;     //数组的最大容量
        private int front;       //队列头
        private int rear;        //队列尾
        private int[] arr;       //用于存放数据，模拟队列

        //创建队列的构造器
        public CircleArray(int arrMaxSize){
            maxSize = arrMaxSize;
            arr = new int[maxSize];
//            front = 0;     //指向队列的第一个元素，本身默认就位为0
//            rear = 0;      //rear 指向队列的最后一个元素，本身默认就位为0
        }

        //判断队列是否已满
        public boolean isFull() {
            return (rear + 1) % maxSize == front;
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
           //直接添加数据
            arr[rear] = n;
            //将rear后移rear++，这里要考虑取模，rear会变小
            rear = (rear + 1) % maxSize;
        }

        //获取队列的数据，出队列
        public int getQueue(){
            //判断队列是否已空
            if(isEmpty()){
//                System.out.println("队列已空，不能取数据");
                throw new RuntimeException("队列已空，不能取数据");
            }
            //front是指向队列的第一个元素
            //1、先将front对应的值保存到一个临时变量；
            //2、将front后移，要考虑取模
            //3、将临时保存的变量返回
            int value = arr[front];
            front = (front + 1) % maxSize;
            return value;
        }

        //展示队列的所有数据
        public void showQueue(){
            //遍历队列
            if(isEmpty()){
                System.out.println("队列是空的，没有数据");
                return;
            }
            //从front开始遍历，遍历多少个元素
            for(int i = front; i < front + size(); i++){
                System.out.printf("arr[%d] = %d \n", i % maxSize, arr[i % maxSize]);
            }
        }

        //求出当前队列有效数据的个数
        public int size(){
            return (rear + maxSize - front) % maxSize;
        }

        //显示队列的头数据
        public int headQueue(){
            if(isEmpty()){
                throw new RuntimeException("队列已空，没有数据");
            }
            return arr[front];
        }
    }

}
