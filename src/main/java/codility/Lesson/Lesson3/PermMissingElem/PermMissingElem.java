package codility.Lesson.Lesson3.PermMissingElem;

public class PermMissingElem {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] input = {2,3,1,5};
        int[] input = {1};

        System.out.println(solution.solution(input));
    }
}

class Solution {
    public int solution(int[] A) {

        if (A.length == 0) {
            return 1;
        }

        double n = ((double) (1 + (A.length + 1)) / 2 * (A.length+1));

        for (int a : A) {
            n -= a;
        }

        return (int) n;
    }
}
