/**
 * Question Link:
 * https://pintia.cn/problem-sets/16/exam/problems/663?type=7&page=0
 */
public class MaximumSubsequenceSum {
    public static void main(String[] args) {
        int[] A = new int[]{-2, 11, -4, 13, -5, -2};
        int K = 6;
        int sum = maximumSubsequenceSum(A, K);
        System.out.println(sum);
    }

    /**
     * Divide & Conquer Version
     * @param sequence
     * @param size
     */
    public static int maximumSubsequenceSum(int[] sequence, int size) {
        int res = smallMaxSubSeq(sequence, 0, size - 1, size / 2);
        return res;
    }

    public static int smallMaxSubSeq(int[] sequence, int left, int right, int middle) {
        if (left == right) return sequence[left];
        int leftMax = smallMaxSubSeq(sequence, left, middle, (left + middle) / 2);
        // 注意index mid
        int rightMax = smallMaxSubSeq(sequence, middle + 1, right, (middle + right) / 2);
        int max = mergeMaxSubSeq(leftMax, rightMax, sequence, left, right, middle);
        return max;
    }

    /**
     * merge steps
     * 尝试添加中间的数字是否使左右两边各自的局部sum改变
     * Trying to see if adding the middle number makes the respective local sums
     * on the left and right change
     */
    public static int mergeMaxSubSeq(int leftMax, int rightMax, int[] sequence, int left, int right, int mid) {
        int tempL = 0, tempR = 0, sumL = Integer.MIN_VALUE, sumR = 0;
        for (int i = mid; i >= left; i--) {
            tempL += sequence[i];
            if (tempL > sumL) sumL = tempL;
        }

        for (int i = mid + 1; i <= right; i++) {
            tempR += sequence[i];
            if (tempR > sumR) sumR = tempR;
        }

        // 注意分析情况
        return Math.max(sumL + sumR, Math.max(leftMax, rightMax));
    }
}
