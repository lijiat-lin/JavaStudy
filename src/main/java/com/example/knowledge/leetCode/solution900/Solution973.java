package com.example.knowledge.leetCode.solution900;

import java.util.*;


/**
 * @program: knowledge
 * @description: 最接近原点的K个点（中等难度）
 * @author: zhangjialin
 * @create: 2020-11-10 10:17
 */
public class Solution973 {

    /**
     *
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * （这里，平面上两点之间的距离是欧几里德距离。）
     *
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     * 示例 1：
     *
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest(int[][] points, int K) {

        int n = points.length;
        Map<Integer,Double> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            map.put(i,Math.sqrt(Math.pow(points[i][0],2)+Math.pow(points[i][1],2)));
        }
        List<Map.Entry<Integer,Double>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Double>>() {
            @Override
            public int compare(Map.Entry<Integer, Double> o1, Map.Entry<Integer, Double> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        int count = 0;
        int[][] ans = new int[K][2];
        for(Map.Entry<Integer,Double> s:list){
            if (count>=K){
                break;
            }
            ans[count++] = points[s.getKey()];
        }
        return ans;

    }

    /**
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]);
            }
        });
        return Arrays.copyOfRange(points, 0, K);
    }

    /**
     * 方法二：优先队列
     * 思路和算法
     *
     * 我们可以使用一个优先队列（大根堆）实时维护前 KK 个最小的距离平方。
     *
     * 首先我们将前 KK 个点的编号（为了方便最后直接得到答案）以及对应的距离平方放入优先队列中，随后从第 K+1K+1 个点开始遍历：如果当前点的距离平方比堆顶的点的距离平方要小，就把堆顶的点弹出，再插入当前的点。当遍历完成后，所有在优先队列中的点就是前 KK 个距离最小的点。
     *
     * 不同的语言提供的优先队列的默认情况不一定相同。在 C++ 语言中，优先队列即为大根堆，但在 Python 语言中，优先队列为小根堆，因此我们需要在优先队列中存储（以及比较）距离平方的相反数。
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest2(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //o1-o2 升序   o2-o1 降序
                return o2[0]-o1[0];
            }
        });
        for (int i = 0; i <K ; i++) {
            pq.offer(new int[]{points[i][0]*points[i][0]+points[i][1]*points[i][1],i});
        }
        int n = points.length;
        for (int i = K; i < n; i++) {
            int dist = points[i][0]*points[i][0]+points[i][1]*points[i][1];
            if(dist<pq.peek()[0]){
                pq.poll();
                pq.offer(new int[]{dist,i});
            }
        }
        int[][] ans = new int[K][2];
        for (int i = 0; i < K; i++) {
            ans[i] = points[pq.poll()[1]];
        }
        return ans;
    }

    Random rand = new Random();
    /**
     * 采用快速排序思想，对数据排序
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest3(int[][] points, int K) {
        int n = points.length;
        randomSelect(points,0,n-1,K);
        return Arrays.copyOfRange(points,0,K);

    }

    private void randomSelect(int[][] points, int left, int right, int k) {
        //随机得到区间的一个值
        int pivotId = left+rand.nextInt(right-left+1);
        //获取这个值的欧几里得距离
        int pivot = points[pivotId][0]*points[pivotId][0]+points[pivotId][1]*points[pivotId][1];
        //将这个随机的数据交换到数组区间的最后面，以便以下的比较交换
        swap(points,right,pivotId);
        //从区间的最左边界开始交换
        int i = left - 1;
        for (int j = left; j < right; j++) {
            int dist = points[j][0]*points[j][0]+points[j][1]*points[j][1];
            //只要值小于随机计算的pivot，则就将数据放到区间的最左边
            if(dist<=pivot){
                ++i;
                swap(points,i,j);
            }
        }
        ++i;
        //最后再将pivot这个值交换到第一个大于它的位置
        swap(points,i,right);

        if(k<i-left+1){
            //表示第K大的数据在左区间
            randomSelect(points,left,i-1,k);
        }else if(k>i-left+1){
            //表示第K大的数据在右区间
            randomSelect(points,i+1,right,k-(i-left+1));
        }
    }

    private void swap(int[][] points,int right,int pivotId){
        int[] temp = points[right];
        points[right]  = points[pivotId];
        points[pivotId] = temp;
    }


    /**
     *
     * @param points
     * @param K
     * @return
     */
    public int[][] kClosest4(int[][] points, int K) {
        int n = points.length;
        //随机选择交换数据
        randomSelect1(points,0,n-1,K);
        return Arrays.copyOfRange(points,0,K);

    }

    private void randomSelect1(int[][] points, int left, int right, int k) {
        int pivotId = left+rand.nextInt(right-left+1);
        int pivot = points[pivotId][0]*points[pivotId][0]+points[pivotId][1]*points[pivotId][1];
        swap(points,right,pivotId);
        int i = left-1;
        for (int j = left; j < right; j++) {
            int dist = points[j][0]*points[j][0]+points[j][1]*points[j][1];
            if(dist<=pivot){
                i++;
                swap(points,i,j);
            }
        }
        i++;
        swap(points,i,right);
        if(k<i-left+1){
            randomSelect1(points,left,i-1,k);
        }else if(k>i-left+1){
            randomSelect1(points,i+1,right,k-(i-left+1));
        }
    }
}
