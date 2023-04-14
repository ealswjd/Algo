package programmers;

import java.util.Arrays;

// https://school.programmers.co.kr/learn/courses/30/lessons/160586
class Solution_대충만든자판 {
    int[] seq; // 알파벳 순서 배열
    
    public int[] solution(String[] keymap, String[] targets) {
        int size = targets.length;
        int[] answer = new int[size]; // 답
        seq = new int[26]; 
        Arrays.fill(seq, 111); // 큰 숫자로 초기화
        
        char c;
        int idx;
        for(int i=0, len=keymap.length; i<len; i++){
            for(int j=0, strLen=keymap[i].length(); j<strLen; j++){
                c = keymap[i].charAt(j);
                idx = c-'A';
                if(seq[idx] > j+1) seq[idx] = j+1; // 더 적게 누를 수 있다면 변경
            }//for j
        }//for i
        
        for(int i=0; i<size; i++){
            for(int j=0, len=targets[i].length(); j<len; j++){
                idx = targets[i].charAt(j) - 'A';
                if(seq[idx] == 111) { // 할당되지 않은 경우
                    answer[i] = -1;
                    break;
                }//if
                answer[i] += seq[idx];
            }//for j 
            
        }//for i
        
        return answer;
    }//solution
    
}//class
