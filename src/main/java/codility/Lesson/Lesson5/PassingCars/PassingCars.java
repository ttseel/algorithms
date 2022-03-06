package codility.Lesson.Lesson5.PassingCars;

import java.util.ArrayList;
import java.util.List;

public class PassingCars {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] input = {0, 1, 0, 1, 1};

        System.out.println(s.solution(input));
    }
}


class Solution {

    public int solution(int[] A) {
        int ans = -1;
        int cumulativeSum = 0;

        if (A.length == 1) {
            return 0;
        }

        List<Integer> idxOfZero = new ArrayList<>();
        int[] cumulativeSumOfOne = new int[A.length];

        for (int i = 0; i < A.length; i++) {
            if (A[i] == 0) {
                idxOfZero.add(i);
            } else {
                cumulativeSum++;
            }
            cumulativeSumOfOne[i] = cumulativeSum;
        }

        ans = 0;
        for (Integer idx : idxOfZero) {
            if (idx == 0) {
                ans += cumulativeSum;
            } else {
                ans += cumulativeSum - cumulativeSumOfOne[idx - 1];
            }
            if (ans > 1000000000) {
                ans = -1;
                break;
            }
        }

        return ans;
    }
}
