import java.util.*;

class Solution {
    public static int[] solution(String[] id_list, String[] report, int k) {
      int[] answer = new int[id_list.length];

      Map<String, Integer> reportCnt = new HashMap<>(); // 각 유저별 신고당한 횟수
      Map<String, ArrayList<String>> reportRecord = new HashMap<>(); // 누가 누구를 신고했는지

      for(String id : id_list) {
         reportCnt.put(id, 0); // 키:유저 아이디   값:신고당한횟수
         reportRecord.put(id, new ArrayList<>());  // 키:유저아이디  값:신고한 아이디 리스트
      }

      for(String r : report) { // 신고목록만큼
         String[] name = r.split(" ");
         String from = name[0]; // 신고자
         String to = name[1];   // 신고 당한사람
         ArrayList<String> record = reportRecord.get(from);

         if(record.contains(to)) continue; // 이미 신고자가 똑같은 유저 신고를 했었다면

         record.add(to); // 아니라면 유저가 신고한 유저 넣어주기
         reportCnt.put(to, reportCnt.get(to) + 1); // 결과맵에 신고당한 횟수 증가
      }

      Iterator<String> it;
      for(int i=0; i<id_list.length; i++) { // 유저 수만큼
         String id = id_list[i];
         ArrayList<String> record = reportRecord.get(id);
         it = record.iterator();
         while(it.hasNext()) {
            if(reportCnt.get(it.next()) >= k) {
               answer[i]++;
            }
         }
      }

      return answer;
   }
}