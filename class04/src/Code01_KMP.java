import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;

import java.util.ConcurrentModificationException;

/**
 * @author : WXY
 * @create : 2022-09-07 19:33
 * @Info : KMP算法
 * 两个字符串，一长一短，判断短的串是不是长的串的子串，
 */
public class Code01_KMP {

    //O(n)
    public static int getIndexOF(String s, String m) {
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }
        char[] str = s.toCharArray();
        char[] match = m.toCharArray();
        int x = 0;//str中当前比对到的位置
        int y= 0;//match中当前比对到的位置
        // M  M <= N  O(n)
        int[] next = getNextArray(match);
        //O(N)
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (y == 0) {//next[y] == -1
                x++;
            } else {
                y = next[y];
            }
        }
        return y == match.length ? x - y : -1;
    }

    //M O(M)
    private static int[] getNextArray(char[] match) {
        if (match.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[match.length];
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        //cn代表 ，cn位置的字符，是当前和i-1位置比较的字符
        //cn代表从哪个位置往前跳，  指i-1位置的信息
        int cn = 0;
        while (i < match.length) {
            if (match[i - 1] == match[cn]) { // 跳出来的时候
                next[i++] = ++cn;
//                next[i] = cn + 1;
//                i++;
//                cn++;
            } else if (cn > 0) {
                cn = next[cn];
            }else {
                next[i++] = 0;
            }
        }
        return next;
    }

    //for text
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }
    //Run
    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOF(str, match) != str.indexOf(match)) {
                System.out.println("BUG BUG BUG!!!");
            }
        }
        System.out.println("FINISH");

    }



}
