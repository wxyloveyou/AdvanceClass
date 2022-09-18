import com.sun.deploy.config.WinPlatform;

/**
 * @author : WXY
 * @create : 2022-09-18 14:57
 * @Info : 原地旋转一个正方形矩阵
 */
public class Code04_RotateMatrix {

    public static void rotate(int[][] matrix) {
        //a 行 b 列， ，，c 行 d 列
        //左上        右下
        int a = 0;
        int b = 0;
        int c = matrix.length - 1;
        int d = matrix[0].length -1;
        while (a < c) {
            rotateEdge(matrix, a++, b++, c--, d--);
        }
    }

    private static void rotateEdge(int[][] m, int a, int b, int c, int d) {
        int temp = 0;
        for (int i = 0; i < d - b; i++) {
            temp = m[a][b + i];
            m[a][b+i] = m[c - i][b];
            m[c - i][b] = m[c][d - i];
            m[c][d - i] = m[a + i][d];
            m[a + i][d] = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        printMatrix(matrix);
        rotate(matrix);
        System.out.println("+++++++++++++++++");
        printMatrix(matrix);
    }
}
