package codility.Lesson.Lesson5.CountDiv;

public class CountDiv {

    public static void main(String[] args) {
        Solution s = new Solution();

//        int A = 6;
//        int B = 11;
//        int K = 2;

//        int A = 11;
//        int B = 345;
//        int K = 17;

        int A = 1;
        int B = 1;
        int K = 17;

//        int A = 0;
//        int B = 2000000000;
//        int K = 2000000000;
        System.out.println(s.solution(A, B, K));

    }
}

class Solution {

    public int solution(int A, int B, int K) {

        if (A == 0 && B == 0) {
            return 1;
        }

        int firstModZeroNumber = findFirstModZeroNumber(A, B, K);

        if (firstModZeroNumber == -1) {
            return 0;
        }

        return (B - firstModZeroNumber) / K + 1;
    }

    private int findFirstModZeroNumber(int A, int B, int K) {
        int res = -1;

        int findStart = -1;
        if (K <= A) {
            findStart = A;
        } else if (K <= B) {
            findStart = K;
        }

        if (findStart != -1) {
            for (int i = A; i <= B; i++) {
                if (i % K == 0) {
                    res = i;
                    break;
                }
            }
        }

        return res;
    }
}
