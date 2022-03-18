package programmers.dfsbfs.convertwords;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ConvertWords {

    public static void main(String[] args) {
        Solution s = new Solution();

        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(s.solution(begin, target, words));
    }
}


class Solution {

    int answer = 99;
    Map<String, Boolean> reservedMap = new HashMap<>();
    Stack<String> stack = new Stack<>();

    public int solution(String begin, String target, String[] words) {

        int localMin = 0;
        dfs(begin, target, words, localMin);

        return answer == 99 ? 0 : answer;
    }

    public void dfs(String begin, String target, String[] words, int localMin) {
        if (begin.equals(target)) {
            answer = Math.min(answer, localMin);
            return;
        }

        localMin++;
        String[] stack = new String[words.length];
        int stackCnt = 0;

        for (int i = 0; i < words.length; i++) {
            String candidateWord = words[i];

            if (isVisited(candidateWord)) {
                continue;
            }

            if (possibleToConvert(begin, candidateWord)) {
                stack[stackCnt++] = candidateWord;
                reservedMap.put(candidateWord, true);
            }
        }

        for (int i = 0; i < stackCnt; i++) {
            dfs(stack[i], target, words, localMin);
        }
    }

    public boolean isVisited(String word) {
        return reservedMap.containsKey(word);
    }

    public boolean possibleToConvert(String before, String after) {
        char[] beforeChar = before.toCharArray();
        char[] afterChar = after.toCharArray();

        int diffCnt = 0;
        for (int i = 0; i < beforeChar.length; i++) {
            if (beforeChar[i] != afterChar[i]) {
                diffCnt++;
            }
        }
        if (diffCnt != 1) {
            return false;
        }

        return true;
    }
}