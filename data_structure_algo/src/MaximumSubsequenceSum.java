/**
 * Question Link: MaximumSubsequenceSum
 * https://pintia.cn/problem-sets/16/exam/problems/663?type=7&page=0
 * 最大子序列和问题
 * 输入样例:
 * 6
 * -2 11 -4 13 -5 -2
 * 输出样例:
 * 20
 */
class MaximumSubsequenceSum {
    public static void main(String[] args) {
        int[] A = new int[]{-2, 11, -4, 13, -5, -2};
        int K = 6;
        int sum = maximumSubsequenceSum(A, K);
        int sum2 = maximumSubsequenceSum2(A, K);
        System.out.println(sum);
        System.out.println(sum2);
    }

    /**
     * Online Algorithms Method 在线处理算法解决
     * @param sequence
     * @param size
     * @return
     */
    static int maximumSubsequenceSum2(int[] sequence, int size){
        boolean isPositive = true;
        int sum = 0, maxSum = 0;
        for (int i = 0; i < sequence.length; i++) {
            // constraint: 在一行中输出最大子列和。如果序列中所有整数皆为负数，则输出0。
            isPositive = sequence[i] < 0 ? false : true;
            sum += sequence[i];
            if(sum > maxSum) maxSum = sum; // if the sum > previous maxSum then update
            else if (sum < 0) sum = 0; // it'll not be beneficial for the maxSum in which E num should > 0
        }
        // If all integers in the sequence are negative, output 0.
        return isPositive ? 0 : maxSum;
    }



    /**
     * Divide & Conquer Version
     * Recurrence:
     * T(N) = 2 * T(N/2) + O(N) =  2 * T(N/2) + c*N, T(1) = O(1)
     * N/2 plug into formula we got:
     * 2 * [2*T(N/2^2) + c(N/2)] + cN ...
     * 2^k O(1) + ckN, and N / 2^k = 1 so k = logN
     * 2logN + c logN * N
     * O(NlogN)
     * recognize the pattern:
     * 2^k T(N/2^k) + kcN
     * @param sequence
     * @param size
     */
    static int maximumSubsequenceSum(int[] sequence, int size) {
        int res = smallMaxSubSeq(sequence, 0, size - 1, size / 2);
        return res;
    }

    static int smallMaxSubSeq(int[] sequence, int left, int right, int middle) {
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
    static int mergeMaxSubSeq(int leftMax, int rightMax, int[] sequence, int left, int right, int mid) {
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
