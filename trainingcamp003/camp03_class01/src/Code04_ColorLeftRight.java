/**
 * @author : WXY
 * @program : AdvanceClass
 * @className ：Code04_ColorLeftRight
 * @create : 2022/11/4:20:12
 * @info : Code04_ColorLeftRight
 * <p>
 * R,G字符组成的串，改最少的字符，使得 R 在 G 的左边
 * RGRGR -> RRRGG
 * 返回修改的数量，
 **/
public class Code04_ColorLeftRight {
    public static int minPaint(String s) {
        if (s == null || s.length() < 2) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int rAll = 0;
        for (int i = 0; i < N; i++) {
            rAll += str[i] == 'R' ? 1 : 0;
        }
        int ans = rAll;
        int left = 0;
        for (int i = 0; i < N ; i++) { // 0..i 左侧 n-1..N-1
            left += str[i] == 'G' ? 1 : 0;
            rAll -= str[i] == 'R' ? 1 : 0;
            ans = Math.min(ans, left + rAll);
        }
        // 0...N-1 左全部 右无
        //ans = Math.min(ans, left + (str[N - 1] == 'G' ? 1 : 0));
        return ans;
    }

    public static void main(String[] args) {
        String test = "GGGG";
        int ans = minPaint(test);
        System.out.println(ans);
    }
}
