package com.example.knowledge.algorithms.search;

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
    private LinkedList<Integer> list[];

    public Graph(int v) {
        this.v = v;
        for (int i = 0; i < v; i++) {
            list[i] = new LinkedList<>();
        }
    }

    /**
     * 无向图添加顶点的关系
     */
    public void addEdge(){

    }
}
