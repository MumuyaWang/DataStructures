package com.test.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by Mumuya on 2019/8/20
 * 【后缀表达式的计算机求值】
 * 从左至右扫描表达式，遇到数字时，将数字压入堆栈，遇到运算符时，弹出栈顶的两个数，用运算符对它们做相应的计算（次顶元素 和 栈顶元素），并将结果入栈；重复上述过程直到表达式最右端，最后运算得出的值即为表达式的结果。
 * 例如: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
 * 1、从左至右扫描，将3和4压入堆栈；
 * 2、遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
 * 4、将5入栈；
 * 5、接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
 * 6、将6入栈；
 * 7、最后是-运算符，计算出35-6的值，即29，由此得出最终结果
 */
public class PolandNotation {

    public static void main(String[] args){
        //将中缀表达式转为后缀表达式
        //1、对一个字符串中缀表达式“1+((2+3)×4)-5”进行操作不方便，先把中缀的表达式先放到list中
        //   1+((2+3)×4)-5   ==>    ArrayList [1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]
        //2、将得到的中缀表达式对应的list==>后缀表达式对应的List
        //   ArrayList [1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]   ==>  ArrayList [1,2,3,+,4,*,+5,-]

        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式ArrayList为：" + infixExpressionList);
        //        //将中缀表达式转为后缀表达式
        List<String> suffixExpressionList = paraSuffixExpressionList(infixExpressionList);
        System.out.println("后缀表达式对应的List为" + suffixExpressionList);
        System.out.printf("expression = %d  \n",calculate(suffixExpressionList));


        //先定义逆波兰表达式
        //  （30+4）×5-6   为了方便，逆波兰表达式的数字和符号间用空格分开
        String suffixExpression = "30 4 + 5 * 6 -";
        //思路：
        //1、先将表达式放到ArraryList中
        //2、将ArraryList传递给一个方法，遍历ArraryList配合栈完成计算
        List<String>  list = getString(suffixExpression);
        System.out.println("逆波兰表达式是：" + list);

        int res = calculate(list);
        System.out.println("计算的结果是：" + res);
    }


    //将得到的中缀表达式对应的list==>后缀表达式对应的List
    //   ArrayList [1, +, (, (, 2, +, 3, ), ×, 4, ), -, 5]   ==>  ArrayList [1,2,3,+,4,*,+5,-]
    public static List<String > paraSuffixExpressionList(List<String> ls){
        //定义两个栈
        //s1符号栈
        Stack<String> s1 = new Stack<String>();
        //s2存储中间结果     由于s2在整个转换过程中没有pop操作，而且后面还要逆序输出，所以不用Stack,直接用List
        List<String> s2 = new ArrayList<>();

        //遍历ls
        for(String item:ls){
            //如果是一个数，加入s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();     //将 （ 弹出s1栈，消除小括号
            }else{
                //当item的优先级<=s1栈顶的运算符，将s1栈顶的运算符弹出并压入到s2中，再次转到(4-1)与s1中新的栈顶运算符相比较；
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while(s1.size()!=0){
            s2.add(s1.pop());
        }
        return  s2;   //存放到list,因此按顺序输出就是对应的逆波兰表达式
    }






    //中缀表达式转化转成对应的list
    public static List<String> toInfixExpressionList(String s){
        //定义一个list，存放中缀表达式对应内容
        List<String> ls = new ArrayList<String>();
        //i 是一个指针，用于遍历中缀表达式字符串
        int i = 0;
        String str;     //对多位数的拼接
        char c;         //每遍历到一个字符，放到c
        do{
            //如果c是一个非数字，需要加入到ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else{//考虑多位数
                str = "";      //先将str置为""      0[48]-->9[57]
                while(i<s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                    str += c;     //拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());{
            return ls;
        }
    }



    //将逆波兰表达式，依次把数据和运算符放到ArraryList中
    public static List<String> getString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele:split){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的计算
    /**
     * 从左至右扫描，将3和4压入堆栈；
     * 遇到+运算符，因此弹出4和3（4为栈顶元素，3为次顶元素），计算出3+4的值，得7，再将7入栈；
     * 将5入栈；
     * 接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈；
     * 将6入栈；
     * 最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> ls){
        //创建栈，只需要一个栈就可以
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for(String item:ls){
            //使用正则表达式取数???
            //匹配的是多位数
            if(item.matches("\\d+")){
                //入栈
                stack.push(item);
            }else{
                //pop出两个数，并计算，再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }else if(item.equals("-")){
                    res = num1 - num2;
                }else if(item.equals("*")){
                    res = num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push("" + res);
            }
        }
        //最终留在stack中的数据是运算结果
        return Integer.parseInt(stack.pop());
    }
}


//Operation 返回一个运算符对应的优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 3;

    //写一个方法返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;

            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}
