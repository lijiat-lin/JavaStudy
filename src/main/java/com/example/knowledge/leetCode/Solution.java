package com.example.knowledge.leetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
     * @param s
     * @return
     */
    public static  String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        String ans = "";
        for (int i = 0; i < n; ++i) {
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
        if(s.length() == 1){
            return true;
        }
        int[] charsCount = new int[128];
        int count = s.length()/2 == 0?0:1;
        for (int i = 0; i < s.toCharArray().length; i++) {
            charsCount[s.charAt(i)]++;
        }
        for (int i = 0; i < charsCount.length; i++) {
            if(charsCount[i]%2 == 1){
                if(count==0){
                    return false;
                }else{
                    count--;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(canPermutePalindrome("aaabb"));
    }
}
