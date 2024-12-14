class Solution {
    int min = 9;
    int n;
    int target;

    public int solution(int N, int number) {
        n = N;
        target = number;
        dfs(0, 0);
        return min > 8 ? -1 : min;
    }//solution

    void dfs(int cnt, int prev){
        if(cnt > 8) return;
        if(prev == target) {
            min = Math.min(cnt, min);
            return;
        }

        int tmpN = n;
        for(int i=0; i<8-cnt; i++){
            int nCnt = cnt + i + 1;
            dfs(nCnt, prev + tmpN);
            dfs(nCnt, prev - tmpN);
            dfs(nCnt, prev / tmpN);
            dfs(nCnt, prev * tmpN);

            tmpN = tmpN * 10 + n;
        }
    }//dfs
    
}//class