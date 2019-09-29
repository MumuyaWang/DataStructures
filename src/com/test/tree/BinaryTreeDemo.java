package com.test.tree;

/**
 * 二叉树
 * 1、前序、中序、后续遍历
 * 2、前序、中序、后续遍历查找
 *
 * Created by Mumuya on 2019/9/29
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一个二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的节点
        HeroNode root = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);
        node3.setLeft(node5);

        //测试前序遍历
        System.out.println("前序遍历：");
        binaryTree.preOrder();

        //测试中序遍历
        System.out.println("中序遍历：");
        binaryTree.infixOrder();

        //测试后序遍历
        System.out.println("后序遍历：");
        binaryTree.postOrder();

        //要查找的id
        int find = 5;

        //测试前序遍历
        //前序遍历的次数
        System.out.println("前序遍历查找：");
        HeroNode resNode = binaryTree.proOrderSearch(find);
        if(resNode != null){
            System.out.println("找到了，id = "+ resNode.getId() + ", name = " + resNode.getName());
        }else{
            System.out.println("没有找到 id = " + find + "的英雄");
        }

        //测试中序遍历
        //中序遍历的次数
        System.out.println("中序遍历查找：");
        HeroNode resNode2 = binaryTree.infixOrderSearch(find);
        if(resNode != null){
            System.out.println("找到了，id = "+ resNode2.getId() + ", name = " + resNode2.getName());
        }else{
            System.out.println("没有找到 id = " + find + "的英雄");
        }

        //测试后序遍历
        //后序遍历的次数
        System.out.println("后序遍历查找：");
        HeroNode resNode3 = binaryTree.postOrderSearch(find);
        if(resNode != null){
            System.out.println("找到了，id = "+ resNode3.getId() + ", name = " + resNode3.getName());
        }else{
            System.out.println("没有找到 id = " + find + "的英雄");
        }
    }
}

//定义一个二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root){
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root != null){
            this.root.perOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode proOrderSearch(int id){
        if(root != null){
            return root.preOrderSearch(id);
        }else{
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int id){
        if(root != null){
            return root.infixOrderSearch(id);
        }else{
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int id){
        if(root != null){
            return root.postOrderSearch(id);
        }else{
            return null;
        }
    }
}


//先创建HeroNode结点
class HeroNode{
    private int id;
    private String name;
    private HeroNode left;   //默认为null
    private HeroNode right;  //默认为空
    //快速生成构造器     右键Generate--Constructor
    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //快速生成方法    右键Generate--Getter and Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    ////快速生成方法    右键Generate--toString
    @Override
    public String toString() {
        return "HeroNode{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    //前序遍历的方法
    public void perOrder(){
        //先输出父节点
        System.out.println(this);
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.perOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.perOrder();
        }
    }

    //中序遍历的方法
    public void infixOrder(){
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }
    //后续遍历的方法
    public void postOrder(){
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.postOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找

    /**
     * 前序遍历查找
     * @param id  要查找的编号
     * @return  如果找到就返回该node,如果没找到就返回null
     */
    public HeroNode preOrderSearch(int id){
        System.out.println("进入前序遍历查找~~~~");
        //比较当前节点是否是要查找的那个
        if(this.id == id){
            return this;
        }

        //1、判断当前节点的左子节点是否为空，如果不为空则递归前序查找
        //2、如果左递归前序找到，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(id);
        }
        if(resNode != null){
            //说明左子树找到
            return resNode;
        }

        //1、左递归前序查找，找到节点则返回，如果没找到则继续判断
        //2、当前节点的右子节点是否为空，如果不为空，则继续向右递归前序查找
        if(this.right != null){
            resNode = this.right.preOrderSearch(id);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int id){

        //1、判断当前节点的左子节点是否为空，如果不为空则递归中序查找
        //2、如果左递归前序找到，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(id);
        }
        if(resNode != null){
            //说明左子树找到
            return resNode;
        }

        System.out.println("进入中序遍历查找~~~~");


        //如果没有找到，就和当前节点比较，是否是要查找的那个。如果是就返回
        if(this.id == id){
            return this;
        }

        //否的话，则继续判断
        //当前节点的右子节点是否为空，如果不为空，则继续向右递归中序查找
        if(this.right != null){
            resNode = this.right.infixOrderSearch(id);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int id){

        //1、判断当前节点的左子节点是否为空，如果不为空则递归中序查找
        //2、如果左递归前序找到，则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(id);
        }
        if(resNode != null){
            //说明左子树找到
            return resNode;
        }

        //如果左子树没有找到，则继续向右递归后序查找
        if(this.right != null){
            resNode = this.right.postOrderSearch(id);
        }
        if(resNode != null){
            //说明右子树找到
            return resNode;
        }

        System.out.println("进入后序遍历查找~~~~");


        //如果左右子树都没有找到，就和当前节点比较，是否是要查找的那个。如果是就返回
        if(this.id == id){
            return this;
        }
        return resNode;
    }
}

