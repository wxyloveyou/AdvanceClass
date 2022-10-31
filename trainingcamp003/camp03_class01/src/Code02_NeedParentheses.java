/**
 * @author : WXY
 * @create : 2022-10-31 20:58
 * @Info :  有效括号问题
 */
public class Code02_NeedParentheses {
    public static boolean vaild(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return true;
    }

    public static int needParentheses(String s) {
        char[] str = s.toCharArray();
        int count = 0;
        int need = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                count++;
            } else {
                if (count == 0) {
                    need++;
                } else {
                    count--;
                }

            }
        }
        return need + count;
    }

    public static void main(String[] args) {
        String s = ")))(((";
        int i = needParentheses(s);
        System.out.println(i);
    }

}
