package com.example.knowledge.cartoon;

/**
 * 链表的  添加 查询  删除
 */
public class LinkedList {
    
    //头指针节点
    private Node head;
    
    //尾指针节点
    private Node last;
    
    //链表实际长度
    private int size;

    public static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }
    /**
     * 链表插入元素
     * @param data 插入元素
     * @param index 插入位置
     * @throws Exception
     */
    public void insert(int data,int index) throws Exception{
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }
        Node insertNode = new Node(data);
        if(size ==0){
            //空链表
            head = insertNode;
            last = insertNode;
        }else if(index == 0){
            //插入头部
            insertNode.next = head;
            head = insertNode;
        }else if(index == size){
            //插入尾部
            last.next = insertNode;
            last = insertNode;
        }else{
            //插入中间
            Node prevNode = get(index -1);
            insertNode.next = prevNode.next;
            prevNode.next = insertNode;
        }
        size++;
    }

    /**
     * 查询链表
     * @param index
     * @return
     * @throws Exception
     */
    public Node get(int index) throws Exception{
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }

        Node temp = head;
        for(int i=0;i<index;i++){
            temp = temp.next;
        }
        return temp;
    }
    
    public Node remove(int index) throws Exception{
        if(index<0||index>size){
            throw new IndexOutOfBoundsException("超出链表节点范围");
        }

        Node removeNode = null;
        if(index == 0){
            //插入头部
            removeNode = head;
            head = head.next;
        }else if(index == size-1){
            //插入尾部
            Node prevNode = get(index -1);
            removeNode = prevNode.next;
            prevNode.next = null;
            last = prevNode;
        }else{
            //插入中间
            Node prevNode = get(index -1);
            Node nextNode = prevNode.next.next;
            removeNode = prevNode.next;
            prevNode.next = nextNode;
        }
        size--;
        return removeNode;
    }

    public void output(){
        Node temp = head;
        while(temp!=null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) throws Exception{
//        LinkedList linkedList = new LinkedList();
//        linkedList.insert(3,0);
//        linkedList.insert(7,1);
//        linkedList.insert(9,2);
//        linkedList.insert(5,3);
//        linkedList.insert(6,1);
//        linkedList.remove(0);
//        linkedList.output();
        System.out.println(tableSizeFor(10));

    }
    static final int tableSizeFor(int cap) {
        System.out.println(MAXIMUM_CAPACITY);
        int n = cap - 1;
        System.out.println(n);
        n |= n >>> 1;
        System.out.println(n);
        n |= n >>> 2;
        System.out.println(n);
        n |= n >>> 4;
        System.out.println(n);
        n |= n >>> 8;
        System.out.println(n);
        n |= n >>> 16;
        System.out.println(n);
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
