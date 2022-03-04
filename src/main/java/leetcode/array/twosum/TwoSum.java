package leetcode.array.twosum;

import java.util.Arrays;

public class TwoSum {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {2, 7, 11, 15};
//        int[] nums = {3, 2, 4};
//        int[] nums = {3, 3};
//        int[] nums = {0, 4, 3, 0};
        int target = 17;

        System.out.println(Arrays.toString(solution.twoSum(nums, target)));
    }

}
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];

        for (int i = 0; i < nums.length; i++) {
            res[0] = i;

            for (int j = i+1; j < nums.length; j++) {
                if (nums[res[0]] + nums[j] == target) {
                    res[1] = j;
                    return res;
                }
            }
        }

        return res;
    }
}