package programmers;

import java.util.HashMap;
// https://programmers.co.kr/learn/courses/30/lessons/42578
// Level 2. 해시 > 위장
class Camouflage {
    public int solution(String[][] clothes) {
        int answer = clothes.length; 
		HashMap<String, Integer> map = new HashMap<>();
		for(int i=0; i<clothes.length; i++) {
			map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1); // key:옷종류, value:개수
		}
	   /* 상의 A 하의 B
          조합해 입을 수 있는 경우의 수 : A * B
          상의만 입을 수 있고 하의만 입을 수 있는 경우의 수 : (A+1) * (B+1)
          최소 한 개의 의상은 입어야 하므로 아예 안입었을 경우 제외 : (A+1) * (B+1) - 1   */
		if(map.size() > 1) { // 옷 종류가 2개 이상일 경우
			int cnt = 1;
			for(String key : map.keySet()) {
				cnt *= map.get(key) + 1; // 경우의 수 
			}
			answer = cnt - 1; // 아무것도 안 입었을 경우 빼주기
		}
		
		return answer; // 옷 종류가 1개일 경우 의상 개수 리턴
    }
}
