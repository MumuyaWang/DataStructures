package com.test.linkedlist;

/**
 * Created by Mumuya on  2019/8/6
 */
public class SingleLinkedListDemo {
    public static void main(String[] args){
        //创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"误用","智多星");

        //创建一个链表
        SingLinkedList singLinkedList = new SingLinkedList();
        //加入节点
        singLinkedList.add(hero1);
        singLinkedList.add(hero2);
        singLinkedList.add(hero3);
        //显示
        singLinkedList.list();
    }

}

//定义SingleLinkedList管理英雄
class SingLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1、找到当前链表的最后节点
    //2、将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，需要一个辅助遍历temp
        HeroNode temp = head;
        //遍历链表，找到最后一个节点
        while(true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }

    //显示链表（遍历）
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }
    }


}



//定义一个HeroNode，每个对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;  //指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方法，重新显示toString. 调用了next的toString方法
    @Override
    public String toString(){
        return "HeroNode [ no = " + no + ", name = " + name + ",nickname = " + nickname + "]";
    }
}
