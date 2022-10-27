import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.LinkedList;

/**
 * @author : WXY
 * @create : 2022-10-27 22:31
 * @Info : AC自动机
 */
public class Code01_AC {
    //前缀树节点
    public static class Node{
        //如果一个node，end为空，不是结尾
        //如果end不为空，表示这个点是某个字符串的结尾，end的值就是这个字符串
        public String end;
        //只有在上面的end变量不为空的时候，endUse才有意义
        //表示，这个字符串之前没有加入过答案
        public boolean endUse;
        public Node fail;
        public Node[] nexts;
        public Node(){
            endUse = false;
            end = null;
            fail = null;
            nexts = new Node[26];
        }
    }
    public static class ACAutomation{
        private Node root;

        public ACAutomation() {
            root = new Node();
        }

        public void insert(String s) {
            char[] str = s.toCharArray();
            Node cur = root;
            int index = 0;
            for (int i = 0; i < str.length; i++) {
                index = str[i] - 'a';
                if (cur.nexts[index] == null) {
                    Node next = new Node();
                    cur.nexts[index] = next;
                }
                cur = cur.nexts[index];
            }
            cur.end = s;
        }

        public void build() {
            LinkedList<Node> queue = new LinkedList<>();
            queue.add(root);
            Node cur = null;
            Node cfail = null;
            while (!queue.isEmpty()) {
                //当前节点弹出
                //当前节点的所有后代加入到队列中去，
                //当前节点给它的子去设置fail指针
                //cur -> 父亲
                cur = queue.poll();
                for (int i = 0; i < 26; i++) {  //所有的路
                    if (cur.nexts[i] != null) { //找到有效的路
                        //cur.nexts[i] 子
                        cur.nexts[i].fail = root;
                        cfail = cur.fail;
                        while (cfail != null) {
                            if (cfail.nexts[i] != null) {  //cfail的子有这个方向上的路， 那么子的fail直接指过去
                                cur.nexts[i].fail = cfail.nexts[i];
                                break;
                            }
                            cfail = cfail.fail;  //要是没有这个方向上的路，那么cfail继续往下跳
                        }
                        queue.add(cur.nexts[i]);
                    }
                }
            }
        }





    }
}
