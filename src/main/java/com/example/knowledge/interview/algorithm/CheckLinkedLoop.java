package com.example.knowledge.interview.algorithm;

/**
 * 校验链表是否有环
 *
 * 解题思路
 *  首先创建两个指针p1和p2，让它们同时指向这个链表的头节点，然后开始一个大循环。
 *  在循环体中，让指针p1每次向后移动一个节点，让指针p2每次向后移动两个节点。
 *  然后比较两个指针指向的节点是否相同。如果
 * @author 大米哥
 */
public class CheckLinkedLoop {

    /**
     * 链表节点
     */
    private static class Node{
        int data;
        Node next;
        Node(int data){
            this.data = data;
        }
    }

    /**
     * 判断链表是否有环
     * @param head 链表头节点
     * @return
     */
    public static boolean isCycle(Node head){
        Node p1 = head;
        Node p2 = head;
        while (p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                return true;
            }
        }
        return false;
    }

    /**
     * 获取环的长度
     * 第一次相遇和第二次相遇之间的长度就是环的长度
     * @param head
     * @return
     */
    public static int cycleLength(Node head){
        Node p1 = head;
        Node p2 = head;
        int meetCount = 0;
        int count = 0;
        while (p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                meetCount ++;
            }
            if(meetCount>1){
                return count;
            }
            if(meetCount==1){
                count++;
            }
        }

        return count;

    }

    /**
     * 计算入环节点
     * 假设从链表头节点到入环点的距离是D，从入环点到首次相遇点的距离是S1，从首次相遇点回到入环点的距离是S2
     * 指针P1每次只走2步，所走的距离是D+S1
     * 指针P2每次走两步，多走了n+1整圈，所走的距离是D+S1+n(S1+S2)
     * 由于P2的速度是P1的两倍，所以走的距离也是P1的两倍，因此：
     *              2(D+S1) = D+S1+n(S1+S2)
     * 等式经过计算得出
     *              D = (n-1)(S1+S2)+S2
     * 也就是说，从链表头节点到入环点的距离，等于从首次相遇点，绕环n-1圈再回到入环点的距离
     *
     * 这样一来，只要把其中一个指针放到头节点的位置，另一个指针保持在首次相遇的点
     * 两个指针都是每次往前走1步。那么，他们最终相遇的节点就是入环节点
     * @param head
     * @return
     */
    public static Node ringNode(Node head){
        Node p1 = head;
        Node p2 = head;
        boolean flag = false;
        while (p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1 == p2){
                flag = true;
                break;
            }
        }
        if(flag){
            while (p2!=null && p2.next!=null){
                p1 = p1.next;
                p2 = p2.next;
                if(p1 == p2){
                    return p1;
                }
            }
        }

        return null;

    }
    public static void main(String[] args) {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node2;
        System.out.println(isCycle(node1));
        System.out.println(cycleLength(node1));
        System.out.println(ringNode(node1).data);
    }
}
