/**
 * @author : WXY
 * @create : 2022-09-10 15:35
 * @Info : 两个字符串str1 和 str2 ，判断str2是否是str1的循环字符串
 * a是否是b的循环字符串
 */
public class Code03_IsRotation {
    public static boolean isRotation(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }
        String b2 = b + b;
        return getIndexOf(b2, a) != -1;
    }

    private static int getIndexOf(String s, String m) {
        if (s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] ms = m.toCharArray();
        int stri = 0;
        int mi = 0;
        int[] next = getNextArray(ms);
        while (stri < str.length && mi < ms.length) {
            if (str[stri] == ms[mi]) {
                stri++;
                mi++;
            } else if (next[mi] == -1) {
                stri++;
            } else {
                mi = next[mi];
            }
        }
        return mi == ms.length ? stri - mi : -1;
    }

    private static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        int cn = 0;
        while (i < ms.length) {
            if (ms[i - 1] == ms[cn]) {
                next[i++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[i++] = 0;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        String str1 = "asdf";
        String str2 = "sdfa";
        System.out.println(isRotation(str1, str2));
    }
}
