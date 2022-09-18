/**
 * @author : WXY
 * @create : 2022-09-10 22:16
 * @Info :蓄水池算法
 *
 *
 */
public class Code02_ReservoirSampling {

    public static class RandomBox{
        private int[] bag;
        private int N;
        private int count;

        public RandomBox(int capacity) {
            bag = new int[capacity];
            N = capacity;
            count = 0;
        }

        private int rand(int max) {
            return (int) ((Math.random() * max) + 1);
        }

        public void add(int num) {
            count++;
            if (count < N) {
                bag[count - 1] = num;
            } else {
                if (rand(count) <= N) {
                    bag[rand(N) - 1] = num;
                }
            }
        }

        public int[] choice() {
            int[] ans = new int[N];
            for (int i = 0; i < N; i++) {
                ans[i] = bag[i];
            }
            return ans;
        }

    }

    public static void main(String[] args) {
        int all = 100;
        int choose = 10;
        int testTimes = 10000;
        int[] counts = new int[all + 1];
        System.out.println("test Begin");
        for (int i = 0; i < testTimes; i++) {
            RandomBox box = new RandomBox(choose);
            for (int num = 1; num <= all; num++) {
                box.add(num);
            }
            int[] ans = box.choice();
            for (int j = 0; j < ans.length; j++) {
                counts[ans[j]]++;
            }

        }
        for (int i = 0; i < counts.length; i++) {
            System.out.println(i + " times : " + counts[i]);
        }
    }
}
