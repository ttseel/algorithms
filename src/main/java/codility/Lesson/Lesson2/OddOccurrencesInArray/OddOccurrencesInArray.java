package codility.Lesson.Lesson2.OddOccurrencesInArray;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class OddOccurrencesInArray {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] input = {9, 3, 9, 3, 9, 7, 9};

        solution.solution(input);
    }

}

class Solution {
    public int solution(int[] A) {
        int res = -1;
        Map<Integer, Integer> count = new ConcurrentHashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (count.containsKey(A[i])) {
                count.put(A[i], count.get(A[i]) + 1);
            } else {
                count.put(A[i], 1);
            }
        }

        for (int no : count.keySet()) {
            if (count.get(no) % 2 != 0) {
                res = no;
            }
        }

        return res;
    }
}
