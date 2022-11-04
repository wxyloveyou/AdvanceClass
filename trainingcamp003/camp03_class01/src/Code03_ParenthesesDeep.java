import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

/**
 * @author : WXY
 * @program : AdvanceClass
 * @className ：Code03_ParenthesesDeep
 * @create : 2022/11/4:19:49
 * @info :  Code03_ParenthesesDeep
 **/
public class Code03_ParenthesesDeep {
    /// 判断字符串是否合法
    public static boolean isValid(char[] str) {
        if (str == null || str.length == 0) {
            return false;
        }
        int status = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != ')' && str[i] != '(') {
                return false;
            }
            if (str[i] == ')' && --status < 0) {
                return false;
            }
            if (str[i] == '(') {
                status++;
            }
        }
        return status == 0;
    }

    //有效括号的最大嵌套深度
    public static int deep(String s) {
        char[] str = s.toCharArray();
        if (!isValid(str)) {
            return 0;
        }
        int count = 0;
        int max = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == '(') {
                max = Math.max(max, ++count);
            } else {
                count--;
            }
        }
        return max;
    }

    //括号的最长有效长度
    public static int maxLength(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        int pre = 0;
        int ans = 0;
        char[] str = s.toCharArray();
        int[] dp = new int[str.length];
        for (int i = 1; i < str.length; i++) {
            if (str[i] == ')') {
                pre = i - dp[i - 1] - 1;
                if (pre >= 0 && str[pre] == '(') {
                    dp[i] = dp[i - 1] + 2 + (pre > 0 ? dp[pre - 1] : 0);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String test = "((()))()()()()()()()";
     //   System.out.println(deep(test));
        System.out.println(maxLength(test));

    }
}
