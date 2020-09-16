package com.example.knowledge.leetCode;

import java.util.HashSet;
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

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abcbabcba"));
    }
}
