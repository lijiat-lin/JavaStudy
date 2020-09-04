package com.example.knowledge.cartoon;

import java.util.Scanner;

/**
 *
 * 1.链表中不存在数据，直接存储在头结点
 * 2.链表中已经存在该数据，则删除原数据，存储在头结点
 * 3.如果链表长度大于最大长度，则删除尾结点，将数据插入在头结点
 * @program: knowledge
 * @description: 基于单链表LRU算法（java）
 * @author: zhangjialin
 * @create: 2020-09-03 14:53
 */
public class LRUBaseLinkedList<T> {

    private static final Integer DEFAULT_CAPACITY = 10;

    private SNode head;

    private Integer length;

    private Integer capacity;


    public LRUBaseLinkedList() {
        this.head = new SNode<>();
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
    }

    public LRUBaseLinkedList(Integer capacity) {
        this.head = new SNode<>();
        this.length = 0;
        this.capacity = capacity;
    }

    public void add(T data){
        //查找数据是否存在，如果存在则获取他的前一个节点
        SNode preNode = findPreNode(data);

        if (preNode!=null){
            //删除相同数据的节点
            deleteElementSame(preNode);
        }else{
            if(length >= this.capacity){
                //删除尾结点
                deleteElementEnd();
            }
        }
        //在头结点添加数据
        insertElementAtBegin(data);
    }

    private void insertElementAtBegin(T data) {
        SNode node = head.getNext();
        head.setNext(new SNode(data,node));
        length++;
    }

    private void deleteElementEnd() {
        SNode ptr = head;
        if (ptr.getNext()==null){
            return;
        }

        while(ptr.getNext().getNext()!=null){
            ptr = ptr.getNext();
        }
        SNode temp = ptr.getNext();
        ptr.setNext(null);
        temp = null;
        length --;

    }

    private void deleteElementSame(SNode preNode) {
        SNode temp = preNode.next;
        preNode.setNext(temp.getNext());
        temp = null;
        length--;
    }

    private SNode findPreNode(T data) {
        SNode node = head;
        while (node.getNext()!=null){
            if (data.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }


    /**
     * 结点类型
     * @param <T>
     */
    public class SNode<T>{
        private T element;

        private SNode next;

        public SNode() {
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    private void printAll(){
        SNode sNode = head;
        while (sNode!=null){
            System.out.print(sNode.getElement()+",");
            sNode = sNode.getNext();
        }
        System.out.println();
    }
    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
//        Scanner sc= new Scanner(System.in);
//        while (true){
//            list.add(sc.nextInt());
//            list.printAll();
//        }
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            list.add(i);
        }
        list.printAll();
    }
}
