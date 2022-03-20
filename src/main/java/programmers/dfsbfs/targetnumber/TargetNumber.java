package programmers.dfsbfs.targetnumber;


import java.util.Stack;

public class TargetNumber {

    public static void main(String[] args) {
        Solution s = new Solution();

        int[] numbers = {4, 1, 2, 1};
        int target = 4;

        System.out.println(s.solution(numbers, target));
    }
}


class Solution {

    Stack<Point> stack = new Stack<Point>();
    int arrLength;
    int answer = 0;

    public int solution(int[] numbers, int target) {
        arrLength = numbers.length;

        int numberSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            numberSum += numbers[i];
        }
        for (int i = 0; i < numbers.length; i++) {
            stack.add(new Point(i, numberSum));
        }
        while (stack.size() > 0) {
            dfs(stack.pop(), numbers, target);
        }

        return answer;
    }

    public void dfs(Point point, int[] numbers, int target) {
        int curSum = point.curSum - numbers[point.startIdx] * 2;
        int nextStartIdx = point.startIdx + 1;

        if (curSum == target) {
            answer++;
            return;
        } else if (nextStartIdx >= numbers.length) {
            return;
        }

        while (nextStartIdx < arrLength) {
            stack.add(new Point(nextStartIdx++, curSum));
        }

        dfs(stack.pop(), numbers, target);
    }
}

class Point {

    int startIdx;
    int curSum;

    public Point(int startIdx, int curSum) {
        this.startIdx = startIdx;
        this.curSum = curSum;
    }
}