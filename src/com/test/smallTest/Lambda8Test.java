package com.test.smallTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mumuya on 2020/1/10
 */
public class Lambda8Test {

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a, b);
    }

    public static void main(String args[]){
//        Lambda8Test tester = new Lambda8Test();
//
//        // 类型声明
//        // 接收2个int型整数,返回他们的和
//        MathOperation addition = (int a, int b) -> a + b;
//
//        // 不用类型声明
//        // 接受2个参数(数字),并返回他们的差
//        MathOperation subtraction = (a, b) -> a - b;
//
//        // 大括号{}中的返回语句return
//        // 接收2个int型整数,返回他们的乘积
//        MathOperation multiplication = (int a, int b) -> { return a * b; };
//
//        // 没有大括号及返回语句
//        // 接收2个int型整数,返回a/b
//        MathOperation division = (int a, int b) -> a / b;
//
//        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
//        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
//        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
//        System.out.println("10 / 5 = " + tester.operate(10, 5, division));
//
//        // 不用括号(一个参数无需定义圆括号)
//        GreetingService greetService1 = message -> System.out.println("Hello " + message);
//
//        // 用括号
//        GreetingService greetService2 = (message) -> System.out.println("Hello " + message);
//
//        greetService1.sayMessage("Runoob");
//        greetService2.sayMessage("Google");



        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
