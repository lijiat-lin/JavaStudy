package com.example.knowledge.algorithms.search;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: knowledge
 * @description: 图
 * @author: zhangjialin
 * @create: 2020-09-18 10:55
 */
public class Graph {
    /**
     * 顶点的个数
     */
    private int v;
    /**
     * 邻接表实现图
     */
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i=0; i<v; ++i) {
            adj[i] = new LinkedList<>();
        }
    }
    /**
     * 无向图添加顶点的关系
     */
    public void addEdge(int s, int t) { // 无向图一条边存两次
        adj[s].add(t);
        adj[t].add(s);
    }

    /**
     * 广度优先搜索（Breadth-First-Search），其实是一种“地毯式”的层层递进的搜索策略，即先查找距离顶点最近的，然后是次近的，依次往外搜索。
     * @param s
     * @param t
     */
    public void bfs(int s,int t){
        if (s == t){
            return;
        }
        //定义一个图大小的boolean数组，用于记录每个节点是否被访问过
        boolean[] visited = new boolean[v];
        visited[s] = true;

        //定义一个队列，队列中存放的是已经被访问但是相邻节点未被访问的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        //定义一个数据用来记录搜索路径，例如2的下一个连接点是3，那么prev[3] = 2;
        int[] prev = new int[v];
        Arrays.fill(prev,-1);

        while (!queue.isEmpty()){
            int w = queue.poll();
            //循环这个节点所连接的其他定点
            for (int i = 0; i < adj[w].size(); i++) {
                int q = adj[w].get(i);
                //判断q是否被访问过
                if(!visited[q]){
                    //将当前节点的路径设置为访问他的顶点
                    prev[q] = w;
                    if(q==t){
                        print(prev,s,t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }


    /**
     * 全局变量或者类成员变量
     * 用于表示目标是否被发现
     */
    boolean found = false;
    /**
     * 深度优先搜索（Depth-First-Search），简称 DFS。最直观的例子就是“走迷宫”。
     * @param s
     * @param t
     */
    public void dfs(int s,int t){
        found = false;

        //用于记录被访问的顶点
        boolean[] visited = new boolean[v];

        //用于记录当前顶点的路径，假如2的下一个连接顶点是3   那么prev[3] = 2
        int[] prev = new int[v];
        Arrays.fill(prev,-1);
        recurDfs(s,t,visited,prev);
        print(prev,s,t);
    }

    /**
     * 深度查找目标顶点
     * @param w
     * @param t
     * @param visited
     * @param prev
     */
    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if(found){
            return;
        }
        visited[w] = true;
        if (w == t){
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); i++) {
            int q = adj[w].get(i);
            if(!visited[q]){
                prev[q] = w;
                recurDfs(q,t,visited,prev);
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }
}
