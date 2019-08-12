package com.test.linkedlist;

/**
 * Created by Mumuya on  2019/8/12
 * Josephu问题为：设编号为1，2，… n的n个人围坐一圈，
 * 约定编号为k（1<=k<=n）的人从1开始报数，数到m 的那个人出列，
 * 它的下一位又从1开始报数，数到m的那个人又出列，
 * 依次类推，直到所有人出列为止，由此产生一个出队编号的序列
 */
public class Josephu {
    public static void main(String[] args){
        //构建环形链表，遍历
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList() ;
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1,2,5);
    }
}

//创建环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = new Boy(-1);
    //添加小孩节点，构成一个环形的链表
    public void addBoy(int nums){
        if(nums < 1){
            System.out.println("nums值不正确");
            return;
        }
        //辅助指针，帮助构建环形链表
        Boy curBoy = null;
        //创建环形链表
        for(int i = 1; i <= nums; i++){
            //根据编号，创建小孩的节点
            Boy boy = new Boy(i);
            //如果这是第一个小孩
            if(i == 1){
                first = boy ;
                //构成1个人的环
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("链表为空，没有小孩");
            return;
        }
        //因为first不能动，因此仍需要一个辅助指针来完成遍历
        Boy curBoy = first;
        while(true){
            System.out.printf("小孩的编号是%d \n",curBoy.getNo());
            //如果遍历完毕后
            if(curBoy.getNext() ==  first){
                break;
            }
            curBoy = curBoy.getNext(); //curBoy后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序

    /**
     *
     * @param starNo 表示从第几个开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少小孩在圈中
     */
    public void countBoy(int starNo, int countNum, int nums){
        //数据校验
        if(first == null || starNo < 1 || starNo > nums){
            System.out.println("输入参数有误，重新输入");
            return;
        }
        //创建辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //1、需要创建一个辅助指针（变量）helper,事先应该指向环形链表的最后的节点
        while(true){
            //如果helper已经指向最后一个小孩,跳出循环
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //2、小孩报数前，先让first和helper都移动k-1次（first移到第一个开始数数的小孩）
        for(int j = 0; j <starNo - 1 ; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //3、当小孩报数时，让first 和 helper 指针同时 的移动  m  - 1 次
        //循环操作，直到圈里只有一个节点
        while(true){
            //圈里只有一个节点
            if(helper == first){
                break;
            }
            //让first 和 helper 同时移动countNum-1，模拟报数的移动
            for(int j = 0; j < countNum -1; j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //first指针指向的节点，就是要出圈的小孩节点
            System.out.printf("要出圈的小孩序号是%d \n",first.getNo());
            //
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号是%d \n",first.getNo());
    }
}


//创建一个Boy类，表示一个节点
class Boy{
    private int no; //编号
    private Boy next;   //指向下一个节点，默认为null
    public Boy(int no){
        this.no = no;
    }
    public int getNo(){
        return no;
    }
    public void setNo(int no){
        this.no = no;
    }
    public Boy getNext(){
        return next;
    }
    public void setNext(Boy next){
        this.next = next;
    }
}
