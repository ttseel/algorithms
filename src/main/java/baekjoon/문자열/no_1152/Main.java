package baekjoon.문자열.no_1152;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String trim = s.trim();
        if (trim.isEmpty()) {
            System.out.println(0);
        } else {
            String[] split = trim.split(" ");
            System.out.println(split.length);
        }
    }
}