package leetcode.array.containsduplicate;

import java.util.Arrays;

public class ContainsDuplicate {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] nums = {1, 2, 3, 1};
//        int[] nums = {1,2,3,4};
//        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2,};

        System.out.println(s.containsDuplicate(nums));

    }
}

class Solution {

    public boolean containsDuplicate(int[] nums) {
        boolean res = false;

        if (nums.length == 1) {
            return res;
        }

        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                res = true;
                break;
            }
        }

        return res;
    }
}