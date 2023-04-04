package programmers;

import java.util.*;

public class Solution_추억점수 {

	Map<String, Integer> map;
    
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        map = new HashMap<>();
        
        for(int i=0, len=name.length; i<len; i++){
            map.put(name[i], yearning[i]);
        }//for
        
        int[] answer = getScore(photo);
        
        return answer;
    }//solution
    
    private int[] getScore(String[][] photo) {
        int score = 0, len=photo.length;
        int[] scores = new int[len];
        for(int i=0; i<len; i++){
            score = 0;
            for(String key : photo[i]) {
                if(map.containsKey(key)){
                    score += map.get(key);
                }//if
            }//for key
            scores[i] = score;
        }//for i
        
        return scores;
    }//getScore
    
}//class
