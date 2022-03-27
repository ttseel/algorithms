package codility.Lesson.Lesson9;

public class MaximumSliceProblem {

    public int solution(int[] A) {
        if (A.length == 0 || A.length == 1) {
            return 0;
        }

        int min = A[0];
        int maxProfit = Integer.MIN_VALUE;
        for (int i = 1; i < A.length; i++) {
            min = Math.min(min, A[i - 1]);
            maxProfit = Math.max(maxProfit, A[i] - min);
        }

        return Math.max(maxProfit, 0);
    }
}
