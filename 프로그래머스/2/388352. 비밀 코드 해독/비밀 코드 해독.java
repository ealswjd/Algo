class Solution {
    private final int LEN= 5; // 비밀 코드 길이
    private int answer, N, M; // 가능한 조합 개수, 1부터 N, M번의 시도
    private int[] arr; // 조합 배열
    
    
    public int solution(int n, int[][] q, int[] ans) {
        init(n, q, ans);
        comb(1, 0, q, ans);
        
        return answer;
    }//solution
    
    
    private void comb(int cur, int cnt, int[][] q, int[] ans) {
        if(cnt == LEN) {
            if(isPossible(q, ans)) {
                answer++;
            }
            
            return;
        }
        
        for(int i=cur; i<=N; i++) {
            arr[cnt] = i;
            comb(i+1, cnt+1, q, ans);
        }
    }//comb
    
    
    private boolean isPossible(int[][] q, int[] ans) {
        int cnt; // 비밀 코드에 포함된 정수 개수
        
        for(int i=0; i<M; i++) {
            cnt = 0;
            for(int j=0; j<LEN; j++) {
                for(int k=0; k<LEN; k++) {
                    if(q[i][j] == arr[k]) {
                        cnt++;
                        break;
                    }
                }
            }
            
            // 포함된 정수 개수가 다름
            if(cnt != ans[i]) return false;
        }
        
        return true;
    }//isPossible
    
    
    private void init(int n, int[][] q, int[] ans) {
        M = ans.length;
        N = n;
        arr = new int[LEN];
    }//init
    
    
}//class