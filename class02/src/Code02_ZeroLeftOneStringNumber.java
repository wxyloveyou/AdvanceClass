import sun.nio.cs.ext.MacHebrew;

import javax.xml.ws.EndpointReference;

/**
 * @author : WXY
 * @create : 2022-09-05 22:01
 * @Info :
 * 给定一个数N，想象只由0和1两种字符，组成的所有长度为N的字符串如果某个字符串,
 * 任何0字符的左边都有1紧挨着,认为这个字符串达标返回有多少达标的字符串
 * 题目在图片中，class02_code2中
 */
public class Code02_ZeroLeftOneStringNumber {

    public static int getNum1(int n) {
        if (n < 1) {
            return 0;
        }
        return process(n);
//        return process(i, n);
    }

    private static int process(int n) {
        if (n == 2) {
            return 2;
        }
        if (n == 1) {
            return 1;
        }
        return process(n - 1) + process(n - 2);
        //上面和下面注释部分写法等价，结果是一样的
/*
        if (i == n - 1) {
            return 2;
        }
        if (i == n) {
            return 1;
        }
        return process(i + 1, n) + process(i + 2, n);
        */
    }

    public static int getNum2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int pre = 1;
        int cur = 1;
        int tmp = 0;
        for (int i = 2; i < n + 1; i++) {
            tmp = cur;
            cur = cur + pre;
            pre = tmp;
        }
        return cur;
    }

    public static int getNum3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return n;
        }
        int[][] base = {{1, 1}, {1, 0}};
        int[][] res = matrixPower(base, n - 2);
        return 2 * res[0][0] + res[1][0];
    }

    public static int[][] matrixPower(int[][] m, int p) {
        int[][] res = new int[m.length][m[0].length];
        for (int i = 0; i < res.length; i++) {
            res[i][i] = 1;
        }

        // res = 矩阵中的1
        int[][] tmp = m;// 矩阵1次方
        for (; p != 0; p >>= 1) {
            if ((p & 1) != 0) {
                res = muliMatrix(res, tmp);
            }
            tmp = muliMatrix(tmp, tmp);
        }
        return res;
    }

    // 两个矩阵乘完之后的结果返回
    public static int[][] muliMatrix(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(getNum1(i));
            System.out.println(getNum2(i));
            System.out.println(getNum3(i));
            System.out.println("=================");
        }
    }

}
