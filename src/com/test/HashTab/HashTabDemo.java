package com.test.HashTab;

import java.util.Scanner;

/**
 * 哈希表
 * 有一个公司,当有新的员工来报道时,要求将该员工的信息加入(id,性别,年龄,名字,住址..),
 * 当输入该员工的id时,要求查找到该员工的 所有信息。
 * Created by Mumuya on  2019/9/28
 */
public class HashTabDemo {

    public static void main(String[] args){
        //创建一个哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单的菜单
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("add: 添加雇员");
            System.out.println("list: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");

            key = scanner.next();
            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case"list":
                    hashTab.list();
                    break;
                case"find":
                    System.out.println("输入要查找的id");
                    id = scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}
//创建哈希表，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示有多少条链表

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化empLinkedListArray
        empLinkedListArray = new EmpLinkedList[size];
        //需要初始化每个链表
        for(int i = 0; i < size; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应当添加到哪个链表中
        int empLinkedListNO = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNO].add(emp);
    }

    //遍历所有的链表
    public void list(){
        for(int i = 0; i < size; i++){
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列函数确定要查找哪个链表
        int empLinkedListNO = hashFun(id);
        Emp emp =empLinkedListArray[empLinkedListNO].findEmpById(id);
        //如果找到了
        if(emp != null){
            System.out.println("在第" + empLinkedListNO + "条链表中找到了该雇员id = " + id);

        }else{
            //如果没找到
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }
}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;    //默认为null
    //构造器
    public Emp(int id, String name){
        super();
        this.id = id;
        this.name = name;
    }
}

//创建EmpLinkedList，表示链表
class EmpLinkedList{
    //头指针，执行第一个Emp，因此这个链表的head是直接指向第一个Emp
    private Emp head;   //默认为null

    //添加雇员到链表
    //1、当添加员工时，id自增长    因此将该雇员直接加入到本链表的最后
    public void add(Emp emp){
        //如果添加的是第一个雇员
        if(head == null){
            head = emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp = head;
        while(true){
            //当到链表最后了
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;    //后移
        }
        //退出时，直接将emp加到链表最后
        curEmp.next =emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        //链表为空
        if(head == null){
            System.out.println("第" + no +"条链表为空");
            return;
        }
        System.out.println("第" + no +"条链表信息为：");
        Emp curEmp = head;//辅助指针
        while(true){
            System.out.printf("=> id = %d, name = %s \n", curEmp.id, curEmp.name);
            //说明curEmp已经是最后的结点
            if(curEmp.next == null){
                break;
            }
            curEmp = curEmp.next;  //后移
        }
    }

    //根据i的查找雇员
    //如果查找到，就返回emp,如果没有找到，就返回null
    public Emp findEmpById(int id){
        //判断链表是否为空
        if(head == null){
            System.out.println("链表为空");
            return null;
        }
        //辅助指针
        Emp curEmp = head;
        while(true){
            //如果找到,这是curEmp就指向要找到的雇员
            if(curEmp.id == id){
                break;
            }
            //退出
            //如果遍历当前链表都没有找到该雇员
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;//后移
        }
        return curEmp;
    }
}
