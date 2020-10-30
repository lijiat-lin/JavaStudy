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
     *
     *      这里可以将states[1][2] 合并  也就是说经过第二个物品之后存在的可能性是states[1][0]  states[1][2]  states[1][4]
     *      以此类推，直到考察完所有的物品后，整个 states 状态数组就都计算好了。
     *      图中 0 表示 false，1 表示 true。我们只需要在最后一层，找一个值为 true 的最接近 w（这里是 9）的值，就是背包中物品总重量的最大值。
     * @param weight 物品重量
     * @param n 物品个数
     * @param w 背包可承载的重量
     */
    public static int knapsack(int[] weight,int n,int w){
        //状态默认值false
        boolean[][] states = new boolean[n][w+1];
        //第一个物品未放入背包
        states[0][0] = true;
        //第二个物品放入背包
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
        for (int i = w; i >=0 ; i--) {
            if(states[n-1][i]){
                return i;
            }
        }
        return 0;
    }

    /**
     * 我们现在引入物品价值这一变量。对于一组不同重量、不同价值、不可分割的物品，我们选择将某些物品装入背包，在满足背包最大重量限制的前提下，背包中可装入物品的总价值最大是多少呢？
     * int[] items = {2，2，4，6，3}; // 物品的重量
     * int[] value = {3，4，8，9，6}; // 物品的价值
     * int w = 9; // 背包承受的最大重量
     *
     * 依然是采用二维数组记录 每一个物品的选择以及最后的重量，但是数组的值定义为物品的价值。
     * 如果物品的编号和放入前的重量是相同的  则选择价值最大的保存
     * 例如states[2][2] = 3  和 states[2][2] = 4  选择价值为4的物品选择方式
     *
     * @param weight 物品的重量
     * @param value 物品的价值
     * @param n 物品的数量
     * @param w 背包承受的最大重量
     * @return
     */
    public static int knapsack1(int[] weight,int[] value,int n,int w){
        int[][] states = new int[n][w+1];
        //初始化state的价格数据
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w+1; j++) {
                states[i][j] = -1;
            }
        }
        //第一个物品未放置，价值自然也是0
        states[0][0] = 0;
        if(weight[0]<= w){
            //记录放置第一个物品的价值
            states[0][weight[0]] = value[0];
        }

        //动态规划，状态转移
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w ; j++) {
                //不选择第i个物品
                if(states[i-1][j] >=0 ){
                    states[i][j] = states[i-1][j];
                }
            }
            for (int j = 0; j <= w-weight[i]; j++) {
                //选择第i个物品
                if(states[i-1][j]>=0){
                    int v = states[i-1][j]+value[i];
                    if(v > states[i][j+weight[i]]){
                        states[i][j+weight[i]] = v;
                    }
                }
            }
        }
        int maxValue =-1;
        for (int i = 0; i <= w ; i++) {
            if(states[n-1][i] >maxValue){
                maxValue = states[n-1][i];
            }
        }

        return maxValue;
    }

    /**
     *
     * @param items 每个商品的价格
     * @param n 商品的数量
     * @param w 价格超过w才会满减
     */
    public static void double11Advance(int[] items,int n,int w){
        //定义二维数，记录是否选择商品,超过满减3三倍的价格就不选择了。
        boolean[][] states = new boolean[n][3*w+1];
        states[0][0] = true;
        if(items[0]<=3*w){
            states[0][items[0]] = true;
        }

        //动态规划
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= 3*w; j++) {
                //不选择这个商品
                if(states[i-1][j]){
                    states[i][j] = states[i-1][j];
                }
            }

            for (int j = 0; j <= 3*w - items[i]; j++) {
                if (states[i-1][j]){
                    states[i][j+items[i]] = true;
                }
            }
        }
        int j;
        //得到结果大于等于w的最小值
        for ( j = w; j < 3*w+1; j++) {
            if(states[n-1][j]){
                break;
            }
        }
        if(j == 3*w+1){
            return;
        }
        for (int i = n-1; i >= 1 ; i--) {
            //如果减去这个物品的价值后，上一个物品的选择是true  就表示选择了这个物品
            if(j-items[i]>=0 && states[i-1][j-items[i]]){
                //选择了商品
                System.out.println(items[i]+"");
                j = j-items[i];
            }
        }
        if (j!=0){
            System.out.println(items[0]);
        }
    }

    /**
     *
     * @param mareix
     * @return
     */
    public int yangHuiSanJiao(int[][] mareix ,int n){

        int[][] states = new int[n][n];

        states[0][0] = mareix[0][0];
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <=i; j++) {
                if(j == 0 ) {
                    states[i][j] = states[i - 1][j] + mareix[i][j];
                }else if(j == i-1){
                    states[i][j] = states[i-1][j-1] + mareix[i][j];
                }else{
                    int top1 = states[i-1][j-1];
                    int top2 = states[i-1][j];
                    states[i][j] = Math.min(top1,top2)+mareix[i][j];
                }
            }
        }
        int minDis = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if(states[n-1][i]<minDis){
                minDis = states[n-1][i];
            }
        }

        return minDis;
    }

    /**
     * 校验每个网格周边的四个格子是否为1
     * @param grid
     * @return
     */
    public static int islandPerimeter(int[][] grid) {
        int sum  = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == 1){
                    sum+=4;
                    //上
                    if(i>0 && grid[i-1][j] == 1){
                        sum-=2;
                    }

                    //左
                    if(j>0 && grid[i][j-1] == 1){
                        sum -=2;
                    }

                }
            }
            System.out.println(sum);
        }
        System.out.println(sum);
        return sum;
    }
    public static void main(String[] args) {
        islandPerimeter(new int[][]{{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}});
    }

}
