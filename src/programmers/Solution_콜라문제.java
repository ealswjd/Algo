package programmers;

// https://school.programmers.co.kr/learn/courses/30/lessons/132267
public class Solution_콜라문제 {

	// a:마트에 주어야 하는 병수 b:a개를 가져다 주면 마트가 주는 병 수 n:가지고 있는 빈 병
    public int solution(int a, int b, int n) {
        int answer = 0;
        
        while(n >= a){
            answer +=  (n/a)*b;            
            n = (n/a)*b + (n%a);    
        }//while
        
        
        return answer;
    }//solution
    
}//class