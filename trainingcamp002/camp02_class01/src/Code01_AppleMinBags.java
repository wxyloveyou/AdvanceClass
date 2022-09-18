/**
 * @author : WXY
 * @create : 2022-09-18 13:56
 * @Info : 使用最少袋子问题
 * 有A 、 B 两种袋子无穷多个， 其中A袋子可以装6个苹果，B袋子可以装8个苹果，
 * 现有N个苹果，问，可以最少用多少个袋子装
 */
public class Code01_AppleMinBags {
    public static int minBags(int apple) {
        if (apple < 0) {
            return -1;
        }
        int bag6 = -1;
        int bag8 = apple / 8;
        int rest = apple - 8 * bag8;
        while (bag8 >= 0 && rest < 24) {
            int restUse6 = minBag6(rest);
            if (restUse6 != -1) {
                bag6 = restUse6;
                break;
            }
            rest = apple - 8 * (--bag8);
        }
        return bag6 == -1 ? -1 : bag6 + bag8;
    }
    // 如果剩余苹果rest可以被装6个苹果的袋子搞定，返回袋子数量
    // 不能搞定返回-1
    private static int minBag6(int rest) {
        return rest % 6 == 0?(rest / 6) : -1;
    }

    public static int minBagAwesome(int apple) {
        if ((apple & 1) != 0) { // 如果是奇数，返回-1
            return -1;
        }
        if (apple < 18) {
            return apple == 0 ? 0 : (apple == 6 || apple == 8) ? 1 : (apple == 12 || apple == 14 || apple == 16) ? 2 : -1;
        }
        return (apple - 18) / 8 + 3;
    }


    public static void main(String[] args) {
        for (int apple = 0; apple < 200; apple++) {
            System.out.println(apple + ":" + minBags(apple));
        }
    }
}
