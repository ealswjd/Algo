package programmers;

import java.util.HashMap;

/* https://programmers.co.kr/learn/courses/30/lessons/42576
 * Lv.1) 해시 > 완주하지 못한 선수                             */
public class NotCompletionPlayer {

	public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> player = new HashMap<>();
        for(String name : participant){ // 마라톤에 참여한 선수들 맵에 담아주기
            player.put(name, player.getOrDefault(name, 0) + 1);
        }
        
        for(String name : completion){ // 완주한 선수들 값 -1 감소
            player.put(name, player.get(name) - 1);
        }
        
        for(String name : player.keySet()){
            if(player.get(name) > 0){ // 값이 0 이상이면 완주하지 못한 선수
                answer = name;
                break;
            }
        }
        return answer;
    }//solution
	
}//class
