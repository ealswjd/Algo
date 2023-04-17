package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/133499
public class Solution_옹알이_2 {

	private String[] words = {"aya", "ye", "woo", "ma"}; // 네 가지 발음
    private int[] index = {3, 2, 3, 2};
    
    public int solution(String[] babbling) {
        int answer = 0;
        
        String prev;
        int len = 0, wordLen=0, end;
        boolean flag;
        for(String b : babbling) {
            prev = "";
            len = b.length();
            flag = false;
            for(int i=0; i<b.length();){
            	if(len == 0) break;
                for(int j=0; j<4; j++) {
                	end = i+index[j];
                	if(end >= b.length()) {
                		end = b.length();
                	}
                    if(b.substring(i, end).equals(words[j])){
                        if(prev.equals(words[j])) continue;
                        flag = true;
                        wordLen = words[j].length();
                        i += wordLen;
                        len -= wordLen;
                        prev = words[j];
                        break;
                    }else {
                    	flag = false;
                    }
                }//for j
                if(!flag) i++;
            }//for i
            
            if(len == 0) answer++;
        }//for
        
        return answer;
    }//solution
    
}//class
