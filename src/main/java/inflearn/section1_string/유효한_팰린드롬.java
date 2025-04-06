package inflearn.section1_string;

import java.util.Scanner;

public class 유효한_팰린드롬 {
    public static void main(String[] ars) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

//        System.out.println("input: " + input);
        String onlyAlphabet = input.toUpperCase().replaceAll("[^A-Z]", "");
//        System.out.println("after replace: " + onlyAlphabet);

        String result = "YES";
        for (int i=0; i<onlyAlphabet.length()/2; i++) {
            if(onlyAlphabet.charAt(i) != onlyAlphabet.charAt(onlyAlphabet.length()-1-i)){
                result = "NO";
                break;
            }
        }
        System.out.println(result);
    }
}
