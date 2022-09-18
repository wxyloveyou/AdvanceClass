import java.security.ProtectionDomain;

/**
 * @author : WXY
 * @create : 2022-09-18 14:15
 * @Info :  吃草问题
 * 先手和后手 ，分别吃草， 每次只能吃 1 4 8 16.。份草
 * 问，有N份草，谁赢，先吃完草谁赢
 */
public class Code02_EatGrass {
    //n份青草放在一堆
    //先手和后手都是绝顶聪明
    //string "先手“ ”后手”
    public static String winner(int n) {
        //0   1   2   3    4   5
        //后  先  后   先   先   后
        if (n < 5) {
            return (n == 0 || n == 2) ? "后手" : "先手";
        }
        // n > 5
        int base = 1;//当前先手决定吃的草数
        //当前是先手再选
        while (base <= n) {
            //当前一共是N分草，，先手吃掉的是base份，n - base是留给后手的
            //母过程 先手，在子过程里面是 后手
            if (winner(n - base).equals("后手")) {
                return "先手";
            }
            if (base > n / 4) {
                break;
            }
            base *= 4;
        }
        return "后手";
    }

    public static String winner2(int n) {
        if (n % 5 == 0 | n % 5 == 2 ) {
            return "后手";
        }
        return "先手";
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 50; i++) {
            System.out.println(i + ":" + winner(i));
            System.out.println(i + ":" + winner2(i));
            System.out.println();
        }
    }
}
