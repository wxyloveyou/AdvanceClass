/**
 * @author : WXY
 * @create : 2022-09-18 14:36
 * @Info : 是否是连续数的和问题
 *  定义一种数，：可以表示成若干（数量大于1）连续正数和的数，比如
 *  5 = 2 + 3
 *  12 = 3 + 4 + 5
 *  2不是 ： 2 = 1 + 1
 *  给定一个数N,返回是不是可以表示成若干个连续正数和的数
 */
public class Code03_MSumToN {
    public static boolean isMSum1(int num) {
        for (int i = 1; i <= num; i++) {
            int sum = i;
            for (int j = i + 1; j <= num; j++) {
                sum += j;
                if (sum > num) {
                    break;
                }
                if (sum == num) {
                    return true;
                }
            }
        }
        return false;
    }


    public static boolean isMSum2(int num) {
        if (num < 3) {
            return false;
        }
        return (num & (num - 1)) != 0;
    }


    public static void main(String[] args) {
        for (int i = 1; i < 200; i++) {
            System.out.println(i + " : " + isMSum1(i));
        }

        System.out.println("test begin");
        for (int i = 0; i < 300; i++) {
            boolean ans1 = isMSum1(i);
            boolean ans2 = isMSum2(i);
            if (ans1 != ans2) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("FINISH");

    }
}
