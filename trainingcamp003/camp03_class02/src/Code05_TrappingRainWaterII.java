import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : WXY
 * @program : AdvanceClass
 * @className ：Code05_TrappingRainWaterII
 * @create : 2022/11/9:21:25
 * @info : 二维积水问题
 * * 给定一个正整数二维数组arr，把arr想象成一个平面图。返回这个平面图如果装水，能装下几格水？
 **/
public class Code05_TrappingRainWaterII {
    public static class Node{
        public int value;
        public int row;
        public int col;

        public Node(int value, int row, int col) {
            this.value = value;
            this.row = row;
            this.col = col;
        }
    }

    //从小到大排序
    public static class NodeComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.value - o2.value;
        }
    }

    public static int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap == null || heightMap[0].length == 0) {
            return 0;
        }
        int N = heightMap.length;
        int M = heightMap[0].length;
        //inEnter[i][j] == true  之前是进过，
        // false 是没有进过
        boolean[][] isEnter = new boolean[N][M];
        //小根堆
        PriorityQueue<Node> heap = new PriorityQueue<>(new NodeComparator());
        for (int col = 0; col < N - 1; col++) {
            isEnter[0][col] = true;
            heap.add(new Node(heightMap[0][col], 0, col));
        }
        for (int row = 0; row < N - 1; row++) {
            isEnter[row][M - 1] = true;
            heap.add(new Node(heightMap[row][M - 1], row, M - 1));
        }
        for (int col = M - 1; col > 0; col--) {
            isEnter[N - 1][col] = true;
            heap.add(new Node(heightMap[N - 1][col], N - 1, col));
        }
        for (int row = N - 1; row > 0; row--) {
            isEnter[row][0] = true;
            heap.add(new Node(heightMap[row][0], row, 0));
        }


        int water = 0;//每个位置的水量，累加到water上去
        int max = 0; //每个node在弹出的时候，如果value更大，更新max，否则max的值维持不变
        while (!heap.isEmpty()) {
            Node cur = heap.poll();
            max = Math.max(max, cur.value);
            int r = cur.row;
            int c = cur.col;
            if (r > 0 && !isEnter[r - 1][c]) { //如果有上面的位置并且上面位置没进过堆
                water += Math.max(0, max - heightMap[r - 1][c]);
                isEnter[r - 1][c] = true;
                heap.add(new Node(heightMap[r - 1][c], r - 1, c));
            }

            if (r < N - 1 && !isEnter[r + 1][c]) {
                water += Math.max(0, max - heightMap[r + 1][c]);
                isEnter[r + 1][c] = true;
                heap.add(new Node(heightMap[r + 1][c], r + 1, c));
            }
            if (c > 0 && !isEnter[r][c - 1]) {
                water += Math.max(0, max - heightMap[r][c - 1]);
                isEnter[r][c - 1] = true;
                heap.add(new Node(heightMap[r][c - 1], r, c - 1));
            }
            if (c < M - 1 && !isEnter[r][c + 1]) {
                water += Math.max(0, max - heightMap[r][c + 1]);
                isEnter[r][c + 1] = true;
                heap.add(new Node(heightMap[r][c + 1], r, c + 1));
            }
        }
        return water;
    }

}
