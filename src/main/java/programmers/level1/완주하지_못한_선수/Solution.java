package programmers.level1.완주하지_못한_선수;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Solution  {
    public static void main(String[] args){

        String[] inputParti = {"leo", "kiki", "eden"};
        String[] inputCompl = {"eden", "kiki"};

        String res = solution(inputParti, inputCompl);

        System.out.println("Result: " + res);


    }

    public static String solution(String[] participant, String[] completion) {

        Map<String, Integer> participantPerson = new ConcurrentHashMap<>();
        for(String parti : participant) {
            if (!participantPerson.containsKey(parti)) {
                participantPerson.put(parti, 1);
            } else {
                participantPerson.put(parti, participantPerson.get(parti) + 1);
            }
        }

        for(String complete : completion) {
//            if(!participantPerson.containsKey(complete)) {
//                answer = complete;
//                break;
//            }
            Integer nameCount = participantPerson.get(complete);
            nameCount--;
            if (nameCount == 0) {
                participantPerson.remove(complete);
            } else {
                participantPerson.put(complete, nameCount);
            }
        }
        String remains = (String) participantPerson.keySet().toArray()[0];

        return remains;
    }
}
