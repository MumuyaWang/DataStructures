package com.test.linkedlist;

/**
 * Created by Mumuya on 2019/8/9
 * 使用带head头的双向链表实现 –水浒英雄排行榜
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args){
        System.out.println("单向链表的测试：");

        //创建节点
        HeroNode2 hero1 = new HeroNode2(1,"宋江","及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");

        //创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        //逐个加入节点
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);

        //显示
        doubleLinkedList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(2,"小卢啊~~","玉麒麟~~");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表：");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表：");
        doubleLinkedList.list();
    }
}

//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点，头节点不动，且不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead(){
        return head;
    }

    //遍历双向链表的方法
    //显示链表（遍历）
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //添加一个节点到双向链表的最后
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，需要一个辅助遍历temp
        HeroNode2 temp = head;
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
        //将新的节点的pre指向最后节点
        heroNode.pre =temp;
    }


    //修改节点的信息，根据no编号修改
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链接为空");
            return;
        }
        //找到需要修改的节点，设置一个辅助变量
        HeroNode2 temp = head.next;
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


    //删除双向链表的节点
    //1、对于双向链表，可以直接找到要删除的节点
    //2、找到后，自我删除就可以了
    public void del(int no){
        //判断是否为空
        if(head.next == null){
            System.out.println("链接为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;     //辅助节点指向了待删除节点
        //flag标记是否找到了待删除的节点
        boolean flag = false;
        while(true){
            if(temp == null){   //已经到链表的最后节点的next
                break;
            }
            //找到待删除的节点temp
            if(temp.no == no){
                flag = true;
                break;
            }
            //temp后移，遍历
            temp = temp.next;
        }
        //判断flag
        if(flag){
//            temp.next = temp.next.next;   单向链表的删除
            temp.pre.next = temp.next;
            //如果是最后一个节点，不需要执行下面这句话temp.next.pre = temp.pre;，否则会出现空指针
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的节点%d 不存在\n",no);
        }
    }
}



//定义一个HeroNode，每个对象就是一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;  //指向下一个节点
    public HeroNode2 pre;   //指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }
}

