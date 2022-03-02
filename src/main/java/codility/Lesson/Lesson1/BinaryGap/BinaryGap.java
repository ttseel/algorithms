package codility.Lesson.Lesson1.BinaryGap;

public class BinaryGap {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(529));

    }
}

// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class Solution {
    public int solution(int N) {
        String binaryN = Integer.toBinaryString(N);

        int max = 0;
        int nexMax = 0;
        for (char each : binaryN.toCharArray()) {
            if (each == '1') {
                max = Math.max(nexMax, max);
                nexMax = 0;
            } else {
                nexMax++;
            }
        }

        return max;
    }
}
