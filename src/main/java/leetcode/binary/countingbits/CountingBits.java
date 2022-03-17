package leetcode.binary.countingbits;

import java.util.Arrays;

public class CountingBits {

    public static void main(String[] args) {
        Solution s = new Solution();
        int input = 5;

        System.out.println(Arrays.toString(s.countBits(input)));
    }
}

class Solution {

    public int[] countBits(int n) {
        int[] res = new int[n + 1];

        res[0] = 0;
        for (int i = 1; i < n + 1; i++) {
            char[] charN = convertToBinary(i);
            int numberOf1 = 0;
            for (char c : charN) {
                if (c == '1') {
                    numberOf1++;
                }
            }
            res[i] = numberOf1;
        }

        return res;
    }

    private char[] convertToBinary(int n) {
        StringBuilder sb = new StringBuilder();

        if (n == 0) {
            sb.append(0);
        }

        while (n > 0) {
            sb.append(n % 2);
            n /= 2;
        }

        return sb.reverse().toString().toCharArray();
    }
}