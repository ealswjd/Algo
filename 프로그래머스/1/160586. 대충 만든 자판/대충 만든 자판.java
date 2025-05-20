import java.util.*;

class Solution {
    int[] seq;
    
    public int[] solution(String[] keymap, String[] targets) {
        int size = targets.length;
        int[] answer = new int[size];
        seq = new int[26];
        Arrays.fill(seq, 111);
        
        char c;
        int idx;
        for(int i=0, len=keymap.length; i<len; i++){
            for(int j=0, strLen=keymap[i].length(); j<strLen; j++){
                c = keymap[i].charAt(j);
                idx = c-'A';
                if(seq[idx] > j+1) seq[idx] = j+1;
            }
        }
        
        for(int i=0; i<size; i++){
            for(int j=0, len=targets[i].length(); j<len; j++){
                idx = targets[i].charAt(j) - 'A';
                if(seq[idx] == 111) {
                    answer[i] = -1;
                    break;
                }
                answer[i] += seq[idx];
            }
            
        }
        
        return answer;
    }//solution
    
    
}//class