import java.util.*;

/**
 * inorderTraversal using stack to simulate recursion
 * and then coming up with preorder and postorder by changing the order of the print
 * using stack or recursion
 * DFS:
 * 中序遍历，左根右
 * 先序遍历，根左右
 * 后序遍历，左右根
 * BFS: level order
 * 1. when it met a node, it will push it into the stack and move to its left child
 * 2. when left subtree is done, it will pop the node from the stack and print it and access it
 * 3. then it will move to the right subtree and repeat the inorder process
 */
public class inorderTraversal {
    public static void main(String[] args) {
//        System.out.println("inorderTraversal");
//        System.out.println("Expected: [1, 3, 2, 5 ,4 ,6]");
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);
        root.right.right.left = new TreeNode(5);
        root.right.right.right = new TreeNode(6);
//        System.out.println(new inorderTraversal().inorderTraversal(root));

        System.out.println("inorderTraversalRecur");
        System.out.println("Expected: [1, 3, 2, 5 ,4 ,6]");
        System.out.println(new inorderTraversal().inorderTraversalRecur(root));

        System.out.println("preorderTraversal");
        System.out.println("Expected: [1, 2, 3, 4, 5, 6]");
        System.out.println(new inorderTraversal().preorderTraversal(root));

        System.out.println("BFS");
        System.out.println("Expected: [1, 2, 3, 4, 5, 6]");
        System.out.println(new inorderTraversal().BFS(root));

        /**
         * APPLICATION OF TREE TRAVERSAL
         * 二叉树遍历的应用题目
         * 一般都是微调
         **/
        // 1. 输出叶子节点 output all leaf nodes
        System.out.println("Leaf nodes are:");
        // 用preorder 根左右去得到所有 leaf nodes 更 make sense
        System.out.println(new inorderTraversal().printLeafNodes(root));

        // 2. get tree height
        System.out.println("binary tree height is: ");
        System.out.println(new inorderTraversal().getTreeHeight(root));

        // 3.告诉你中序 和 前/后才能 唯一确定一个二叉树
        // TODO: 构造出二叉树的结构

        // 4. TODO：判断二叉树同构的问题通常被称为 "Determining Isomorphism of Binary Trees"
        //  或者 "Binary Tree Isomorphism Check"。
        



    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * used preorderTraversal using recursion
     * 因为逻辑是得到左右subtree的max height，再加上root的height
     * 所以要左右根这种后续
     * USED FOR COMPARISON
     **/
    public int getTreeHeight(TreeNode root) {
        int res = postorder(root);
        return res;
    }

    /**
     * mild change to get tree height
     * postorderTraversal using recursion
     * USED FOR COMPARISON
     **/
    private int postorder(TreeNode root) {
        if (root == null) return 0;
        int l_height = postorder(root.left);
        int r_height = postorder(root.right);
        int res = Math.max(l_height, r_height) + 1;
        return res;
    }


    /**
     * preorderTraversal using recursion
     * USED FOR COMPARISON
     * 1. 输出叶子节点 output all leaf nodes
     **/
    public List<Integer> printLeafNodes(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorder(root, res);
        return res;
    }

    /**
     * preorderTraversal using recursion
     * USED FOR COMPARISON
     * // 1. 输出叶子节点 output all leaf nodes
     **/
    private void preorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        // 左右为空，就是叶子节点
        // 1. 输出叶子节点 output all leaf nodes
        if(root.left == null && root.right == null) res.add(root.val);
        preorder(root.left, res); // 不是null就一直往左走 compared with stack
        preorder(root.right, res); //  再访问右子树 compared with stack
    }

    /**
     * inorderTraversal using recursion
     * USED FOR COMPARISON
     **/
    public List<Integer> inorderTraversalRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    /**
     * inorderTraversal using recursion
     * USED FOR COMPARISON
     **/
    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res); // 不是null就一直往左走
        // 当前节是null的时候，才打印当前节点
        res.add(root.val);
        inorder(root.right, res); //  再访问右子树
    }

    /**
     * left root right
     * inorderTraversal using stack 中序遍历
     * 1. when it met a node, it will push it into the stack and move to its left child
     * 2. when left subtree is done, it will pop the node from the stack and print it and access it
     * 3. then it will move to the right subtree and repeat the inorder process
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // 一直往左延伸，直到没有左子树
            // keep going left until no left subtree
            while (curr != null) {
                stack.push(curr); // 模拟递归调用 stimulation of recursive call
                curr = curr.left;
            }
            // 当左子树为空时，打印当前节点，然后访问右子树
            // when left subtree is done, print the node and access the right subtree
            if (curr == null) { // 当当前节是null的时候，打印当前节点 == recursion
                TreeNode printNode = stack.pop(); // 模拟递归返回 stimulation of recursive return
                res.add(printNode.val); // 就打印
                curr = printNode.right; // 访问右子树
            }
        }
        return res;
    }

    /**
     * root left right
     * 先序遍历
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // 一直往左延伸，直到没有左子树
            // keep going left until no left subtree
            while (curr != null) {
                stack.push(curr); // 模拟递归调用 stimulation of recursive call
                res.add(curr.val); //第一次遇到root就打印 先序遍历 （主要就是改变print时候 相比于inorder）
                curr = curr.left;
            }
            // 当左子树为空时，打印当前节点，然后访问右子树
            // when left subtree is done, print the node and access the right subtree
            if (curr == null) { // 当当前节是null的时候，打印当前节点 == recursion
                TreeNode printNode = stack.pop(); // 模拟递归返回 stimulation of recursive return
//                res.add(printNode.val); // 就打印 中序遍历
                curr = printNode.right; // 访问右子树
            }
        }
        return res;
    }

    /**
     * Bread First Tree
     * Level Order Traversal
     * A
     *
     * @param root
     * @return
     */
    public List<Integer> BFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove();
            res.add(curr.val);
            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
        }
        return res;
    }
}
