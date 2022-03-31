package codility.Exercises.Exercise1.LongestPassword;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 글자수 짝수, 숫자는 홀수 개 있어야 함 가능한 비밀번호 중 가장 긴 것을 선택하는 것이 목표
 * <p>
 * Assume that:
 * N is an integer within the range [1..200];
 * string S consists only of printable ASCII characters and spaces.
 */
public class Solution {

    Map<Character, Boolean> possibleLetter;
    Map<Character, Boolean> possibleDigit;

    public int solution(String S) {

        // space로 나눠 후보군 만들기
        String[] candidates = S.split(" ");

        // validation 전 가능한 문자열 Hash 생성
        initPossibleChar();

        // validation
        int maxLength = Integer.MIN_VALUE;
        for (int i = 0; i < candidates.length; i++) {
            maxLength = Math.max(maxLength, validation(candidates[i]));
        }

        return maxLength;
    }

    private void initPossibleChar() {
        possibleDigit = new HashMap<>();
        possibleLetter = new HashMap<>();

        for (int i = '0'; i <= '9'; i++) {
            possibleDigit.put((char) i, true);
        }
        for (int i = 'a'; i <= 'z'; i++) {
            possibleLetter.put((char) i, true);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            possibleLetter.put((char) i, true);
        }
    }

    private int validation(String candidate) {
        char[] charCandidate = candidate.toCharArray();

        int digitCnt = 0;
        int letterCnt = 0;
        for (int i = 0; i < charCandidate.length; i++) {
            if (!isPossibleChar(charCandidate[i])) {
                return -1;
            }

            if (possibleDigit.containsKey(charCandidate[i])) {
                digitCnt++;
            } else if (possibleLetter.containsKey(charCandidate[i])) {
                letterCnt++;
            }
        }

        // 숫자가 짝수이거나, 문자가 홀수이면 실패
        if (isEven(digitCnt) || !isEven(letterCnt)) {
            return -1;
        }
        return candidate.length();
    }

    private boolean isPossibleChar(char c) {
        return possibleDigit.containsKey(c) || possibleLetter.containsKey(c);
    }

    private boolean isEven(int n) {
        return n % 2 == 0;
    }
}
