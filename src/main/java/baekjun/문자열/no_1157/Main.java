package baekjun.문자열.no_1157;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine().toUpperCase();
        Map<Character, Integer> countMap = new HashMap<>();

        int maxCount = Integer.MIN_VALUE;

        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);

            Integer count = countMap.get(c);
            if(count == null) {
                countMap.put(c, 1);
                maxCount = Math.max(maxCount,1);
            } else {
                int newCount = count+1;
                countMap.put(c, newCount);
                maxCount = Math.max(maxCount,newCount);
            }
        }

//        countMap.forEach((k,v) -> System.out.println(k + " / " + v));
//        System.out.println("maxCount : " + maxCount);


        char maxChar = '?';
        int maxCharCount = 0;
        for(Character key : countMap.keySet()){
            if(countMap.get(key) == maxCount) {
                maxChar = key;
                maxCharCount++;
                if(maxCharCount > 1) {
                    maxChar = '?';
                    break;
                }
            }
        }

        System.out.println(maxChar);
//        System.out.println(maxCount);
//        System.out.println(countMap.keySet());
    }
}
