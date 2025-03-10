package inflearn.section1_string;

import java.util.*;

public class 숫자만_추출 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String r = input.replaceAll("[^0-9]", "");
        System.out.println(Integer.valueOf(r));
    }
}
