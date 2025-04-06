package baekjun.문자열.no_10809;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        for (int i=(int)'a'; i<=(int)'z'; i++) {
            char c = (char)i;

            System.out.print(word.indexOf(c) + " ");
        }
    }
}
