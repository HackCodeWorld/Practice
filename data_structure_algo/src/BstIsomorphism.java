/**
 * BST Isomorphism Problem
 * 二叉树同构问题
 * a binary tree A can convert to BST B
 * through x times of switches btw L & R child
 * <p>
 * 0,1,2,3,4 : A,B,C,D,E
 * 5:G, 6:F, 7:H
 * input:
 * 8 - eight nodes
 * A 1 2 (A node's left child is B & right child is C)
 * B 3 4
 * C 5
 * D
 * E 6
 * G 7
 * F
 * H
 * 递归的本质
 * 递归函数通过调用自身来解决问题的子问题。在二叉树的同构检查中，我们尝试通过比较树的子结构来判断整体结构是否相同。递归的每一层都尝试解决“这两个子树是否同构”的问题。
 *
 * 基本情况（Base Cases）
 * 两树均为空：如果两个树都为空，它们自然是同构的，因为没有结构可以比较。
 * 一树为空，另一树非空：如果其中一个树为空而另一个不为空，它们不可能是同构的，因为结构不同。
 * 根节点值不同：如果两个树的根节点值不同，无需进一步比较，它们不是同构的。
 * 这些基本情况确保了递归有明确的终止条件，并处理了最简单的情况。
 *
 * 递归调用的逻辑
 * 对于非基本情况，我们需要检查两种可能的同构情况：
 *
 * 直接同构：即一个树的左子树与另一个树的左子树同构，同时，一个树的右子树与另一个树的右子树同构。这表示两个树在结构和节点值上都是一致的。
 * 镜像同构：即一个树的左子树与另一个树的右子树同构，同时，一个树的右子树与另一个树的左子树同构。这考虑了树可能在结构上是镜像对称的情况。
 * 通过这两个递归调用，我们能够检查所有的结构匹配情况。这种方法的正确性基于递归能够穷尽所有可能的结构配置。对于任意两个节点，我们都检查了它们作为树根时，树的所有子结构是否能够对应上。
 *
 * 确保正确性的关键点
 * 递归的穷尽性：通过直接同构和镜像同构的检查，我们没有遗漏任何可能的结构匹配情况。
 * 明确的终止条件：基本情况确保了递归总会在某一点停止，防止了无限递归。
 * 这种实现之所以正确，是因为它结合了递归的穷尽性和明确的终止条件，通过系统地检查所有可能的结构配置来确定两个树是否同构。
 */
public class BstIsomorphism {

    // Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) {
        // 1. build the tree 1
        // 2. build the tree 2
        // 3. check is it isomorphic
    }

    boolean isIsomorphic(TreeNode t1, TreeNode t2) {
        // some base cases：
        // 1.if both empty, return true
        // 如果两个节点都为空，认为它们是同构的
        if (t1 == null && t2 == null) return true;
        // 2.one of them is empty, return false
        // 如果其中一个节点为空，另一个不为空，它们不是同构的
        if (t1 == null || t2 == null) return false;
        // 3.roots are diff return false
        // 如果两个节点的值不相同，它们不是同构的
        if (t1.val != t2.val) return false;

//        // both have no left subtree 左边同时空
//        // explore right subtree to see whether is 同构
//        if (t1.left == null && t2.left == null) return isIsomorphic(t1.right, t2.right);
//        // 左边同时不空
//        /*no need to swap the left & right*/
//        if (t1.left != null && t2.left != null
//                && t1.left.val == t2.left.val) // check both trees left same elements?
//            // check left subtree the same and right subtree the same
//            return isIsomorphic(t1.left, t2.left) && isIsomorphic(t1.right, t2.right);
//
//        /*need to swap the left and right*/
//        return isIsomorphic(t1.left, t2.right) && isIsomorphic(t1.right, t2.left);

        // 检查：1.左子树是否与左子树同构且右子树是否与右子树同构
        //      2.或者左子树是否与右子树同构且右子树是否与左子树同构
        // 这两种情况满足任意一种即可认为是同构的
        return (isIsomorphic(t1.left, t2.left) && isIsomorphic(t1.right, t2.right)) || // 一样结构检查
                (isIsomorphic(t1.left, t2.right) && isIsomorphic(t1.right, t2.left)); // mirror check
    }
}
