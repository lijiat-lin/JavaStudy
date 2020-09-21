package com.example.knowledge.algorithms;

/**
 * @program: knowledge
 * @description: 动态规划
 * @author: zhangjialin
 * @create: 2020-09-21 15:32
 */
public class DynamicProgramming {

    /**
     * 0-1背包问题
     * 背包承受的最大重量是9
     * 物品的重量依次是 2,2,4,6,3
     * 使用二维数据记录每次将物品放到背包之后的状态
     * 例如 第一件物品的重量是2 那存在两种可能性，要么放要么不放，就会导致两种结果  没有放入背包：states[0][0] = true    放入背包state[0][2] = true
     * 第二件物品的重量是2 它在基于第一个物品的结果之后存在3种
     *      第一个没放，第二个没放：states[1][0] = true
     *      第一个放了，第二个没放：states[1][2] = true
     *      第一个没放，第二个放了：states[1][2] = true
     *      第一个放了，第二个放了：states[1][4] = true
     * @param weight 物品重量
     * @param n 物品个数
     * @param w 背包可承载的重量
     */
    public static int knapsack(int[] weight,int n,int w){
        //状态默认值false
        boolean[][] states = new boolean[n][w+1];
        states[0][0] = true;
        if(weight[0]<=w){
            states[0][weight[0]] = true;
        }
        //动态规划状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w ; j++) {
                //不把第i个物品放入背包
                if (states[i-1][j]){
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= w-weight[i]; j++) {
                //把第i个物品放入背包
                if (states[i-1][j]){
                    states[i][j+weight[i]] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w+1; j++) {
                System.out.printf(states[i][j]+",");
            }
            System.out.println();
        }
        for (int i = w; i >=0 ; i--) {
            if(states[n-1][i]){
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] weight = new int[]{2,2,4,6,3};
        System.out.println(knapsack(weight, weight.length, 9));
    }

}
