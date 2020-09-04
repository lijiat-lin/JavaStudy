package com.example.knowledge.interview.algorithm;

/**
 * @program: knowledge
 * @description: 单链表反转
 * @author: zhangjialin
 * @create: 2020-09-03 15:50
 */
public class ReverseLinkedList {

    private Node head;

    private Integer length;

    public ReverseLinkedList() {
        this.head = new Node();
        this.length = 0;
    }

    private static class Node{
        int data;
        Node next;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }
    }

    public void insert(Node node){
        node.next = head.next;
        head.next = node;
        length++;
    }
    public void printf(Node node){
        while (node!=null){
            System.out.print(node.data+",");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 单链表反转
     * @param node
     */
    public void reverse(Node node){
        Node newHead = new Node();
        while (node!=null){
            Node temp = node.next;
            node.next = newHead.next;
            newHead.next = node;
            node = temp;
        }
        printf(newHead);

    }
    public static void main(String[] args) {
        ReverseLinkedList linkedList = new ReverseLinkedList();
        for (int i = 10; i < 20; i++) {
            linkedList.insert(new Node(i));
        }
        linkedList.printf(linkedList.head);
        linkedList.reverse(linkedList.head.next);
        linkedList.printf(linkedList.head);

    }

}
