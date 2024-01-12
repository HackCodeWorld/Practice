/**
 * A robot walks from start to the destination on a line
 * given:
 * - start
 * - dest destination
 * - steps how many you want to walk frm star to dest
 * - length the length of the entire road
 * constraint of line:
 * robot on left bound ONLY moving right
 * robot on right bound ONLY moving left
 * robot on the middle cells can either move left or right
 */
public class RobotWalk {
    /**
     * the brute force recursion version
     * params:
     * - start
     * - dest destination
     * - steps how many you want to walk frm star to dest
     * - length the length of the entire road
     *
     * @return how many ways of path A robot walks from start to the destination on a line
     * according to the given number of steps
     */
    static int process(int start, int dest, int steps, int length) {
        // TODO: eliminate some corner cases

        return bruteForce(start, dest, steps, length);
    }

    static int bruteForce(int curr, int dest, int remainingSteps, int length) {
        // steps exhausted, arrived at the destination, #ways + 1;
        if (remainingSteps == 0) return curr == dest ? 1 : 0;
        // robot on left bound ONLY moving right (curr index goes right)
        if (curr == 0) return bruteForce(curr + 1, dest, remainingSteps - 1, length);
        // robot on right bound ONLY moving left
        if (curr == length - 1) return bruteForce(curr - 1, dest, remainingSteps - 1, length);
        // robot on the middle cells can either move left or right
        int numOfWays = bruteForce(curr - 1, dest, remainingSteps - 1, length) +
                bruteForce(curr + 1, dest, remainingSteps - 1, length);
        return numOfWays;
    }

    static int dp(int start, int dest, int remainingSteps, int length) {
        // to hold all possibilities of the (curr, remainingSteps) status / state
        int[][] dp = new int[length + 1][remainingSteps + 1]; // java all initialize to [i][j] = 0
        // base case: all initialize to 0 except "curr == dest ? 1 : 0"
        // remaining steps 0: [curr, remainingSteps=0] -> 1 method count added
        dp[dest][0] = 1; // cells left:  dp[0..|dest|..length][0] = 0
        for (int rest = 1; rest <= remainingSteps; rest++) {
            dp[0][rest] = dp[1][rest - 1];//if (curr == 0) return bruteForce(curr + 1, dest, remainingSteps - 1, length);
            for (int curr = 1; curr < length - 1; curr++) { // curr = 0 and curr = length - 1 chosen
                // robot on the middle cells can either move left or right
                /**int numOfWays = bruteForce(curr - 1, dest, remainingSteps - 1, length) +
                 bruteForce(curr + 1, dest, remainingSteps - 1, length);**/
                dp[curr][rest] = dp[curr - 1][rest - 1] + dp[curr + 1][rest - 1];
            }
            dp[length - 1][rest] = dp[length - 2][rest - 1];//if (curr == length - 1) return bruteForce(curr - 1, dest, remainingSteps - 1, length);
        }
        return dp[start][remainingSteps];
    }

    public static void main(String[] args) {
        int n = process(1, 3, 6, 5);
        System.out.println(n);
        int n2 = dp(1, 3, 6, 5);
        System.out.println(n2);
    }

}
