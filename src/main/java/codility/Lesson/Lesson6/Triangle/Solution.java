package codility.Lesson.Lesson6.Triangle;

import java.util.Arrays;


/**
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [0..100,000]; each element of array A is an integer within the
 * range [−2,147,483,648..2,147,483,647].
 */
public class Solution {

    public int solution(int[] A) {

        if (A.length < 3) {
            return 0;
        }

        // A를 오름 차순으로 sorting
        Arrays.sort(A);

        // 순차대로 3개씩 확인하면서 a1 + a2 > a3 인지 검증
        long a1;
        long a2;
        long a3;
        for (int i = 0; i < A.length - 2; i++) {
            a1 = A[i];
            a2 = A[i + 1];
            a3 = A[i + 2];

            if (a1 + a2 > a3) {
                return 1;
            }
        }

        return 0;
    }
}

