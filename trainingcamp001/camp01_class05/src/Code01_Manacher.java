/**
 * @author : WXY
 * @create : 2022-09-10 22:30
 * @Info : Manacher算法
 * 一个字符串中，最大的回文子串是多少
 */
public class Code01_Manacher {
    public static int manacher(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        //"12321" -> "#1#2#3#2#1#"
        char[] str = manacherString(s);
        //回文半径的大小
        int[] pArr = new int[str.length];
        int c = -1;
        //讲述中，R代表最右的扩成功的位置，代码中，最右的扩成功位置的，再下一个位置
        int R = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            // R第一个违规的位置，i>= R
            // i位置扩出来的答案，i位置扩的区域，至少是多大。
            pArr[i] = R > i ? Math.min(pArr[2 * c - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                c = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    private static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[2 * charArr.length + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    //for test
    public static int right(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = manacherString(s);
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            int L = i - 1;
            int R = i + 1;
            while (L >= 0 && R < str.length && str[L] == str[R]) {
                L--;
                R++;
            }
            max = Math.max(max, R - L - 1);
        }
        return max / 2;
    }

    //for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int testTimes = 1000;
        System.out.println("test Begin!");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            if (manacher(str) != right(str)) {
                System.out.println(str);
                System.out.println("manacher : " + manacher(str));
                System.out.println("right : " + right(str));
                System.out.println("BUG BUG BUG");
            }
        }
        System.out.println("FINISH");

    }
}
