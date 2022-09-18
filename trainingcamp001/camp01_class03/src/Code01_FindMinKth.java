import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : WXY
 * @create : 2022-09-10 15:56
 * @Info : 在arr无序数组中，怎么求第 K 小的数据
 *
 * 写了三种方式，
 * 1、利用大跟堆的方式
 * 2、利用快排，改写快排
 * 3、利用bfprt算法
 */
public class Code01_FindMinKth {
    public static class MaxHeapCompararot implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    //利用大根堆，时间复杂度O(N * logK)
    //peek函数取出的是第一个元素(每次取出都是最大的)， 和arr中剩下的进行比较，如果arr中的小，就加入大根堆中
    //那么遍历完arr后，大根堆的第一个元素就是需要的第k小的数，大根堆中剩下的都比头小
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new MaxHeapCompararot());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    //改写快排的方式实现，时间复杂都O(N)
    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    private static int[] copyArray(int[] arr) {
        int[] ans = new int[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    //arr上第k小的数
    //process(arr,0,N - 1,k - 1）
    //arr[L..R] 范围上，如果排序的话(不去真的排序),找位于index位置的数
    //index[L...R]
    private static int process2(int[] arr, int L, int R, int index) {
        if (L == R) { // L == R == index
            return arr[L];
        }
        // 不止一个数，L + [ 0 ,R-L]
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else if (index > range[1]) {
            return process2(arr, range[1] + 1, R, index);
        }else {
            return arr[index];
        }
    }

    private static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, ++less, cur++);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    //for test
    private static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((Math.random() * maxSize) + 1)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxSize + 1));
        }
        return arr;
    }


    //利用bfprt算法，时间复杂度O(N)
    public static int minKth3(int[] arr, int k) {
        int[] array = copyArray(arr);
        return bfprt(array, 0, array.length - 1, k - 1);
    }

    private static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[L];
        }
        int piovt = medianOfMedians(arr, L, R);
        int[] range = partition(arr, L, R, piovt);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    private static int medianOfMedians(int[] arr, int l, int r) {
        int size = r - l + 1;
        int offset = size % 5 == 0 ? 0 : 1;
        int[] mArr = new int[size / 5 + offset];
        for (int team = 0; team < mArr.length; team++) {
            int teamFirst = l + team * 5;
            // L ... L + 4
            // L +5 ... L +9
            // L +10....L+14
            mArr[team] = getMedian(arr, teamFirst, Math.min(r, teamFirst + 4));
        }
        // marr中，找到中位数
        // marr(0, marr.len - 1,  mArr.length / 2 )
        return bfprt(mArr, 0, mArr.length - 1, mArr.length / 2);
    }

    private static int getMedian(int[] arr, int L, int R) {
        insertionSort(arr, L, R);
        return arr[(L + R) / 2];

    }

    public static void insertionSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }


    public static void main(String[] args) {
        int testTimes = 1000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) ((Math.random() * arr.length) + 1);
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("FINISH");
    }


}
