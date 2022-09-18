import java.util.LinkedList;

/**
 * @author : WXY
 * @create : 2022-09-02 21:08
 * @Info : 所有小于Num的数的子数组的数量
 * 给定一个整型数组arr，和一个整数num
 * 某个arr中的子数组sub，如有想达标，必须满足
 * sub中的最大值 - sub中的最小值 <= num
 * 返回arr中达标的子数组的数量
 */
public class Code02_AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int L = 0;
        int R = 0;
        //[L..R) -> [0,0) 窗口内无数 [1,1)
        //[0,1) -> [0~0]
        int res = 0;
        while (L < arr.length) { //L是开头的位置，尝试每一个开头
            //如果此时窗口的开头是 i, 下面while工作：R向右扩到违规为止
            //违规：最大值 - 最小值 > num
            while (R < arr.length) {  // R是最后一个达标位置的再下一个
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                R++;
            }
            //R是最后一个达标位置的再下一个，第一个违规的位置
            res += R - L;
            if (qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if (qmin.peekFirst() == L) {
                qmin.pollFirst();
            }
            L++;
        }
        return res;
    }

    //for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    //for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(10);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }
}
