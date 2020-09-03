package com.example.knowledge.cartoon;

import java.util.*;
import java.util.LinkedList;

/**
 * 二叉树的递归遍历 深度（前序遍历，中序遍历，后序遍历）
 * 二叉树的非递归遍历 深度（前序遍历，中序遍历，后序遍历）
 */
public class TreeLinkedList {

    public static class TreeNode{
        int data;
        TreeNode leftChild;
        TreeNode rightChild;
        TreeNode(int data){
            this.data = data;
        }
    }

    /**
     * 创建二叉树
     * @param inputList
     * @return
     */
    public static TreeNode createBinaryTree(LinkedList<Integer> inputList){
        TreeNode node = null;
        if(inputList == null || inputList.isEmpty()){
            return null;
        }
        Integer data = inputList.removeFirst();
        if(data!=null){
            node = new TreeNode(data);
            node.leftChild = createBinaryTree(inputList);
            node.rightChild = createBinaryTree(inputList);
        }

        return node;
    }

    /**
     * 前序遍历
     * @param node
     */
    public static void preOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        System.out.print(node.data);
        preOrderTraveral(node.leftChild);
        preOrderTraveral(node.rightChild);
    }


    /**
     * 中序遍历
     * @param node
     */
    public static void inOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        inOrderTraveral(node.leftChild);
        System.out.print(node.data);
        inOrderTraveral(node.rightChild);
    }


    /**
     * 后序遍历
     * @param node
     */
    public static void postOrderTraveral(TreeNode node){
        if(node == null){
            return;
        }
        postOrderTraveral(node.leftChild);
        postOrderTraveral(node.rightChild);
        System.out.print(node.data);

    }

    /**
     * 二叉树非递归前序遍历
     * @param root
     */
    public static void preOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = root;
        while(treeNode!=null || !stack.isEmpty()){
            //迭代方位节点的孩子，并且入栈
            while (treeNode!=null){
                System.out.print(treeNode.data);
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //如果节点没有左孩子，则弹出栈顶节点，访问节点右孩子
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                treeNode = treeNode.rightChild;
            }

        }
    }

    /**
     * 二叉树非递归中序遍历
     * @param root
     */
    public static void inOrderTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode treeNode = root;
        while(treeNode!=null || !stack.isEmpty()){
            //一直遍历到左子树的最下面，边遍历边保存根节点到栈中
            while(treeNode!=null){
                stack.push(treeNode);
                treeNode = treeNode.leftChild;
            }
            //当treeNode为null的时候，说明已经到达左子树的最下面了，这个时候就需要出栈了
            if(!stack.isEmpty()){
                treeNode = stack.pop();
                System.out.print(treeNode.data);
                treeNode = treeNode.rightChild;
            }


        }
    }

    /**
     * 二叉树非递归后序遍历
     * @param root
     */
    public static void postTraveralWithStack(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //当前访问的节点
        TreeNode treeNode = root;
        //上一次访问的节点
        TreeNode lastVisit = null;

        //先访问节点到最左
        while(treeNode!=null){
            stack.push(treeNode);
            treeNode = treeNode.leftChild;
        }

        while(!stack.isEmpty()){
            //到这里 treeNode已经是null了，也就是到了树的最左孩子
            treeNode = stack.pop();

            //一个根节点被访问的前提是：无右子树或者右子树被访问完毕
            if(treeNode.rightChild == null||treeNode.rightChild == lastVisit){
                System.out.print(treeNode.data);
                lastVisit = treeNode;
            }else{
                //根节点再次入栈
                stack.push(treeNode);
                //进入右子树，且可以肯定右子树不为null
                treeNode = treeNode.rightChild;
                while (treeNode!=null){
                    stack.push(treeNode);
                    treeNode = treeNode.leftChild;
                }
            }
        }

    }

    public static void BreadthFirstTraversal(TreeNode treeNode){
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(treeNode);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            System.out.print(node.data);
            if(node.leftChild!=null){
                queue.offer(node.leftChild);
            }
            if(node.rightChild!=null){
                queue.offer(node.rightChild);
            }
        }

    }

    public static void main(String[] args) {
        LinkedList<Integer> inputList = new LinkedList<Integer>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode node = createBinaryTree(inputList);
        System.out.println("前序遍历：");
        preOrderTraveral(node);
        System.out.println();

        preOrderTraveralWithStack(node);
        System.out.println();


        System.out.println("中序遍历：");

        inOrderTraveral(node);
        System.out.println();

        inOrderTraveralWithStack(node);
        System.out.println();

        System.out.println("后序遍历：");

        postOrderTraveral(node);
        System.out.println();

        postTraveralWithStack(node);
        System.out.println();

        System.out.println("广度优先：");
        BreadthFirstTraversal(node);

    }


}
