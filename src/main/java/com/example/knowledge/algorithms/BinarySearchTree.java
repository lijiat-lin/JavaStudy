package com.example.knowledge.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

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

    public static int findTreeHigh(Node node){

        if (node.left == null && node.right == null){
            return 1;
        }
        //求左子树的高度
        int leftHigh = 0;
        if(node.left!=null){
            leftHigh = findTreeHigh(node.left);
        }
        int rightHigh = 0;
        //求右子树的高度
        if(node.right != null){
            rightHigh = findTreeHigh(node.right);
        }
        return leftHigh>rightHigh?leftHigh+1:rightHigh+1;
    }

    /**
     * 前序遍历
     * @param node
     */
    public static void preOrder(Node node){
        if(node == null){
            return;
        }
        System.out.printf(node.data+",");
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 中序遍历
     * @param node
     */
    public static void inOrder(Node node){
        if(node == null){
            return;
        }
        preOrder(node.left);
        System.out.printf(node.data+",");
        preOrder(node.right);
    }


    /**
     * 后序遍历
     * @param node
     */
    public static void postOrder(Node node){
        if(node == null){
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.printf(node.data+",");
    }

    /**
     * 层级遍历
     * @param node
     */
    public static void levelOrder(Node node){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()){
            Node node1 = queue.poll();
            System.out.printf(node1.data+",");
            if(node1.left!=null){
                queue.offer(node1.left);
            }

            if (node1.right!=null){
                queue.offer(node1.right);
            }
        }
    }


    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        Random random = new Random();

        binarySearchTree.insert(50);
        for (int i = 0; i < 30; i++) {
            binarySearchTree.insert(random.nextInt(100));

        }
        System.out.println("前序遍历：");
        preOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("中序遍历：");
        inOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("后序遍历：");
        postOrder(binarySearchTree.tree);
        System.out.println();

        System.out.println("层级遍历（广度优先）");
        levelOrder(binarySearchTree.tree);
        System.out.println();
        System.out.println("数的层数"+findTreeHigh(binarySearchTree.tree));


        System.out.println(10%10);


    }
}
