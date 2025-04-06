package baekjun.문자열.no_9012;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int i=0; i<n; i++){
            String s = sc.next();
//            System.out.println("input: " + s);
            Stack<Character> stack = new Stack<>();

            for(int j=0; j<s.length(); j++) {
                char c = s.charAt(j);
                if(stack.isEmpty()){
                    stack.push(c);
                } else {
                    char prev = stack.peek();
                    if(prev == '(' && c == ')'){
                        stack.pop();
                    } else {
                        stack.push(c);
                    }
                }
//                for (char t : stack) {
//                    System.out.print(t);
//                }
//                System.out.println();
            }

            String result = stack.isEmpty() ? "YES" : "NO";
            System.out.println(result);
        }
    }
}
