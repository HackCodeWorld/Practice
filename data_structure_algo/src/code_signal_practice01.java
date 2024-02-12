public class code_signal_practice01 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 2, 1, 2, 1}));
    }

    /**
     * 这段代码的主要作用是遍历一个整数数组，计算数组中相邻且不相同元素对的总数。例如，
     * 对于数组[1, 2, 1, 2, 1]，它会计算出有4对相邻且不相同的元素（1-2, 2-1, 1-2, 2-1），
     * 因此最终输出结果为4。注意，这个解决方案中有一个细节处理，即通过prevCounted变量
     * 确保在连续的不相同元素对中，每对只被额外计数一次。
     * @param arr
     * @return
     */
    static long solution(int[] arr) {
        boolean prevCounted = false;
        int count = 0;
        for(int i = 0, j = i + 1; i < arr.length && j < arr.length; i++, j++){
            if(arr[j] != arr[i]){
                if(prevCounted) count++;
                prevCounted = true;
                count++;
            }else{ // the same
                prevCounted = false;
            }
        }
        return count;
    }

}
