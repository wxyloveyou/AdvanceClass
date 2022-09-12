/**
 * @author : WXY
 * @create : 2022-09-12 13:23
 * @Info : Morris算法
 * 不使用递归方式或者手动实现递归的方式遍历二叉树，时间O(N) 空间O(h)
 *
 * Morris算法遍历二叉树，时间O(N) 空间O(1)
 *
 */
public class Code01_MorrisTraversal {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public static void morris(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            //cur 有没有左子树
            mostRight = cur.left;
            if (mostRight != null) { // 有左子树的情况
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                //从while中出来，mostRight一定是cur左树上的最右节点
                //mostRight
                if (mostRight.right == null) { //该节点第一次到达
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // 该节点第二次到达，mostRight.right != null ->mostRight.right ==  cur
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }


    //morris序列中，若一个节点有左孩子，则该节点到达两次，若没有左孩子，则到达一次
    //中序遍历：morris序列中，没有左孩子的节点，到达一次的节点直接打印，到达两次的节点，第二次到达的时候打印
    public static void morrisIn(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { //第一次到达
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { //第二次到达
                    mostRight.right = null;
                }
            }
            System.out.print(cur.value + " ");
            cur = cur.right;
        }
        System.out.println();
    }

    //morris序列中，若一个节点有左孩子，则该节点到达两次，若没有左孩子，则到达一次
    //先序遍历：morris序列中，没有左孩子的节点，到达一次的节点直接打印，到达两次的节点，第一次到达的时候打印
    public static void morrisPre(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    System.out.print(cur.value + " ");
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            } else {
                System.out.print(cur.value + " ");
            }
            cur = cur.right;
        }
        System.out.println();
    }

    //morris序列中，若一个节点有左孩子，则该节点到达两次，若没有左孩子，则到达一次
    //后序遍历：morris序列中，到达两次的节点，逆序打印该节点左子树的右边界
    public static void morrisPos(Node head) {
        if (head == null) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                    printEdge(cur.left);
                }
            }
            cur = cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    private static void printEdge(Node head) {
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur != null) {
            System.out.print(cur.value + " ");
            cur  = cur.right;
        }
        reverseEdge(tail);
    }

    private static Node reverseEdge(Node from) {
        Node pre = null;
        Node next = null;
        while (from != null) {
            next = from.right;
            from.right = pre;
            pre = from;
            from = next;
        }
        return pre;
    }


    //for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    private static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "V", len);
        String val = to + head.value + to;
        int lenM  = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = (len - lenL - lenM);
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    private static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    //morris算法 判断一个树是不是二叉搜索树（左子树的最大值比右子树的最大值小）
    // 二叉搜索树  中序遍历呈现一种递增关系
    public static boolean isBST(Node head) {
        if (head == null) {
            return true;
        }
        Node cur = head;
        Node mostRight = null;
        Integer pre = null;
        boolean ans = true;
        while (cur != null) {
            mostRight = cur.left;
            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else {
                    mostRight.right = null;
                }
            }
            if (pre != null && pre >= cur.value) {
                ans = false;
            }
            pre = cur.value;
            cur = cur.right;
        }
        return ans;
    }





    public static void main(String[] args) {
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(6);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
        head.right.right = new Node(7);
        printTree(head);
        System.out.println("morris序 ： ");
        morris(head);
        System.out.println("morris 写中序遍历 ： ");
        morrisIn(head);
        System.out.println("morris 写先序遍历 ： ");
        morrisPre(head);
        System.out.println("morris 写后序遍历 ： ");
        morrisPos(head);
        printTree(head);

        System.out.println(isBST(head));
    }

}
