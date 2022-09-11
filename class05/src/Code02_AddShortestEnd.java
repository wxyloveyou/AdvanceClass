/**
 * @author : WXY
 * @create : 2022-09-10 23:14
 * @Info : 一个字符串，在尾部添加最少长度的串使之整体成为最长的回文串
 */
public class Code02_AddShortestEnd {

    public static String shortestEnd(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        char[] str = manacherString(s);
        int c = -1;
        int R = -1;
        int maxContainsEnd = -1;
        int[] pArr = new int[str.length];
        for (int i = 0; i <= str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * c - i], R - i) : 1;

            while (i - pArr[i] > -1 && i + pArr[i] < str.length) {
                if (str[i - pArr[i]] == str[i + pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                c = i;
            }
            if (R == str.length) {
                maxContainsEnd = pArr[i];
                break;
            }
        }
        char[] res = new char[s.length() - maxContainsEnd + 1];
        for (int i = 0; i < res.length; i++) {
//            res[res.length - 1 - i] = str[2 * i + 1];
            res[res.length - 1 - i] = s.charAt(i);
        }
        return String.valueOf(res);
    }

    private static char[] manacherString(String s) {
        char[] str = s.toCharArray();
        char[] ans = new char[2 * str.length + 1];
        int index = 0;
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (i & 1) == 0 ? '#' : str[index++];
        }
        return ans;
    }

    public static void main(String[] args) {
        String str = "abcd12321";
        System.out.println(shortestEnd(str));
    }
}
