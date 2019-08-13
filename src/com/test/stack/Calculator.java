package com.test.stack;

/**
 * Created by Mumuya on 2019/8/13
 * 使用栈完成表达式的计算思路
 * 1. 通过一个 index  值（索引），来遍历我们的表达式
 * 2. 如果我们发现是一个数字, 就直接入数栈
 *                 //1、当处理多位数时，不能只看一个数就入栈，有可能是多位数
 *                 //2、在处理数时，需要想表达式的index后再看一位，如果是数就继续扫描
 *                 //3、需要定义一个字符串变量，用于拼接多位数
 * 3. 如果发现扫描到是一个符号,  就分如下情况
 * 3.1 如果发现当前的符号栈为 空，就直接入栈
 * 3.2 如果符号栈有操作符，就进行比较,
 * 如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈，
 * 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
 * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
 * 5. 最后在数栈只有一个数字，
 *
 *
 * 15-2*3+1 有BUG！！！
 */
public class Calculator {
    public static void main(String[] args){
        //表达式
        String expression = "70+20*6-4";
        //创建两个栈，数栈，符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义相关变量
        int index = 0;    //用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';     //把每次扫描得到的char保存到ch
        String keepNum = "";     //用于拼接多位数
        //开始while循环扫描表达式expression
        while(true){
            //依次得到表达式的每一个字符
            ch = expression.substring(index, index+1).charAt(0);
            //判断ch是什么，然后做出对应处理
            //如果是运算符
            if(operStack.isOper(ch)){
                //判断当前的符号栈是否为空
                //不为空的话，符号栈里有符号
                if(!operStack.isEmpty()){
                    //符号栈有操作符，就进行比较
                    //如果当前的操作符的优先级小于或者等于栈中的操作符
                    //就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        //需要从数栈中pop出两个数
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        //将运算的结果入数栈
                        numStack.push(res);
                        //把当前的操作符入符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
                        operStack.push(ch);
                    }
                }else{
                    //如果符号栈为空，直接入符号栈
                    operStack.push(ch);
                }
            }else{
                //如果是数，直接入数栈
//                numStack.push(ch-48);      //转码
                //1、当处理多位数时，不能只看一个数就入栈，有可能是多位数
                //2、在处理数时，需要想表达式的index后再看一位，如果是数就继续扫描
                //3、需要定义一个字符串变量，用于拼接多位数
                //处理多位数
                keepNum += ch;

                //如果ch已经是表达式的最后一位，直接入栈
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，是数字的话，继续扫描，如果是运算符，就入栈
                    if(operStack.isOper(expression.substring(index+1, index+2).charAt(0))){
                        //如果后一位是运算符，则入栈
                        numStack.push(Integer.parseInt(keepNum));    //转换
                        //重要：清空keepNum!!!!
                        keepNum = "";
                    }
                }
            }
            //让index+1,并且判断是否扫描到了表达式的最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }
        //当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
        while(true){
            //如果符号算为空，则计算到最后的结果，数栈中只有一个数字，就是结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res);    //把最终结果入栈
        }
        System.out.printf("表达式 %s = %d",expression,numStack.peek());
    }
}

//先创建一个栈
class ArrayStack2{
    private int maxSize;    //栈的大小
    private int[] stack;    //数组，数组模拟栈，数据存在
    private int top = -1;   //top表示栈顶，初始化为-1

    //构造器
    public ArrayStack2(int maxSize){
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

    //返回当前栈顶的值，并不出栈pop
    public int peek(){
        //先判断栈是否是空
//        if(isEmpty()){
//            throw new RuntimeException("栈已空，没有数据");
//        }
        return stack[top];
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


    //返回运算符的优先级，优先级使用数字来表示
    //数字越大，优先级越高
    //假设目前表达式只有 + - * /
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+' || oper == '-'){
            return 0;
        }else{
            return -1;
        }
    }

    //判断是否是个运算符
    public boolean isOper(char val){
        return val == '+' || val =='-' || val =='*' || val =='/';
    }

    //计算方法
    public int cal(int num1, int num2, int oper){
        int res = 0;  //res用于存放计算的结果
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }
}
