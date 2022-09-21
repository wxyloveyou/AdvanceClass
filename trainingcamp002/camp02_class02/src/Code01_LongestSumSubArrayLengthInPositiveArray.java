/**
 * @author : WXY
 * @create : 2022-09-20 19:35
 * @Info : 在非负arr数组中，子数组的累加和刚刚号等于sum的子数组最长是多长
 *  arr 数组是非负的，sum 随长度的增大而增大
 */
public class Code01_LongestSumSubArrayLengthInPositiveArray {

    public static int getMaxLength(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == k) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < k) {
                right++;
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    //for test
    public static int right(int[] arr, int k) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (valid(arr, i, j, k)) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
    }

    private static boolean valid(int[] arr, int L, int R, int k) {
        int sum = 0;
        for (int i = L; i <= R; i++) {
            sum += arr[i];
        }
        return sum == k;
    }

    //for test
    public static int[] generatePositiveArray(int size, int value) {
        int[] ans = new int[size];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * value) + 1;
        }
        return ans;
    }

    //for test
    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 1000;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generatePositiveArray(len, value);
            int k = (int) (Math.random() * value) + 1;
            int ans1 = getMaxLength(arr, k);
            int ans2 = right(arr, k);
            if (ans1 != ans2) {
                System.out.println("BUG BUG BUG");
                System.out.println(" K : " + k);
                System.out.println(ans1);
                System.out.println(ans2);
                break;
            }
        }
        System.out.println("FINISH");

    }
}
