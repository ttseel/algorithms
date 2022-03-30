package leetcode.array.concatenationofarray;

import java.util.Arrays;

public class ConcatenatioOfArray {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums = {1, 2, 1};

        System.out.println(Arrays.toString(s.getConcatenation(nums)));
    }
}

class Solution {

    public int[] getConcatenation(int[] nums) {
        int[] res = new int[nums.length * 2];

        for (int i = 0; i < nums.length; i++) {
            res[i] = nums[i];
            res[i + nums.length] = nums[i];
        }

        return res;
    }
}