package leetcode.dp.climbingstairs;

public class ClimbingStairs {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.climbStairs(5));
    }
}

class Solution {

    public int climbStairs(int n) {
        int[] noOfStairs = new int[n + 1];

        int curN = 3;
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }

        noOfStairs[1] = 1;
        noOfStairs[2] = 2;
        for (int i = curN; i <= n; i++) {
            noOfStairs[i] = noOfStairs[i - 1] + noOfStairs[i - 2];
        }

        return noOfStairs[n];
    }
}