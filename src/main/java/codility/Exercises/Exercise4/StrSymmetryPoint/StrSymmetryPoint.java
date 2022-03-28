package codility.Exercises.Exercise4.StrSymmetryPoint;

public class StrSymmetryPoint {

    public int solution(String S) {
        if (isEven(S.length())) {
            return -1;
        }

        char[] charS = S.toCharArray();

        for (int i = 0; i < charS.length / 2; i++) {
            if (charS[i] != charS[charS.length - i - 1]) {
                return -1;
            }
        }

        return S.length() / 2;
    }

    private boolean isEven(int length) {
        return length % 2 == 0;
    }
}
