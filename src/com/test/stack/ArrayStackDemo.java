package com.test.stack;

import java.util.Scanner;

/**
 * Created by Mumuya on 2019/8/13
 * 实现栈的思路分析
 * 1. 使用数组来模拟栈
 * 2. 定义一个 top  来表示栈顶，初始化 为  -1
 * 3. 入栈的操作，当有数据加入到栈时， top++;  stack[top] = data;
 * 4. 出栈的操作， int value = stack[top]; top--, return value
 */
public class ArrayStackDemo {
    public static void main(String[] args){
        //创建一个ArrayStack对象（表示栈）
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);

        System.out.println("show:表示显示栈");
        System.out.println("exit:退出程序");
        System.out.println("push:入栈（添加数据到栈）");
        System.out.println("pop:出栈（从栈中取出数据）");

        while(loop){
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数字");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~~");
    }
}

//定义一个ArrayStack表示栈
class ArrayStack{
    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数据存在
    private int top = -1;   //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //判断栈是否满
    public boolean isFull(){
        return top == maxSize -1;
    }

    //判断栈是否是空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 push
    public void push(int value){
        //判断栈是否满了
        if(isFull()){
            System.out.println("栈已满，无法添加");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈pop，将栈顶的数据返回
    public int pop(){
        //先判断栈是否是空
        if(isEmpty()){
            throw new RuntimeException("栈已空，没有数据");
        }
        int value = stack[top];
        System.out.println("出栈的数据是"+value);
        top--;
        return value;
    }

    //显示栈，遍历时，需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈已空，没有数据");
            return;
        }
        for(int i = top; i >= 0; i--){
            System.out.printf("stack[%d] = %d \n", i, stack[i]);
        }
    }
}
