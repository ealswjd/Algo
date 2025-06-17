class Solution {
    int answer; 
    boolean[] checked; // 단어를 사용했는지 체크
    

    public int solution(String begin, String target, String[] words) {
        checked = new boolean[words.length]; 
        dfs(begin, target, words, 0); // 시작단어, 목표단어, 단어배열, 횟수
        return answer;
    }// solution
    
    
    void dfs(String begin, String target, String[] words, int cnt){
        if(begin.equals(target)){ // 시작단어와 목표단어가 같다면 
            answer = cnt; // 횟수 대입 후 return
            return;
        }
        
        for(int i=0; i<words.length; i++){
            // 단어를 사용하지 않았고, 단어를 변환할 수 있는 조건에 부합하면
            if(!checked[i] && wordCheck(begin, words[i])){ 
                checked[i] = true; // 단어를 사용했다고 변경
                dfs(words[i], target, words, cnt+1); // 재귀함수 호출
                checked[i] = false; // 모든 경우의 수를 보기 위해 다시 false로 재설정
            }
        }
        
    }// dfs
    
    
    // 단어 변환이 가능한지 체크
    boolean wordCheck(String prevWord, String nextWord){ 
        int cnt = 0; // 스펠링이 다른 글자 개수
        
        for(int i=0; i<prevWord.length(); i++){
            // 이전 단어와 다음 단어의 스펠링이 다르다면 cnt 증가
            if(prevWord.charAt(i) != nextWord.charAt(i)) cnt++; 
        }
        
        // 한글자만 다르다면 조건을 만족하므로 true
        return cnt == 1 ? true : false; 
    }// wordChecked
    
    
}