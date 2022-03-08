package codility.Lesson.Lesson4.MissingInteger;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ConcurrentHashMap;

public class MissingInteger {

    public static void main(String[] args) {
        Solution s = new Solution();

//        int[] input = {1, 3, 6, 4, 1, 2};
//        int[] input = {1, 2, 3};
        int[] input = {-1, -3};
//        int[] input = {4, 5, 6, 2};

        System.out.println(s.solution(input));

    }
}


class Solution {

    public int solutionUsingArr(int[] A) {
        int ans = 1;
        int[] occured = new int[1000001];

        int maxValue = 0;
        for (int element : A) {
            if (element > 0) {
                if (element > maxValue) {
                    maxValue = element;
                }
                occured[element] = 1;
            }
        }

        for (int i = 1; i <= maxValue; i++) {
            if (occured[i] == 0) {
                ans = i;
                break;
            } else if (i == maxValue) {
                ans = maxValue + 1;
            }
        }

        return ans;
    }

    public int solution(int[] A) {
        int ans = 1;

        Map<Integer, Integer> occuredFalse = new HashMap<>();

        int maxValue = 0;
        for (int element : A) {
            if (element > 0) {
                if (element > maxValue) {
                    for (int i = maxValue + 1; i < element; i++) {
                        occuredFalse.put(i, i);
                    }
                    maxValue = element;
                } else if (occuredFalse.containsKey(element)) {
                    occuredFalse.remove(element);
                }
            }
        }

        if (occuredFalse.keySet().size() == 0 && maxValue != 0) {
            ans = maxValue + 1;
        } else {
            for (int falseNo : occuredFalse.keySet()) {
                ans = falseNo;
                break;
            }
        }

        return ans;
    }

    public int solutionUsingPq(int[] A) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Map<Integer, Integer> occuredNumber = new ConcurrentHashMap<>();

        for (int element : A) {
            if (element > 0 && !occuredNumber.containsKey(element)) {
                occuredNumber.put(element, element);
                pq.offer(element);
            }
        }

        int ans = 1;
        int prevNo = 0;
        int totalNumberSize = pq.size();
        if (pq.size() > 0 && pq.peek() == 1) {
            for (int i = 0; i < totalNumberSize; i++) {
                if (pq.peek() - prevNo > 1) {
                    ans = prevNo + 1;
                    break;
                } else if (i == totalNumberSize - 1) {
                    ans = pq.peek() + 1;
                }
                prevNo = pq.poll();
            }
        }
        return ans;
    }

}
