package inflearn.section1_string;

import java.lang.*;
import java.util.*;

public class 암호 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int count = sc.nextInt();
        String input = sc.next();

//        System.out.println("count : " + count);
//        System.out.println("input : " + input);

        String result = "";

        int start = 0;

        for(int i = 0; i < count; i++) {
            String part = input.substring(start, start + 7);
            result += toCharacter(part);
            start = start+7;
        }
        System.out.println(result);
    }

    private static String toCharacter(String part) {
        int asciiCode = 0;
        for(int i = 0; i < part.length(); i++) {
            int backI = part.length()-1-i;
            char c = part.charAt(backI);

            if(c=='#'){
                asciiCode += toDecimal(i);
            } else {
                asciiCode += 0;
            }
        }
        return Character.toString((char) asciiCode);
    }

    public static int toDecimal(int i) {
        return (int) Math.pow(2, i);
    }
}
