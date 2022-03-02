package codility.Lesson.Lesson3.FromJmp;

public class FromJmp {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.solution(10, 85, 30);
//        solution.solution(10, 1000000000, 1000000000);
//        solution.solution(5, 105, 3);
    }
}


class Solution {
    public int solution(int X, int Y, int D) {
        int count = 0;
        int mock = (Y-X) / D;
        int nameogi = (Y-X) % D;

        if (X == Y) {
            return 0;
        }

        if (nameogi > 0) {
            count = mock + 1;
        } else {
            count = mock;
        }

        System.out.println(count);
        return count;
    }
}