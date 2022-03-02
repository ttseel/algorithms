package codility.Lesson.Lesson2.CyclicRotation;

import java.util.Arrays;

public class CyclicRotation {
    public static void main(String[] args) {
//        int[] A = {1,2,3,4,5};
//        int K = 0;

//        int[] A = {1,2,3};
//        int K = 5;

        int[] A = {};
        int K = 5;

        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(A, K)));
    }
}

class Solution {
    public int[] solution(int[] A, int K) {
        int[] res = new int[A.length];


        if (A.length != 0 && K > A.length) {
            K = K % A.length;
        }

        if (K == A.length || A.length == 0 || K==0) {
            res = A;
        } else {
            for (int i = 0; i < K; i++) {
                res[i] = A[(A.length - K + i)];
            }
            for (int i = K; i < A.length; i++) {
                res[i] = A[i - K];
            }
        }
        return res;
    }
}
