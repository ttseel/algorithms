package programmers.kakao.blindrecruitment2022.신고결과받기;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;

//        String[] id_list = {"con", "ryan"};
//        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
//        int k = 3;

        System.out.println(Arrays.toString(solution.solution(id_list, report, k)));
    }
}


class Solution {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        Map<String, Integer> abuseCntMap = new ConcurrentHashMap<>();
        Map<String, List<String>> abuseAndReportIdMap = new ConcurrentHashMap<>();
        Map<String, Map<String, Boolean>> reportAndAbuseIdMap = new ConcurrentHashMap<>();
        Map<String, Integer> scoreCntMap = new ConcurrentHashMap<>();

        for (String record : report) {
            String reportId = record.split(" ")[0];
            String abuseId = record.split(" ")[1];

            if (!reportAndAbuseIdMap.containsKey(reportId)) {
                reportAndAbuseIdMap.put(reportId, new ConcurrentHashMap<>());
            }

            if (!reportAndAbuseIdMap.get(reportId).containsKey(abuseId)) {
                reportAndAbuseIdMap.get(reportId).put(abuseId, true);
                if (!abuseCntMap.containsKey(abuseId)) {
                    abuseCntMap.put(abuseId, 1);
                } else {
                    abuseCntMap.put(abuseId, abuseCntMap.get(abuseId) + 1);
                }

                if (!abuseAndReportIdMap.containsKey(abuseId)) {
                    List<String> reportIds = new ArrayList<>();
                    reportIds.add(reportId);
                    abuseAndReportIdMap.put(abuseId, reportIds);
                } else {
                    abuseAndReportIdMap.get(abuseId).add(reportId);
                }
            }
        }

        abuseCntMap.keySet().forEach(abuseId -> {
            if (abuseCntMap.get(abuseId) >= k) {
                abuseAndReportIdMap.get(abuseId).forEach(reportId -> {
                    if (!scoreCntMap.containsKey(reportId)) {
                        scoreCntMap.put(reportId, 1);
                    } else {
                        scoreCntMap.put(reportId, scoreCntMap.get(reportId) + 1);
                    }
                });
            }
        });

        for (int i = 0; i < id_list.length; i++) {
            answer[i] = scoreCntMap.getOrDefault(id_list[i], 0);
        }

        return answer;
    }
}