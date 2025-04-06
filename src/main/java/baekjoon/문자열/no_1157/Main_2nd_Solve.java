package baekjoon.문자열.no_1157;

import java.util.*;

public class Main_2nd_Solve {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        s = s.toUpperCase();

        Map<Character, Integer> m = new HashMap<>();
        for(Character c : s.toCharArray()){
            m.putIfAbsent(c, 0);
            m.put(c, m.get(c) + 1);
        }
//        System.out.println(m.keySet());
//        System.out.println(m.values());

        char curMaxChar = 0;
        int curMaxCnt = 0;
        for(Character c : m.keySet()){
            if(curMaxCnt == 0) {
                curMaxChar = c;
                curMaxCnt = m.get(c);
                continue;
            }
            if(m.get(c) > curMaxCnt) {
                curMaxChar = c;
                curMaxCnt = m.get(c);
            } else if(m.get(c) == curMaxCnt) {
                curMaxChar = '?';
            }
        }
        System.out.println(curMaxChar);
    }
}
