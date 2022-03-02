package programmers.skillcheck.level1.problem1;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        int[] test1 = {1, 3, 2, 5, 4};
        int[] test2 = {2,2,3,3};
        solution(test2, 10);
    }

    public static int solution(int[] d, int budget) {
        int answer = 0;

        int[] sorted = Arrays.stream(d).sorted().toArray();
        int cnt = 0;


        for(int t : sorted){
            if(budget-t < 0)
                break;

            budget -= t;
            cnt++;
        }

        System.out.println(cnt);

        return answer;
    }
}