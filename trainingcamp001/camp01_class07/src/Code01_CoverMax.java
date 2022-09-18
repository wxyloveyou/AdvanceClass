import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-09-14 22:56
 * @Info :  最长线段覆盖
 * 有若干个线段 比如： 线段1--5 ， 2--6 , 4--9 ,3--4
 * 在这些线段中，最多有几个线段能够重合（重合部分也算）
 */
public class Code01_CoverMax {

    public static int maxCover1(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int cover = 0;
        for (double p = min + 0.5; p < max; p += 1) {
            int cur = 0;
            for (int i = 0; i < lines.length; i++) {
                if (lines[i][0] < p && lines[i][1] > p) {
                    cur++;
                }
            }
            cover = Math.max(cover, cur);
        }
        return cover;
    }

    public static int maxCover2(int[][] m) {
        Line[] line = new Line[m.length];
        for (int i = 0; i < m.length; i++) {
            line[i] = new Line(m[i][0], m[i][1]);
        }
        Arrays.sort(line, new StrarComparator());
        PriorityQueue<Line> heap = new PriorityQueue<>(new EndComparator());
        int max = 0;
        for (int i = 0; i < line.length; i++) {
            while (!heap.isEmpty() && heap.peek().end <= line[i].start) {
                heap.poll();
            }
            heap.add(line[i]);
            max = Math.max(max, heap.size());
        }
        return max;
    }
    public static class Line{
        public int start;
        public int end;

        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static class StrarComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.start - o2.start;
        }
    }

    public static class EndComparator implements Comparator<Line> {

        @Override
        public int compare(Line o1, Line o2) {
            return o1.end - o2.end;
        }
    }

    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * (N) + 1);
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 300;
        int testTimes = 1000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = maxCover1(lines);
            int ans2 = maxCover2(lines);
            if (ans1 != ans2) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("FINISH");

    }

}
