package com.example.knowledge.algorithms;

/**
 * @program: knowledge
 * @description: 回溯算法
 * @author: zhangjialin
 * @create: 2020-09-21 14:21
 */
public class Backtracking {
    /**
     * 八皇后问题
     * 下标表示行，值标识每行的queen存储在那一列
     */
    public static int[] result = new int[8];

    /**
     * 八皇后问题
     * 计算在每行存储的列
     * @param row 行数，从0开始
     */
    public static void cal8Queens(int row){
        if(row == 8){
            //八个棋子都放好了，打印并退出递归
            printQueens(result);
            return;
        }
        //每一行中有八种放的方式
        for (int column = 0; column < 8; column++) {
            if(isOk(row,column)){
                result[row] = column;
                System.out.println("row:"+row+"****column:"+column);
                cal8Queens(row+1);
            }
        }
    }

    /**
     * 校验这个位置是否满足放置的条件
     * @param row 行数
     * @param column 列数
     * @return
     */
    private static  boolean isOk(int row, int column) {
        int leftUp = column-1,rightUp = column+1;
        for (int i = row-1; i >=0 ; i--) {
            if (result[i]== column){
                //第i行的column列已经有棋子了，相同列不能放置棋子
                return false;
            }
            if (leftUp>=0 && result[i] == leftUp){
                //校验左上对角线的 第i行leftUp列是否有棋子
                return false;
            }

            if (rightUp<8 && result[i] == rightUp){
                //校验右上对角线的 第i行rightUp列是否有棋子
                return false;
            }
            --leftUp;
            ++rightUp;
        }
        return true;
    }

    /**
     * 打印出一个二维矩阵
     * @param result
     */
    private static  void printQueens(int[] result) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (result[i] == j){
                    System.out.printf("Q ");
                }else{
                    System.out.printf("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 结果放到maxW中
     */
    private static int maxW = Integer.MIN_VALUE;
    /**
     * 物品重量
     */
    private static int [] weight = new int[]{2,2,4,6,3};
    /**
     * 物品数量
     */
    private static int n = 5;
    /**
     * 背包总重量
     */
    private static int w = 9;

    /**
     * 记录算过的数据，默认为false
     */
    private static  boolean[][] mem = new boolean[5][10];
    /**
     * 使用回溯算法解决0-1背包的问题
     * 0-1背包问题：
     *   现在有一组不同重量，不可分割的物品，需要选择一些装入背包中，在满足背包最大重量限制的前提下，背包中物品的总重量的最大值是多少呢？
     *   例：
     *      现在有5个物品，重量分别是2,2,4,6,3    背包所能容纳的最大重量是9  求背包所能装下物品的最大值是多少
     * @param i 表示现在装进的第几个物品
     * @param cw 表示装进第i个物品之前背包中已经有的重量
     */
    public static void  calculate(int i ,int cw){
        //如果重量达到背包的重量，或者物品使用完毕
        if(cw == w || i == n){
            if(cw>maxW){
                maxW = cw;
            }
            return;
        }
        if(mem[i][cw]){
            return;
        }
        mem[i][cw] = true;
        //选择不装第i个物品
        calculate(i+1,cw);
        if(cw+weight[i]<=w){
            //选择装第i个物品
            calculate(i+1,cw+weight[i]);
        }
    }
    public static void main(String[] args) {
        cal8Queens(0);
    }
}
