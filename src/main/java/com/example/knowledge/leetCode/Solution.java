package com.example.knowledge.leetCode;

import net.minidev.json.JSONObject;
import org.apache.commons.lang3.ArrayUtils;

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


    /**
     * 给定 S 和 T 两个字符串，当它们分别被输入到空白的文本编辑器后，判断二者是否相等，并返回结果。 # 代表退格字符。
     *
     * 注意：如果对空文本输入退格字符，文本继续为空。
     *
     * 输入：S = "ab#c", T = "ad#c"
     * 输出：true
     * 解释：S 和 T 都会变成 “ac”。
     *
     * 你可以用 O(N) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
     * 解题思路：
     * 1.获取两个字符串最终的结果进行比较   时间复杂度O(N) 空间复杂度O(N)
     * @param S
     * @param T
     * @return
     */
    public static boolean backspaceCompare(String S, String T) {

        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;
        for (int i = 0; i < S.length(); i++) {
            if('#'== S.charAt(i)){
                if(index!=0){
                    stringBuilder.deleteCharAt(index-1);
                    index = index-1;
                }

            }else{
                stringBuilder.append(S.charAt(i));
                index++;
            }
        }
        StringBuilder stringBuilder1 = new StringBuilder();
        int index1 = 0;
        for (int i = 0; i < T.length(); i++) {
            if('#'== T.charAt(i)){
                if(index1!=0){
                    stringBuilder1.deleteCharAt(index1-1);
                    index1 = index1-1;
                }
            }else{
                stringBuilder1.append(T.charAt(i));
                index1++;
            }
        }

        if (stringBuilder.toString().equals(stringBuilder1.toString())){
            return true;
        }else{
            return false;
        }

    }

    /**
     * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
     * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
     *
     * 输入：leaves = "rrryyyrryyyrr"
     * 输出：2
     * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
     *
     * @param leaves
     * @return
     */
    public int minimumOperations(String leaves) {


        return 0;
    }


    /**
     * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
     * 输入：[-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     *
     * @param A
     * @return
     */
    public static int[] sortedSquares(int[] A) {
        int[] result = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            if(A[i]<0){
                result[i] = (int)Math.pow(0-A[i],2);
            }else{
                result[i] = (int)Math.pow(A[i],2);
            }
        }
        Arrays.sort(result);
        return result;
    }

    /**
     * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
     * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
     *
     * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
     *
     * 解题思路：
     * 遍历链表将数据存储在数组，遍历数组，重新建立节点间的关系  时间复杂度O(N) 空间复杂度O(N)
     * @param head
     */
    public static void reorderList(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node!=null){
            count++;
            node = node.next;
        }

        ListNode[] listNodes = new ListNode[count];
        node = head;
        int index = 0;
        while (node!=null){
            listNodes[index] = node;
            node = node.next;
            index++;
        }

        for (int i = 0; i < listNodes.length/2; i++) {
            listNodes[i].next = listNodes[listNodes.length-i-1];
            listNodes[listNodes.length-i-1].next = listNodes[i+1];
        }
        if(count%2 == 0){
            listNodes[listNodes.length/2].next = null;
        }else{
            listNodes[listNodes.length/2].next = null;
            listNodes[listNodes.length/2+1].next = listNodes[listNodes.length/2];
        }

        System.out.println(head);

    }


    /**
     * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
     *
     * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
     *
     * 输入：name = "alex", typed = "aaleex"
     * 输出：true
     * 解释：'alex' 中的 'a' 和 'e' 被长按。
     *
     * @param name
     * @param typed
     * @return
     */
    public static boolean isLongPressedName(String name, String typed) {

        if(name.length()==0 && typed.length() == 0){
            return true;
        }
        if(name.length()==0 && typed.length() != 0){
            return false;
        }
        if(name.length()!=0 && typed.length() == 0){
            return false;
        }
        if(typed.length()<name.length()) {
            return false;
        }


        int index = 0;
        for (int i = 0; i < typed.length(); i++) {
            if(typed.charAt(i) == name.charAt(index)){
                if(index<name.length()-1){
                    index++;

                }
            }else{
                if(typed.charAt(i) != name.charAt(index-1) && index >= 1){
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一个字母只会出现在其中的一个片段。返回一个表示每个字符串片段的长度的列表。
     * 输入：S = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
     * 解析：
     * 遍历字符串，获取每一个字符在字符串中的最后一位的位置，
     * 再遍历字符串，定义截取的字符串
     * @param S
     * @return
     */
    public static List<Integer> partitionLabels(String S) {

        List<Integer> list = new ArrayList<>();
        int[] counts = new int[26];
        for (int i = 0; i < S.length(); i++) {
            counts[S.charAt(i)-'a'] = i;
        }
        int n = -1,m = -1;
        for (int i = 0; i < S.length(); i++) {


            int index = counts[S.charAt(i)-'a'];
            m = index>m?index:m;
            if(i == m){
                list.add(m-n);
                n = m;
            }
        }
        return list;

    }

    /**
     * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
     *
     * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
     *
     * 以数组形式返回答案。
     *
     * 解析：
     * 暴力解法：时间复杂度O(N²)
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] count = new int[101];
        for (int i = 0; i < nums.length; i++) {
            count[nums[i]]++;
        }

        int pre = 0;
        int cur = 0;
        for (int i = 1; i < count.length; i++) {
            cur = count[i];
            count[i] = count[i-1]+pre;

        }


        return count;

    }

    /**
     * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
     *
     * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        return 0.00;

    }

    /**
     * 给定一个二叉树，返回它的 前序 遍历。
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null){
            return list;
        }

        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (!stack.isEmpty()||node!=null ){
            while (node!=null){
                list.add(node.val);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        return list;
    }

    public List<String> fizzBuzz(int n) {
        List<String> strings = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if(i%3 == 0 && i%5 == 0){
                strings.add("FizzBuzz");
            }else if(i%3 == 0){
                strings.add("Fizz");
            }else if(i%5 == 0){
                strings.add("Buzz");
            }else{
                strings.add(String.valueOf(i));
            }
        }
        return strings;
    }


    /**
     * 给你一个整数数组 arr，请你帮忙统计数组中每个数的出现次数。
     *
     * 如果每个数的出现次数都是独一无二的，就返回 true；否则返回 false。
     *
     * 解题思路：
     * 遍历数组，将每个数字出现的次数存储起来
     * @param arr
     * @return
     */
    public static boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer,Integer> map = new HashMap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            if(map.get(arr[i]) == null){
                map.put(arr[i],1);
            }else{
                map.replace(arr[i],map.get(arr[i])+1);
            }
        }

        HashMap<Integer,Integer> count = new HashMap<>(map.size());
        for(Integer v : map.values()){
            if(count.containsKey(v)){
                return false;
            }else{
                count.put(v,v);
            }
        }


        return true;
    }

    /**
     * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。
     * CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
     *
     * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
     *
     * 你需要计算完成所有任务所需要的最短时间。
     *
     * 输入：tasks = ["A","A","A","B","B","B"], n = 2
     *
     * 输出：8
     *
     * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
     *      在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
     *
     * 解析：
     *  1、获取数组中每个任务所包含的数量
     *  2、根据n的数量给每个任务安排相应的间隔
     *  3、获取一共有多少个任务种类
     *  4、
     *      循环数组数据的数量，每次循环的时候验证以下三种情况：
     *      第一种情况：任务的类型大于冷却时间n
     *          - 循环冷却时间n次，将前n个任务的数量减一
     *          - 数组数据的数量减一
     *          - 执行时间+1
     *      第二种情况：任务的类型小于冷却时间n
     *          - 循环任务类型的时间，将任务的数量减一
     *          - 数组数据数量减一
     *          - 循环完后，数据数量不减，执行时间+1
     *      第三种情况：任务的类型等于冷却时间n
     *          - 循环冷却时间n次，将n个任务的数量减一
     *          - 数组数据的数量减一
     *          - 执行时间+1
     * @param tasks
     * @param n
     * @return
     */
    public static int leastInterval(char[] tasks, int n) {
        int length = tasks.length;
        HashMap<Character,Integer> count = new HashMap<>(26);
        for(char c:tasks){
            if(count.containsKey(c)){
                count.put(c,count.get(c)+1);
            }else{
                count.put(c,1);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Character character: count.keySet()){
            stringBuilder.append(character);
        }
        int times = 0;
        while (length>0){
            int taskIndex = 0;
            for (int i = 0; i < n; i++) {
                if(taskIndex<stringBuilder.length()){
                    //任务数量减一
                    count.replace(stringBuilder.charAt(taskIndex),count.get(stringBuilder.charAt(taskIndex))-1);
                    //数组数量减一
                    length--;
                    //时间+1
                    times++;
                    //如果任务数量已经为0，则map移除这个任务，
                    if(count.get(stringBuilder.charAt(taskIndex)) == 0){
                        count.remove(stringBuilder.charAt(taskIndex));
                        stringBuilder.deleteCharAt(taskIndex);
                    }
                    taskIndex++;
                }else{
                    //时间+1
                    times++;
                }
            }
        }
        return times;

    }

    public static int leastInterval1(char[] tasks, int n) {
        int[] map = new int[26];
        for (char c: tasks)
            map[c - 'A']++;
        Arrays.sort(map);
        int time = 0;
        while (map[25] > 0) {
            int i = 0;
            while (i <= n) {
                if (map[25] == 0)
                    break;
                if (i < 26 && map[25 - i] > 0)
                    map[25 - i]--;
                time++;
                i++;
            }
            Arrays.sort(map);
        }
        return time;
    }

    public static  int leastInterval2(char[] tasks, int n) {
        int[] map = new int[26];
        for(char c: tasks){
            map[c-'A']++;
        }
        Arrays.sort(map);
        int max_val = map[25]-1;
        int slots = max_val*n;
        for (int i = 24; i >= 0 && map[i]>0 ; i--) {
            slots -= Math.min(map[i],max_val);
        }

        return slots>0?slots+tasks.length:tasks.length;
    }

    /**
     * 给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
     *
     * 例如，从根到叶子节点路径 1->2->3 代表数字 123。
     *
     * 计算从根到叶子节点生成的所有数字之和。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * 解析：
     * 遍历树节点，通过前序遍历得到每一个路径的数字
     *
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        return dfs(root,0);
    }

    private static int dfs(TreeNode root, int i) {
        if (root == null){
            return 0;
        }
         int sum = i*10 +root.val;
        if(root.left == null && root.right == null){
            return sum;
        }else{
            return dfs(root.left,sum)+dfs(root.right,sum);
        }
    }

    /**
     * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
     *
     * '.' 匹配任意单个字符
     * '*' 匹配零个或多个前面的那一个元素
     * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
     *
     *  解析：
     *  定义两个指针分别指向两个字符串，依次向后匹配
     *  如果字符不同并且字符串P的字符不为'.'和'*' 则返回false
     *  如果字符不用并且字符串P的字符为'.'，指针依次后移
     *
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {

        if(p.length()>s.length()){
            return false;
        }
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            if(index >= p.length()){
                return false;
            }
            if(s.charAt(i) == p.charAt(index)){
                index++;
            }else{
                if('.' == p.charAt(index)){
                    index++;
                }
                if('*' == p.charAt(index)){

                }
            }
        }

        return true;
    }


    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && wordDictSet.contains(s.substring(j,i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public static List<String> wordBreak1(String s, List<String> wordDict) {
        Map<Integer,List<List<String>>> map = new HashMap<>();
        Set<String> hashSet = new HashSet<>(wordDict);
        List<List<String>> wordBreaks = backTrack(s,s.length(),hashSet,0,map);
        List<String> list = new ArrayList<>();
        for(List<String> workBreak : wordBreaks){
            list.add(String.join(" ",workBreak));
        }
        return list;
    }

    /**
     *
     * @param s 字符串
     * @param length 字符串大小
     * @param wordSet 字典集合
     * @param index 下标值
     * @param map 相应下标值所对应的  单词集合
     * @return
     */
    private static List<List<String>> backTrack(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        if(!map.containsKey(index)){
            List<List<String>> wordBreaks = new LinkedList<List<String>>();
            if(index == length){
                wordBreaks.add(new LinkedList<>());
            }
            for (int i = index+1; i <= length ; i++) {
                //截取index 到 i 的字符串
                String word = s.substring(index,i);
                //校验该字符串是否在单词中
                if(wordSet.contains(word)){
                    //获取i之后的字符串的单词组成集合
                    List<List<String>> nextWordBreaks = backTrack(s,length,wordSet,i,map);
                    //循环之后字符串的 单词组成集合
                    for(List<String> nextWordBreak:nextWordBreaks){
                        LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index,wordBreaks);
        }
        return map.get(index);
    }

    public static int[]  intersection(int[] nums1, int[] nums2) {

        Set<Integer> set1 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {

            set1.add(nums1[i]);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            set2.add(nums2[i]);
        }
        int[] result = new int[Math.min(set1.size(),set2.size())];
        int index = 0;
        if(set1.size()<=set2.size()){
            for(Integer integer: set1){
                if(set2.contains(integer)){
                    result[index++] = integer;
                }
            }
        }else{
            for(Integer integer: set2){
                if(set1.contains(integer)){
                    result[index++] = integer;
                }
            }
        }
        return Arrays.copyOf(result,index);


    }
    public boolean validMountainArray(int[] A) {
        int max = 0;
        int index = 0;
        if(A.length<3){
            return false;
        }
        for (int i = 0; i < A.length; i++) {
            if(A[i]>=max){
                max = A[i];
                index = i;
            }
        }
        if(index == 0|| index == A.length-1){
            return false;
        }
        for (int i = 1; i < index; i++) {
            if(A[i]<A[i-1]){
                return false;
            }
        }
        for (int i = index+1; i <A.length; i++) {
            if(A[i]>A[i-1]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] result = intersection(new int[]{4,9,5},new int[]{9,4,9,8,4});
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }

    }
}

 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(){}
     TreeNode(int x) { val = x; }
     TreeNode(int val,TreeNode left,TreeNode right){
         this.val = val;
         this.left = left;
         this.right = right;
     }
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

