import java.util.zip.Checksum;

/**
 * @author : WXY
 * @program : AdvanceClass
 * @className &#xFF1A;Code07_MaxSumInTree
 * @create : 2022/11/6:14:58
 * @info : 树上的最大和
 * 题目：
 * 给定一个二叉树的头节点head，路径的规定有以下三种不同的规定︰
 * 1）路径必须是头节点出发，到叶节点为止，返回最大路径和
 * 2）路径可以从任何节点出发，但必须往下走到达任何节点，返回最大路径和
 * 3）路径可以从任何节点出发，到任何节点，返回最大路径和
 **/
public class Code07_MaxSumInTree {
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    public static int maxSum = Integer.MIN_VALUE;

    public static int maxPath(Node head) {
        maxSum = Integer.MIN_VALUE;
        p(head, 0);
        return maxSum;
    }

    //之前的路径和，为pre
    private static void p(Node x, int pre) {
        if (x.left == null && x.right == null) {
            maxSum = Math.max(maxSum, pre + x.value);
        }
        if (x.left != null) {
            p(x.left, pre + x.value);
        }
        if (x.right != null) {
            p(x.right, pre + x.value);
        }
    }

    public static int maxDis(Node head) {
        if (head == null) {
            return 0;
        }
        return process2(head);
    }

    //x为头的整个树上，最大路径和是多少，返回
    //路经要求，一定从x出发，到叶节点为止，算作一个路径
    private static int process2(Node x) {
        if (x.right == null && x.left == null) {
            return x.value;
        }
        int next = Integer.MIN_VALUE;
        if (x.left != null) {
            next = process2(x.left);
        }
        if (x.right != null) {
            next = Math.max(next, process2(x.right));
        }
        return next + x.value;
    }

    public static int maxSum2(Node head) {
        if (head == null) {
            return 0;
        }
        return f2(head).allTreeMaxSum;
    }

    public static class Info{
        public int allTreeMaxSum; //整数最和
        public int fromHeadMaxSum; // 从头出发最和

        public Info(int allTreeMaxSum, int fromHeadMaxSum) {
            this.allTreeMaxSum = allTreeMaxSum;
            this.fromHeadMaxSum = fromHeadMaxSum;
        }
    }

    //1）x无关的时候，1，左树上整体最大路径和，2.右树上整体最大路径和
    //2）x有关的时候，3，x 自己，4.x往左走， 5.x往右走
    public static Info f2(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = f2(x.left);
        Info rightInfo = f2(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }
        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(p4, p5));
        int fromTreeMaxSum = Math.max(Math.max(p3, p3), p5);
        return new Info(allTreeMaxSum, fromTreeMaxSum);
    }


    //1)x无关的时候，1.左树上的整体最大路径和 2.右树上的整体最大路径和、
    //2）x有关的时候，3，x 自己，4，x往左走，5,x往右走，6，既往左走，也往右走
    public static Info f3(Node x) {
        if (x == null) {
            return null;
        }
        Info leftInfo = f3(x.left);
        Info rightInfo = f3(x.right);
        int p1 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p1 = leftInfo.allTreeMaxSum;
        }
        int p2 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p2 = rightInfo.allTreeMaxSum;
        }
        int p3 = x.value;
        int p4 = Integer.MIN_VALUE;
        if (leftInfo != null) {
            p4 = x.value + leftInfo.fromHeadMaxSum;
        }
        int p5 = Integer.MIN_VALUE;
        if (rightInfo != null) {
            p5 = x.value + rightInfo.fromHeadMaxSum;
        }
        int p6 = Integer.MIN_VALUE;
        if (leftInfo != null && rightInfo != null) {
            p6 = x.value + leftInfo.fromHeadMaxSum + rightInfo.fromHeadMaxSum;
        }
        int allTreeMaxSum = Math.max(Math.max(Math.max(p1, p2), p3), Math.max(Math.max(p4, p5), p6));
        int fromTreeMaxSum = Math.max(Math.max(Math.max(p3, p4), p5), p6);
        return new Info(allTreeMaxSum, fromTreeMaxSum);
    }

    //从头节点出发，到叶节点为止为一个路径，求最大路径和
    //大帅提出，
    public static int max = Integer.MIN_VALUE;

    public static int bigShuai(Node head) {
        if (head.left == null && head.right == null) {
            max = Math.max(max, head.value);
            return head.value;
        }
        int nextMax = 0;
        if (head.left != null) {
            nextMax = bigShuai(head.left);
        }
        if (head.right != null) {
            nextMax = Math.max(nextMax, bigShuai(head.right));
        }
        int ans = head.value + nextMax;
        max = Math.max(ans, max);
        return ans;
    }

}

