package leetcode.binary.sumoftwointegers;

public class SumOfTwoIntegers {

    public static void main(String[] args) {
        Solution s = new Solution();

        int a = 0;
        int b = 1;
        System.out.println(s.getSum(a, b));
    }
}

class Solution {

    public int getSum(int a, int b) {
        int res = 99999;
        while (true) {
            int andVal = a & b;

            if (andVal == 0) {
                res = a | b;
                break;
            }
            b = a ^ b;
            a = andVal << 1;
        }

        //5 : 0101
        //10: 1010

        // 2+3
        // 2: 0010
        // 3: 0011
        // 5: 0101
        // AND: 0010 != 0 이므로 << 0100
        // XOR: 0001

        // 0100과 0001에 대해
        // AND: 0000 == 0 이므로 stop
        // OR: 0101

        // 2+5
        // 2: 0010
        // 5: 0101
        // 7: 0111

        // 2+7
        // 2: 0010
        // 7: 0111
        // 9: 1001
        // AND 후 << : 0100
        // XOR: 0101

        // 0100과 0101에 대해
        // AND: 0100 != 0 이므로 << 1000
        // XOR: 0001

        // 1000과 0001에 대해
        // AND: 0000 = 0 이므로 stop
        // OR: 1001

        // 2와 AND: 0000
        // 7과 AND: 0100 -> << : 1000

        return res;
    }
}