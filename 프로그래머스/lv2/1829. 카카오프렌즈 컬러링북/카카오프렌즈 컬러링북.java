import java.util.LinkedList;
import java.util.Queue;

class Solution {
	private boolean[][] checked;
    private int max;
    private int[] dr = {-1, 1, 0, 0};
    private int[] dc = {0, 0, -1, 1};
    
    public int[] solution(int m, int n, int[][] picture) {
        int cnt = 0; // 영역 개수
        max = 0; // 가장 큰 영역
        checked = new boolean[m][n]; // 방문 체크
        
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(checked[i][j] || picture[i][j] == 0) continue;
                checked[i][j] = true;
                bfs(i, j, picture, m, n);
                cnt++;
            }//for j
        }//for i
        
        return new int[] {cnt, max};
    }//solution
    
    private void bfs(int r, int c, int[][] picture, int m, int n) {
		Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        int color = picture[r][c];
        int cnt = 1;
        
        int[] cur;
        while(!q.isEmpty()){
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(rangeCheck(nr, nc, m, n) 
                   || checked[nr][nc] || picture[nr][nc] != color) continue;
                checked[nr][nc] = true;
                cnt++;
                q.offer(new int[] {nr, nc});
            }//for
        }//while
        max = Math.max(cnt, max);
	}//bfs
    
    private boolean rangeCheck(int r, int c, int m, int n) {
        return r < 0 || r >= m || c < 0 || c >= n; 
    }

}//class