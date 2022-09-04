/**
 * @author : WXY
 * @create : 2022-09-04 20:04
 * @Info : 跳马问题，
 * 中国象棋中，马从（0,0） 跳到（x,y）位置，要跳k步，问有几种方法跳
 */
public class Test {
    public static int way1(int x,int y,int k ) {
        return f(x, y, k);
    }

    private static int f(int x, int y, int k) {
        if (k == 0) {
            return x == 0 && y == 0 ? 1 : 0;
        }
        if (x < 0 || y < 0 || x > 9 || y > 8) {
            return 0;
        }
        //有步数要走，x,y也是棋盘上的位置
        return f(x + 2, y - 1, k - 1) + f(x + 2, y + 1, k - 1) + f(x + 1, y + 2, k - 1) + f(x - 1, y + 2, k - 1)
                + f(x - 2, y + 1, k - 1) + f(x - 2, y - 1, k - 1) + f(x - 1, y - 2, k - 1) + f(x + 1, y - 2, k - 1);
    }


    public static int way2(int x, int y, int k) {
        int[][][] dp = new int[10][9][k + 1];//0--k
        dp[0][0][0] = 1;// dp[..][..][0] = 0
        for (int level = 1; level <= k ; level++) {
            //level层，x,y
            for (int i = 0; i < 10; i++) {//x的可能性
                for (int j = 0; j < 9; j++) {
                    dp[i][j][level] = getValue(dp, i + 2, j - 1, level - 1) + getValue(dp, i + 2, j + 1, level - 1)
                            + getValue(dp, i + 1, j + 2, level - 1) + getValue(dp, i - 1, j + 2, level - 1)
                            + getValue(dp, i - 2, j + 1, level - 1) + getValue(dp, i - 2, j - 1, level - 1)
                            + getValue(dp, i - 1, j - 2, level - 1) + getValue(dp, i + 1, j - 2, level - 1);
                }
            }

        }
        return dp[x][y][k];
    }

    private static int getValue(int[][][] dp, int x, int y, int k) {
        if (x < 0 || y < 0 || x > 9 || y > 8) {
            return 0;
        }
        return dp[x][y][k];
    }

    public static void main(String[] args) {
        int x= 8;
        int y = 8;
        int k = 6;
        System.out.println(way1(x, y, k));
        System.out.println(way2(x, y, k));
    }
}
