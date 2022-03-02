package programmers.skillcheck.level1.problem2;

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        solution(3);
    }

    public static String solution(int n) {
        String answer = "";
        String cur = "박";

        for (int i = 0; i < n; i++) {
            if (cur.equals("수")){
                cur = "박";
            } else {
                cur = "수";
            }

            answer += cur;
        }

        System.out.println(answer);
        return answer;
    }
}