package com.test.sparsearray;

/**
 * Created by Mumuya on 2019/8/1
 */
public class SparseArray {
    public static void main(String[] args){
        //创建一个原始的二维数据11*11
        //0代表没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("输出原始的二维数组：");
        //增强for循环
//        for(数据类型变量名 :被遍历的集合（collection）或者数组) {
//            执行语句
//        }
        for(int[] row : chessArr1){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组 转为 稀疏数组
        //1、先遍历二维数组，得到非0的数据个数
        int sum =0;
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }

        //2、创建对应的稀疏数组
        int sparseArr[][] =  new int[sum+1][3];
        //赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //遍历二维数组，将非0数据放到sparseArr中
        //count用来记录是第几个非0值
        int count = 0;
        for(int i = 0; i < 11; i++){
            for(int j = 0; j < 11; j++){
                if(chessArr1[i][j] != 0){
                    count ++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        System.out.println();

        //输出稀疏数组
        System.out.println("输出稀疏数组为：");
        for(int[] row : sparseArr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        System.out.println();

        //将稀疏数组 恢复 为二维数组
        //1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2、读取稀疏数组的第二行开始的值，赋值给原始的二维数组
        for(int i = 1; i < sparseArr.length; i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println("恢复后的二维数组是：");
        for(int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
}
