package codility.Lesson.Lesson4.PermCheck;

public class PermCheck {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] input = {2};

        System.out.println(solution.solution(input));
    }
}

class Solution {

    public int solution(int[] A) {
        int[] permutation = new int[A.length + 1];
        int findCnt = A.length;

        for (int i = 0; i < A.length; i++) {
            if (A[i] > A.length) {
                return 0;
            }

            if (permutation[A[i]] == 0) {
                permutation[A[i]] = -1;
                findCnt--;
                if (findCnt == 0) {
                    return 1;
                }
            }
        }

        return 0;
    }
}