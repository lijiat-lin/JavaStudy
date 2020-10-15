package com.example.knowledge.leetCode;

import java.util.*;


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-09-14 23:40
 */
public class Solution {

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
     * 回文子串：头尾相同
     *
     * 解题思路：
     * 动态规划解法：
     *  当一个字符串的左右两端的字符相同，并且它去除左右两端字符的子串是回文字符串，那么这个字符串就是回文字符串
     * @param s
     * @return
     */
    public static  String longestPalindrome(String s) {
        int n = s.length();
        //设置一个二维的boolean数组，标识字符串的某一段子串是否是回文字符串
        boolean[][] dp = new boolean[n][n];

        String ans = "";
        //字符串一共有n和不同长度的子串
        for (int i = 0; i < n; ++i) {
            //循环获取字符串中长度为i的字符串
            for (int j = 0; j+i < n; ++j) {
                int k = j+i;
                if (i==0){
                    dp[j][k] = true;
                }else if(i == 1){
                    dp[j][k] = (s.charAt(j) == s.charAt(k));
                }else{
                    dp[j][k] = (s.charAt(j) == s.charAt(k) && dp[j+1][k-1]);
                }

                if(dp[j][k]&& i+1 >ans.length()){
                    ans = s.substring(j,j+i+1);
                }
            }
        }
        return ans;
    }

    /**
     * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     * 输入: s1 = "abc", s2 = "bca"
     * 输出: true
     *
     * 输入: s1 = "abc", s2 = "bad"
     * 输出: false
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean checkPermutation(String s1, String s2) {
        if (s1.length()!=s2.length()){
            return false;
        }
        int[] charsCount = new int[128];
        for (int i = 0; i < s1.toCharArray().length; i++) {
            charsCount[s1.charAt(i)]++;
            charsCount[s2.charAt(i)]--;
        }
        for (int i = 0; i < charsCount.length; i++) {
            if (charsCount[i]!=0){
                return false;
            }
        }
        return true;
    }

    /**
     * URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。
     * （注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * @param s
     * @param length
     * @return
     */
    public static String replaceSpaces(String s, int length) {

        char[] chars = new char[length*3];

        int index = 0;
        for (int i = 0; i < length; i++) {
            if(s.charAt(i) == 32){
                chars[index++] = '%';
                chars[index++] = '2';
                chars[index++] = '0';

            }else{
                chars[index++] = s.charAt(i);
            }
        }

        return String.copyValueOf(chars,0,index);
    }

    /**
     * 给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     * 回文串不一定是字典当中的单词。
     *
     * 输入："tactcoa"
     * 输出：true（排列有"tacocat"、"atcocta"，等等）
     * @param s
     * @return
     */
    public static boolean canPermutePalindrome(String s) {

        int[] charsCount = new int[128];
        for (int i = 0; i < s.toCharArray().length; i++) {
            if (charsCount[s.charAt(i)] == 0){
                charsCount[s.charAt(i)]++;
            }else{
                charsCount[s.charAt(i)]--;
            }
        }
        int result = 0;

        for (int m: charsCount){
            result+=m;
        }
        return result<=1;
    }

    /**
     * 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     * 输入:
     * first = "pale"
     * second = "ple"
     * 输出: True
     *
     * @param first
     * @param second
     * @return
     */
    public static boolean oneEditAway(String first, String second) {
        if (first == null || second == null)
        {return false;}
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) {return false;}
        if (len2 > len1) {return oneEditAway(second, first);}

        // 保持第一个比第二个长
        for (int i = 0; i < len2; i++){
            if (first.charAt(i) != second.charAt(i)){
                // 如果是长度相同字符串，那就比较下一个，如果长度不一样，那就从该字符开始进行比较。
                return first.substring(i + 1).equals(second.substring(len1 == len2 ? i + 1 : i));
            }
        }
        return true;
    }


    /**
     * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。
     * 比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。
     * 你可以假设字符串中只包含大小写英文字母（a至z）。
     *
     *
     * @param S
     * @return
     */
    public static String compressString(String S) {
        //校验字符串的长度
        if(S.length()<=2){
            return S;
        }
        char[] chars = S.toCharArray();
        int index = 0;

        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('!');
        for (int i = 0; i < chars.length; i++) {
            if(stringBuilder.charAt(index)!=chars[i]){
                stringBuilder.append(count);
                stringBuilder.append(chars[i]);
                index += Integer.toString(count).length()+1;
                count = 1;
            }else{
                count++;
            }
            if(index-2>=S.length()){
                return S;
            }
        }
        stringBuilder.append(count);
        index += Integer.toString(count).length();

        if(stringBuilder.length()-2>=S.length()){
            return S;
        }else{
            return stringBuilder.substring(2,index+1);
        }

    }

    /**
     * 给你一幅由 N × N 矩阵表示的图像，其中每个像素的大小为 4 字节。请你设计一种算法，将图像旋转 90 度。
     *
     * 不占用额外内存空间能否做到？
     *
     * 解析：
     * i行j列的数据转化为
     * i,j - j,n-i-1
     * 每一次循环交换4个数据
     * 0,1  1,3   3,2   2,0
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int count = 0;
        for (int i = 0; i < n-count-1; i++) {
            for (int j = i; j < n-count-1; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = tmp;
            }
            count++;
        }
    }
    public static void  soutMatrix(int[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf(matrix[i][j]+",");
            }
            System.out.println();
        }
    }

    /**
     * 编写一种算法，若M × N矩阵中某个元素为0，则将其所在的行与列清零。
     * @param matrix
     */
    public static void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        HashSet<Integer> row = new HashSet<>();
        HashSet<Integer> column = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 0){
                    row.add(i);
                    column.add(j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(row.contains(i)||column.contains(j)){
                    matrix[i][j] = 0;
                }
            }
        }

    }



    /**
     *给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     * 给定 1->2->3->4, 你应该返回 2->1->4->3.
     * 解析:
     * 如果是头结点则将
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        ListNode node = head;
        if(head==null || head.next == null){
            return null;
        }

        ListNode tmp = node.next;
        node.next = node.next.next;
        tmp.next = node;
        head = tmp;

        while (node.next!=null&&node.next.next!=null){
            ListNode tmp1 = node.next;
            node.next = node.next.next;
            tmp1.next = node.next.next;
            node.next.next = tmp1;
            node = node.next.next;
        }
        return head;
    }


    /**
     * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符（包括重复字符）组成的列表。
     * 例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
     *
     * 你可以按任意顺序返回答案。
     *
     * 示例：
     * 输入：["bella","label","roller"]
     * 输出：["e","l","l"]
     *
     * 解析：
     * 循环A数组，循环每个字符串中的字符，使用整数数组记录字符出现的次数。
     * @param A
     * @return
     */
    public static List<String> commonChars(String[] A) {
        if (A==null){
            return null;
        }
        List<String> list = new ArrayList<>();
        int[] minFreq = new int[26];
        Arrays.fill(minFreq,Integer.MAX_VALUE);
        for(String word : A){
            int[] freq = new int[26];
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                ++freq[c-'a'];
            }
            for (int i = 0; i < 26; i++) {
                minFreq[i] = Math.min(minFreq[i],freq[i]);
            }
        }

        for (int i = 0; i < 26; i++) {
            for (int j = 0; j < minFreq[i]; j++) {
                list.add(String.valueOf((char)(i+'a')));
            }
        }
        return list;
    }

    /**
     * 给定一个完美二叉树，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
     * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
     *
     * 解析：
     * 完全二叉树的节点，按照层次遍历放置到Node数组中
     * 节点如果是父节点的左节点，则它的next是父节点的右节点。
     * 节点如果是父节点的右节点，则它的next节点是父节点的next节点的左节点，如果父节点的next节点为null，那么这个节点的next也是null
     *
     *
     * 先操作节点本身，然后操作它的右节点，最后操作它的左节点
     * @param root
     * @return
     */
    public static Node connect(Node root) {

        if(root==null){
            return null;
        }
        Node node = root;
        if (node.left!=null&&node.right!=null){
            node.left.next = node.right;
            if (node.next==null){
                node.right.next = null;
            }else{
                node.right.next = node.next.left;
            }
        }
        connect(node.right);
        connect(node.left);
        return node;
    }

    public static List<List<Integer>> levelOrder(Node root){
        if (root == null){
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int count = queue.size();
            List<Integer> list = new ArrayList<>();
            while (count>0){
                Node node = queue.poll();
                list.add(node.val);
                if(node.left!=null){
                    queue.add(node.left);
                }
                if (node.right!=null){
                    queue.add(node.right);
                }
                count--;
            }

            res.add(list);
        }
        return res;
    }


    /**
     * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
     *
     * 解题思路：
     * 二叉搜索树有个性质为二叉搜索树中序遍历得到的值序列是递增有序的
     *
     * 需要先遍历二叉树的所有节点，然后进行排序，遍历数组获取最小差值。
     * @param root
     * @return
     */

    static int pre;
    static int res;
    public static int getMinimumDifference(TreeNode root) {
        res = Integer.MAX_VALUE;
        pre = -1;
        inOrder(root);
        return res;
    }
    public static void inOrder(TreeNode node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        if(pre == -1){
            pre = node.val;
        }else{
            res = Math.min(res,node.val-pre);
            pre = node.val;
        }
        inOrder(node.right);
    }
    public static void main(String[] args) {

    }
}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};

