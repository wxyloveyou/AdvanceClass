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
}
