package programmers;

import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/67258
public class Solution_보석쇼핑 {
	Map<String, Integer> kinds = new HashMap<>();
	Map<String, Integer> counts = new HashMap<>();

//	public static void main(String[] args) {
////		String[] gems = {"DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"}; // [3, 7]
////		String[] gems = {"AA", "AB", "AC", "AA", "AC"}; // [1, 3]
////		String[] gems = {"XYZ", "XYZ", "XYZ"}; // [1, 1]
////		String[] gems = {"ZZZ", "YYY", "NNNN", "YYY", "BBB"}; // [1, 5]
//		String[] gems = {"A", "A", "A", "B", "B", "C", "B", "A", "B", "A"}; // [1, 5]
//		Solution_보석쇼핑 sol = new Solution_보석쇼핑();
//		int[] ans = sol.solution(gems);
//		System.out.println(Arrays.toString(ans));
//
//	}//main
	
	public int[] solution(String[] gems) {
        int[] answer = new int[2];
        for(String key : gems) {
            kinds.put(key, 0);
        }//for
        
        
        int start=0, end=0, len=gems.length, kindCnt=kinds.size();
        for(int i=0; i<len; i++) {
        	counts.put(gems[i], counts.getOrDefault(gems[i], 0)+1);
        	if(counts.size() == kindCnt) {
        		end = i;
        		break;
        	}
        }//for
        
        answer[0] = start+1;
        answer[1] = end+1;
        int min = end - start+1;
        while(end < len) {
        	while(start<len && counts.get(gems[start]) > 1) {
        		counts.put(gems[start], counts.get(gems[start])-1);
        		if(counts.get(gems[start]) == 0) counts.remove(gems[start]);
        		start++;
        	}//while
    		
        	if(counts.size()==kindCnt && min > end-start+1) {
        		answer[0] = start+1;
        		answer[1] = end+1;
        		min = end-start+1;
        	}//if
        	else {
        		end++;             		
        		if(end < len) counts.put(gems[end], counts.get(gems[end])+1);
        	}
        	
//        	if(start < len) counts.put(gems[start], counts.get(gems[start])+1);
        }//while
         

        return answer;
    }//solution

    
}//class
