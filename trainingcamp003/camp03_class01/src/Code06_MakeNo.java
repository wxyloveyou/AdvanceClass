import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;

/**
 * @author : WXY
 * @program : AdvanceClass
 * @className ：Code06_MakeNo
 * @create : 2022/11/6:14:09
 * @info :  [i] + [j]  != [k] * 2
 * //生成长度为size的达标数组 , i ,j ,k
 * // 达标：对于任意的 i<k<j，满足   [i] + [j]  != [k] * 2
 **/
public class Code06_MakeNo {
    public static int[] makeNo(int size) {
        if (size == 1) {
            return new int[] { 1 };
        }
        // size
        // 一半长达标来
        // 7   :   4
        // 8   :   4
        // [4个奇数] [3个偶]
        int halfSize = (size + 1) / 2;
        int[] base = makeNo(halfSize);
        //base -->  等长奇数达标
        //base -->  等长偶数达标
        int[] ans = new int[size];
        int index = 0;
        for(; index < halfSize;index++) {
            ans[index] = base[index] * 2 - 1;
        }
        for(int i = 0 ;index < size;index++,i++) {
            ans[index] = base[i] * 2;
        }
        return ans;
    }


    //检验函数
    public static boolean isValid(int[] arr) {
        int N = arr.length;
        for (int i = 0; i < N; i++) {
            for (int k = i + 1; k < N; k++) {
                for (int j = k + 1; j < N; j++) {


                    if (arr[i] + arr[j] == 2 * arr[k]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("test Begin");
        for (int i = 1; i < 1100; i++) {
            int[] arr = makeNo(i);
            if (!isValid(arr)) {
                System.out.println("BUG BUG");
            }
        }
        System.out.println("test End");

    }
}
