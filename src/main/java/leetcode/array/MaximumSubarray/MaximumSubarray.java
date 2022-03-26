package leetcode.array.MaximumSubarray;

public class MaximumSubarray {

    int res;

    public int maxSubArray(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        res = Integer.MIN_VALUE;
        int[] maxSubSumArr = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                maxSubSumArr[i] = nums[i];
            } else {
                maxSubSumArr[i] = Math.max(maxSubSumArr[i - 1] + nums[i], nums[i]);
            }
            res = Math.max(res, maxSubSumArr[i]);
        }

        return res;
    }
}
