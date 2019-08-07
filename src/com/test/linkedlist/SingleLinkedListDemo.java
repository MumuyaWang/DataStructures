package com.test.linkedlist;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;

/**
 * Created by Mumuya on  2019/8/6
 * 使用带head头的单向链表实现 –水浒英雄排行榜管理
 * 1、完成对英雄人物的增删改查操作， 注: 删除和修改,查找可以考虑学员独立完成，也可带学员完成
 * 2、第一种方法在添加英雄时，直接添加到链表的尾部
 * 3、第二种方式在添加英雄时，根据排名将英雄插入到指定位置(如果有这个排名，则添加失败，并给出提示)
 */
public class SingleLinkedListDemo {
    public static void main(String[] args){
        //创建节点
        HeroNode hero1 = new HeroNode(1,"宋江","及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");

        //创建一个链表
        SingLinkedList singLinkedList = new SingLinkedList();
        //逐个加入节点
        singLinkedList.add(hero1);
        singLinkedList.add(hero3);
//        singLinkedList.add(hero3);

        //按照编号的顺序加入节点
        singLinkedList.addByOrder(hero3);
        singLinkedList.addByOrder(hero1);
        singLinkedList.addByOrder(hero2);
        //显示
        singLinkedList.list();

        //修改节点
        HeroNode newHeroNode = new HeroNode(2,"小卢啊","玉麒麟~~");
        singLinkedList.update(newHeroNode);
        HeroNode newHeroNode2 = new HeroNode(5,"你是谁","哈哈哈");
        singLinkedList.update(newHeroNode2);
        //显示
        singLinkedList.list();

        //删除节点
        singLinkedList.del(6);
        singLinkedList.del(3);
        //显示
        singLinkedList.list();

        //显示单链表的有效长度
        System.out.println("该单链表的有效长度为："+getLength(singLinkedList.getHead()));
    }

    public static int getLength(HeroNode head){
        if(head.next == null){
            //空链表时，长度为0
            return 0;
        }
        int length =0;
        //定义一个辅助变量temp
        HeroNode temp = head.next;
        while(temp != null){
            length++;
            temp = temp.next;
        }
        return length;
    }
}

//定义SingleLinkedList管理英雄
class SingLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head = new HeroNode(0,"","");

    //返回头节点
    public HeroNode getHead(){
        return head;
    }

    //添加节点到单向链表
    //第一种方法在添加英雄时，直接添加到链表的尾部
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

    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    // (如果有这个排名，则添加失败，并给出提示)
    public void addByOrder(HeroNode heroNode){
        //头节点不能动，需要通过一个辅助的变量（指针）来帮助找到添加的位置
        //单链表，找的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        //flag标记添加的编号是否存在，默认为false
        boolean flag = false;
        while(true){
            //temp是否已经是链表的最后
            if(temp.next == null){
                break;
            }
            //找到位置，就是temp后面
            if(temp.next.no > heroNode.no){
                break;
            }else if(temp.next.no == heroNode.no){
                //说明想要添加的heroNode的编号已经存在
                flag = true;
                break;
            }
            //后移temp，遍历当前的链表
            temp = temp.next;
        }
        //判断flag的值
        //flag=true,说明不能添加，该编号已存在
        if(flag){
            System.out.printf("想要插入的英雄编号%d 已经存在，不能加入\n",heroNode.no);
        }else{
            //flag=flase,把数据插入到链表中，放在temp后面
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }


    //修改节点的信息，根据no编号修改
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链接为空");
            return;
        }
        //找到需要修改的节点，设置一个辅助变量
        HeroNode temp = head.next;
        //flag标记是否找到
        boolean flag= false;
        while(true){
            if(temp == null){
                break;    //已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到了要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            //编号等于这个值得节点不存在
            System.out.printf("没有找到编号为%d 的节点，修改失败\n",newHeroNode.no);
        }
    }

    //删除节点
    //1、head不能动，需要temp辅助节点来找到待删除节点的前一个节点
    //2、temp.next.no和需要删除的节点.no比较
    public void del(int no){
        HeroNode temp = head;
        //flag标记是否找到了待删除的节点
        boolean flag = false;
        while(true){
            if(temp.next == null){
                break;
            }
            //找到待删除的节点的前一个节点temp
            if(temp.next.no == no){
                flag = true;
                break;
            }
            //temp后移，遍历
            temp = temp.next;
        }
        //判断flag
        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("要删除的节点%d 不存在\n",no);
        }
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
