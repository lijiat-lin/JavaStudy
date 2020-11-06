package com.example.knowledge.leetCode.solution100;

import java.util.*;

/**
 * 127.单词接龙
 * 给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则：
 *
 * 每次转换只能改变一个字母。
 * 转换过程中的中间单词必须是字典中的单词。
 * 说明:
 *
 * 如果不存在这样的转换序列，返回 0。
 * 所有单词具有相同的长度。
 * 所有单词只由小写字母组成。
 * 字典中不存在重复的单词。
 * 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。
 * 输入:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * 输出: 5
 *
 * 解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 *      返回它的长度 5。
 *
 * @program: knowledge
 * @description:
 * @author: zhangjialin
 * @create: 2020-11-05 10:38
 */
public class Solution127 {
    /**
     * 为每个单词分配一个id
     */
    static Map<String,Integer> wordId = new HashMap<>();
    /**
     * 每个单词和别的单词构建双向图
     */
    static List<List<Integer>> edge = new ArrayList<>();
    /**
     * 单词节点的数量
     */
    static int nodeNum = 0;

    /**
     * 方法一：广度优先搜索 + 优化建图
     * 对于字典表中的所有单词要构建一个图
     * 为每个单词分配一个id
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        for(String word : wordList){
            addEdge(word);
        }
        addEdge(beginWord);
        if(!wordId.containsKey(endWord)){
            //字典表中不包含endWord，无法转换
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis,Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        int endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(beginId);
        //从beginId开始广度遍历整个图
        while (!queue.isEmpty()){
            //获取当前队列中的单词映射的id
            int x = queue.poll();
            //如果id为endWord则返回到这个id的 边的数量/2+1
            if(x == endId){
                return dis[endId]/2+1;
            }
            //循环这个单词id节点所连接的边
            for(int it : edge.get(x)){
                if(dis[it] == Integer.MAX_VALUE){
                    //如果这个节点还未访问到
                    //则这个节点的边数就是连接这个节点的节点边数加1
                    dis[it] = dis[x]+1;
                    //将当前节点放入队列，广度搜索
                    queue.offer(it);
                }
            }
        }
        return 0;
    }

    /**
     * 方法二：双向广度优先搜索
     * 分别从beginWord和endWord开始搜索
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public static int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        for(String word:wordList){
            addEdge(word);
        }
        addEdge(beginWord);
        if(!wordId.containsKey(endWord)){
            return 0;
        }
        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin,Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<>();
        queBegin.offer(beginId);


        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd,Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty()&& !queEnd.isEmpty()){
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; i++) {
                int nodeBegin = queBegin.poll();
                if(disEnd[nodeBegin]!=Integer.MAX_VALUE){
                    return (disBegin[nodeBegin]+disEnd[nodeBegin])/2+1;
                }
                for (int it:edge.get(nodeBegin)){
                    if(disBegin[it] == Integer.MAX_VALUE){
                        disBegin[it] = disBegin[nodeBegin]+1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize  = queEnd.size();
            for (int i = 0; i < queEndSize; i++) {
                int nodeBegin = queEnd.poll();
                if(disBegin[nodeBegin]!=Integer.MAX_VALUE){
                    return (disBegin[nodeBegin]+disEnd[nodeBegin])/2+1;
                }
                for (int it:edge.get(nodeBegin)){
                    if(disEnd[it] == Integer.MAX_VALUE){
                        disEnd[it] = disEnd[nodeBegin]+1;
                        queEnd.offer(it);
                    }
                }
            }


        }
        return 0;
    }

    /**
     * 添加单词节点之间的边
     * 将单词中的每一个字符都替换一次 *
     * 例如hot，可以替换为 *ot,h*t,ho*
     * hog,可以替换为 *og,h*g,ho*,
     * hot和hog都有ho*,则这两个单词之间可以建立一条边
     *
     * 举例：
     *  单词 hot在addEdge执行完之后，在edge中的存储如下：
     *      hot     [*ot,h*t,ho*]
     *      *ot     [hot]
     *      h*t     [hot]
     *      ho*     [hot]
     *  单词 hog 在addEdge执行完之后，在edge中的存储如下：
     *      hog     [*og,h*g,ho*]
     *      *og     [hog]
     *      h*g     [hog]
     *      ho*     [hog]
     *  这样ho* 的数组中就会存在[hot,hog],hot和hog自然也就关联起来。
     * @param word
     */
    private static void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            char temp = array[i];
            array[i] = '*';

            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = temp;
        }
    }

    /**
     * 添加单词到单词的哈希表中
     * 每个单词设定对应的id
     * 并在图中为该单词的节点添加新的集合，集合中的数据存放的是可以和这个单词进行转换的单词的集合
     * @param word
     */
    private static void addWord(String word) {
        if(!wordId.containsKey(word)){
            wordId.put(word,nodeNum++);
            edge.add(new ArrayList<>());
        }
    }

    public static void main(String[] args) {

        ladderLength1("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog"));
    }

}
