package com.example.knowledge.algorithms;

/**
 * @program: knowledge
 * @description: 二叉查找树
 * @author: zhangjialin
 * @create: 2020-09-11 15:41
 */
public class BinarySearchTree {
    private Node tree;

    public Node find(int data){
        Node p = tree;
        while (p!=null){
            if(data<p.data){
                p = p.left;
            }else if(data>p.data){
                p = p.right;
            }else{
                return p;
            }
        }
        return null;
    }

    public void insert(int data){
        if (tree == null){
            tree = new Node(data);
            return;
        }
        Node p = tree;
        while (p!=null){
            if (data == p.data){
                return;
            }
            if (data<p.data){
                if (p.left == null){
                    p.left = new Node(data);
                    return;
                }
                p = p.left;
            }else{
                if(p.right == null){
                    p.right = new Node(data);
                    return;
                }
                p = p.right;
            }
        }

    }

    public void delete(int data){
        Node p = tree;
        //p节点的父节点
        Node pp = null;
        while (p!=null&&p.data!=data){
            pp = p;
            if(data<p.data){
                p =p.left;
            }else{
                p = p.right;
            }
        }

        if(p == null){
            return;
        }
        //要删除的节点有左右节点

        if(p.left!=null && p.right!=null){
            Node minP = p.right;
            Node minPP = p;
            while (p.left!=null){
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            p = minP;
            pp = minPP;
        }
        //要删除的节点是子节点或者只有一个节点
        Node child;
        if (p.left!= null){
            child = p.left;
        }else if(p.right != null){
            child = p.right;
        }else{
            child = null;
        }

        if(pp == null) {
            tree = child;
        }else if (pp.left == p){
            pp.left = child;
        }else{
            pp.right = child;
        }

    }
    public static class Node{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
