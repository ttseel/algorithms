package codility.Lesson.Lesson4.FrogRiverOne;

public class FrogRiverOne {


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] input = {1, 3, 1, 4, 2, 3, 5, 4};
        int X = 5;

        System.out.println(solution.solution(X, input));
    }
}

class Solution {

    public int solution(int X, int[] A) {

        int[] path = new int[X + 1];
        int findCnt = X;

        for (int i = 0; i < A.length; i++) {
            if (path[A[i]] == 0) {
                path[A[i]] = -1;
                findCnt--;
            }
            if (findCnt == 0) {
                return i;
            }
        }

        return -1;
    }
}
