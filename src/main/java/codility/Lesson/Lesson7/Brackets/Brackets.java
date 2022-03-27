package codility.Lesson.Lesson7.Brackets;

import java.util.Stack;

public class Brackets {

    public static void main(String[] args) {
        Solution s = new Solution();

//        String S = "{[()()]}";
//        String S = "([)()]";
//        String S = "{{{{";
        String S = "}}}";

        System.out.println(s.solution(S));
    }
}

class Solution {

    public int solution(String S) {
        if (S.length() == 0) {
            return 1;
        }

        Stack<Character> leftStack = new Stack<>();

        char[] charArr = S.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            if (i == 0 && !isLeftBracket(charArr[i])) {
                return 0;
            }

            if (isLeftBracket(charArr[i])) {
                leftStack.push(charArr[i]);
            } else {
                if (leftStack.size() > 0) {
                    char left = leftStack.pop();
                    if (!isSameShapeBracket(left, charArr[i])) {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }

        if (leftStack.size() > 0) {
            return 0;
        }

        return 1;
    }

    public boolean isLeftBracket(char b) {
        if (b == '(' || b == '{' || b == '[') {
            return true;
        }
        return false;
    }

    public boolean isSameShapeBracket(char left, char right) {
        if ((left == '(' && right == ')') || (left == '{' && right == '}') || (left == '['
            && right == ']')) {
            return true;
        }
        return false;
    }
}
