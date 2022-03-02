package codility.Lesson.Lesson4.MaxCounters;

import java.util.Arrays;

public class MaxCounters {

    public static void main(String[] args) {
        Solution solution = new Solution();

        int N = 5;
        int[] input = {3, 4, 4, 6, 1, 4, 4};
//        int N = 100000;
//        int[] input = {100001, 100001, 100001, 100001, 100001};

        System.out.println(Arrays.toString(solution.solution(N, input)));
    }
}

class Solution {

    public int[] solution(int N, int[] A) {

        int[] res = new int[N];
        int maxSum = 0;
        int maxValue = 0;
        int updateMaxCnt = 0;
        boolean isResElementChanged = false;

        for (int i = 0; i < A.length; i++) {
            if (isMaxCount(N, A[i])) {
                if (isResElementChanged) {
                    res = new int[N];
                }
                maxSum += maxValue;
                maxValue = 0;
                updateMaxCnt += 1;
            } else {
                isResElementChanged = true;
                res[A[i] - 1] += 1;
                if (res[A[i] - 1] > maxValue) {
                    maxValue = res[A[i] - 1];
                }
            }
        }

        if (updateMaxCnt == A.length) {
            return res;
        }

        for (int i = 0; i < N; i++) {
            res[i] += maxSum;
        }

        return res;
    }

    private Boolean isMaxCount(int N, int X) {
        return X == N + 1;
    }
}