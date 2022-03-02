package codility.Lesson.Lesson3.TapeEquilibrium;

public class TapeEquilibrium {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] input = {3, 1, 2, 3, 4};
        int[] input = {-203, -22};

        System.out.println(solution.solution(input));
    }
}

class Solution {
    public int solution(int[] A) {

        int front = 0;
        int back = 0;
        int diff = -1;
        for (int i = 1; i < A.length; i++) {
            if (i == 1) {
                front = A[0];
                for (int j = 1; j < A.length; j++) {
                    back += A[j];
                }
                diff = Math.abs(front - back);
                continue;
            }

            front += A[i - 1]; // front = front + A[i - 1]
            back -= A[i - 1]; // back = back - A[i - 1]

            diff = Math.min(Math.abs(front - back), diff);
        }

        return diff;
    }
}