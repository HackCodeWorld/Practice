import java.util.*;

/**
 * inorderTraversal using stack to simulate recursion
 * and then coming up with preorder and postorder by changing the order of the print
 * using stack or recursion
 * 中序遍历，左根右
 * 先序遍历，根左右
 * 后序遍历，左右根
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
     * inorderTraversal using stack
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

    /**
     * inorderTraversal using recursion
     **/
    public List<Integer> inorderTraversalRecur(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    private void inorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inorder(root.left, res); // 不是null就一直往左走
        // 当前节是null的时候，才打印当前节点
        res.add(root.val);
        inorder(root.right, res); //  再访问右子树
    }
}
