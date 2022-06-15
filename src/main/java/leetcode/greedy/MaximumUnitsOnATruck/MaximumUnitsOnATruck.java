package leetcode.greedy.MaximumUnitsOnATruck;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumUnitsOnATruck {

    public int maximumUnits(int[][] boxTypes, int truckSize) {

        int res = 0;

        PriorityQueue<Box> pq = new PriorityQueue<>();

        for (int i = 0; i < boxTypes.length; i++) {
            pq.offer(new Box(boxTypes[i][0], boxTypes[i][1]));
        }

        for (int i = 0; i < boxTypes.length; i++) {
            if (truckSize <= 0 || pq.size() == 0) {
                break;
            }

            if (truckSize >= pq.peek().boxCnt) {
                res += pq.peek().boxUnit * pq.peek().boxCnt;
            } else {
                res += truckSize * pq.peek().boxUnit;

            }
            truckSize -= pq.peek().boxCnt;
            pq.poll();
        }

        return res;
    }

    static class Box implements Comparable<Box> {

        int boxCnt;
        int boxUnit;

        public Box(int boxCnt, int boxUnit) {
            this.boxCnt = boxCnt;
            this.boxUnit = boxUnit;
        }

        @Override
        public int compareTo(Box target) {

            return this.boxUnit > target.boxUnit ? -1 : 1;
        }
    }
}
