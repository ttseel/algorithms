package programmers.level1.모의고사;


import java.util.*;

public class Answer {
    public static void main(String[] args) {
        Solution solution = new Solution();

//        int[] input = {1, 3, 2, 4, 2};
//        int[] input = {5,5,4,2,3};
        int[] input = {3,3,2,1,5};

//        int[] input = new int[10000];
//
//        int no = 1;
//        for (int i = 0; i < 10000; i++) {
//            input[i] = no;
//            no++;
//            if (no == 6)
//                no = 1;
//        }

        System.out.println(Arrays.toString(solution.solution(input)));
    }

    /**
     * 문제 설명
     * 수포자는 수학을 포기한 사람의 준말입니다. 수포자 삼인방은 모의고사에 수학 문제를 전부 찍으려 합니다.
     * 수포자는 1번 문제부터 마지막 문제까지 다음과 같이 찍습니다.
     *
     * 1번 수포자가 찍는 방식: 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, ...
     * 2번 수포자가 찍는 방식: 2, 1, 2, 3, 2, 4, 2, 5, 2, 1, 2, 3, 2, 4, 2, 5, ...
     * 3번 수포자가 찍는 방식: 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, 3, 3, 1, 1, 2, 2, 4, 4, 5, 5, ...
     *
     * 1번 문제부터 마지막 문제까지의 정답이 순서대로 들은 배열 answers가 주어졌을 때,
     * 가장 많은 문제를 맞힌 사람이 누구인지 배열에 담아 return 하도록 solution 함수를 작성해주세요.
     *
     * 제한 조건
     * 시험은 최대 10,000 문제로 구성되어있습니다.
     * 문제의 정답은 1, 2, 3, 4, 5중 하나입니다.
     * 가장 높은 점수를 받은 사람이 여럿일 경우, return하는 값을 오름차순 정렬해주세요.
     */
}

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        Solver sovler = new Solver();

        int[] res = new int[3];
        for (int i = 0; i < answers.length; i++) {
            int[] solve = sovler.solve(answers[i]);

            res[0] += solve[0];
            res[1] += solve[1];
            res[2] += solve[2];
        }

        Score score1 = new Score(1, res[0]);
        Score score2 = new Score(2, res[1]);
        Score score3 = new Score(3, res[2]);

        // 우선순위 큐를 이용해 값이 큰 순서대로 sort
        PriorityQueue<Score> pq = new PriorityQueue<>();
        pq.add(score1);
        pq.add(score2);
        pq.add(score3);

        // 최대값이 0일 경우 전부 맞추지 못했다는 뜻이므로 1,2,3 리턴
        int max = pq.peek().score;
        if (max == 0) {
            answer = new int[]{1, 2, 3};
            return answer;
        }

        // 우선순위 큐에서 max값과 일치하는 Score를 꺼내 no를 ans에 add
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Score score = pq.poll();
            if (score.score == max) {
                ans.add(score.no);
            }
        }
        // no를 오름차순으로 정렬
        ans.sort(Comparator.naturalOrder());

        answer = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            answer[i] = ans.get(i);
        }

        return answer;
    }

    private class Solver {
        int[] p1 = {1, 2, 3, 4, 5};
        int[] p2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] p3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int idxP1 = 0;
        int idxP2 = 0;
        int idxP3 = 0;

        public int[] solve(int answer) {
            int[] res = new int[3];

            if (p1[idxP1] == answer) {
                res[0] = 1;
            }
            if (p2[idxP2] == answer) {
                res[1] = 1;
            }
            if (p3[idxP3] == answer) {
                res[2] = 1;

            }
            idxP1++;
            if (idxP1 >= p1.length) {
                idxP1 = 0;
            }
            idxP2++;
            if (idxP2 >= p2.length) {
                idxP2 = 0;
            }
            idxP3++;
            if (idxP3 >= p3.length) {
                idxP3 = 0;
            }

            return res;
        }
    }

    private class Score implements Comparable<Score> {
        int no;
        int score;

        public Score(int no, int score) {
            this.no = no;
            this.score = score;
        }

        @Override
        public int compareTo(Score o) {
            return this.score <= o.score ? 1 : -1;
        }
    }
}