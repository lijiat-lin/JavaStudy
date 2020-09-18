package com.example.knowledge.algorithms;

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
     * 邻接表
     * 数组中的每个订单都有的链表，标识连接的顶点
     *
     */
    private LinkedList<Integer> adj[];

    public Graph(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public void addEdge(int s,int t){
        //无向图 每条边存储两次
        adj[s].add(t);
        adj[t].add(s);
    }


    /**
     * 广度优先搜索
     * 搜索从s到t的路径，就是求从s到t的最短路径
     * @param s
     * @param t
     */
    public void bfs(int s, int t) {
        if (s == t){
            return;
        }
        //定义一个顶点个数大小的  boolean数据，用来记录被访问的顶点，避免顶点被重复访问
        //如果顶点q被访问了就会设置为true
        boolean[] visited = new boolean[v];
        visited[s]=true;
        //queue 是一个队列，用来存储已经被访问、但相连的顶点还没有被访问的顶点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        //prev 用来记录搜索路径  假如 2 的 下一个连接顶点是3   那么prev[3] = 2。
        int[] prev = new int[v];

        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }

        while (queue.size() != 0) {
            int w = queue.poll();
            //循环这个顶点相连的顶点
            for (int i = 0; i < adj[w].size(); ++i) {
                //获取顶点w 相连的顶点 q
                int q = adj[w].get(i);
                //判断q是否被访问过
                if (!visited[q]) {
                    prev[q] = w;
                    if (q == t) {
                        print(prev, s, t);
                        return;
                    }
                    visited[q] = true;
                    queue.add(q);
                }
            }
        }
    }

    private void print(int[] prev, int s, int t) { // 递归打印s->t的路径
        if (prev[t] != -1 && t != s) {
            print(prev, s, prev[t]);
        }
        System.out.print(t + " ");
    }


    /**
     * 全局变量或者类成员变量
     * 标识目标订单是否被发现
     */
    boolean found = false;

    public void dfs(int s, int t) {
        found = false;
        //用来记录被访问的订单
        boolean[] visited = new boolean[v];
        //用来记录当前顶点的路径,假如 2 的 下一个连接顶点是3   那么prev[3] = 2。
        int[] prev = new int[v];
        for (int i = 0; i < v; ++i) {
            prev[i] = -1;
        }
        recurDfs(s, t, visited, prev);
        print(prev, s, t);
    }

    private void recurDfs(int w, int t, boolean[] visited, int[] prev) {
        if (found == true){ return;}
        visited[w] = true;
        if (w == t) {
            found = true;
            return;
        }
        for (int i = 0; i < adj[w].size(); ++i) {
            //获取当前顶点关联的顶点
            int q = adj[w].get(i);
            if (!visited[q]) {
                prev[q] = w;
                recurDfs(q, t, visited, prev);
            }
        }
    }
}
