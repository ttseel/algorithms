package codility.Exercises.Exercise4.FirstUnique;

import java.util.HashMap;
import java.util.Map;

public class FirstUnique {

    public int solution(int[] A) {

        Map<Integer, UniqueCandidate> numberAndCountMap = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            if (numberAndCountMap.containsKey(A[i])) {
                numberAndCountMap.get(A[i]).count += 1;
            } else {
                numberAndCountMap.put(A[i], new UniqueCandidate(i, 1));
            }
        }

        int minIdx = Integer.MAX_VALUE;
        for (UniqueCandidate value : numberAndCountMap.values()) {
            if (value.count != 1) {
                continue;
            }
            minIdx = Math.min(minIdx, value.firstI);
        }

        return minIdx == Integer.MAX_VALUE ? -1 : A[minIdx];
    }

    static class UniqueCandidate {

        int firstI;
        int count;

        public UniqueCandidate(int firstI, int count) {
            this.firstI = firstI;
            this.count = count;
        }
    }
}
