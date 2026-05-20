class Solution {
    
    public int solution(int n, int w, int num) {
        // 꺼낼 상자의 행, 열
        int targetRow = (num - 1) / w; 
        int targetCol = (num - 1) % w; 
        
        // 마지막 상자의 행, 열
        int lastRow = (n - 1) / w; 
        int lastCol = (n - 1) % w; 
        
        int answer = lastRow - targetRow;
        
        // 꺼낼 상자와 마지막 상자의 방향(홀짝)이 다르다면
        // 꺼낼 상자를 마지막 상자 방향 기준으로 뒤집기
        if ((targetRow % 2) != (lastRow % 2)) {
            targetCol = w - targetCol - 1;
        }
        
        // 꺼낼 상자의 꼭대기 층에 상자가 존재하면 1개 추가 
        if (targetCol <= lastCol) {
            answer++;
        }
        return answer;
    }
    
}