import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://school.programmers.co.kr/learn/courses/30/lessons/468375?language=java
public class Solution {
    private static final int INF = 100000000;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private int R, C, K;
    private int er, ec; // 엘리베이터 위치
    private int[][] dist; // 패널 i에서 j위치
    private int[] floor; // 패널 i의 층 정보
    private int[] toE; // 엘리베이터 위치
    private int[] seqMask; // 선행 조건

    
    public int solution(int h, String[] grid, int[][] panels, int[][] seqs) {
        init(h, grid, panels, seqs);

        int mask = (1 << K);
        int[][] dp = new int[K][mask];

        for(int i=0; i<K; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dfs(0, 0, dp);
    }//solution

    private int dfs(int cur, int mask, int[][] dp) {
        if (mask == (1 << K) - 1) {
            return 0;
        }
        if (dp[cur][mask] != -1) {
            return dp[cur][mask];
        }

        int min = INF;

        for(int next=0; next<K; next++) {
            // 활성화 여부 확인
            if ((mask & (1 << next)) == 0) {
                // 선행 조건 만족 확인
                if ((mask & seqMask[next]) == seqMask[next]) {
                    int time = getTime(cur, next) + dfs(next, (mask | (1 << next)), dp);
                    min = Math.min(min, time);
                }
            }
        }

        return dp[cur][mask] = min;
    }//dfs

    private int getTime(int a, int b) {
        int af = floor[a], bf = floor[b]; // 층

        if (af != bf) { // 층이 다름
            return Math.abs(af - bf) + toE[a] + toE[b];
        } else { // 같은 층
            return dist[a][b];
        }
    }//getTime

    private void init(int h, String[] grid, int[][] panels, int[][] seqs) {
        R = grid.length;
        C = grid[0].length();
        boolean[][] isWall = new boolean[R][C];

        for(int i=0; i<R; i++) {
            char[] tmp = grid[i].toCharArray();
            for(int j=0; j<C; j++) {
                if (tmp[j] == '#') {
                    isWall[i][j] = true;
                } else if (tmp[j] == '@') {
                    er = i; ec = j;
                }
            }
        }

        // 패널 위치 기록
        K = panels.length;
        floor = new int[K];
        for(int i = 0; i< K; i++) {
            int f = panels[i][0];     // 층
            panels[i][1]--; // 행
            panels[i][2]--; // 열

            floor[i] = f;
        }

        // 선행 조건 기록
        seqMask = new int[K];
        for(int[] seq : seqs) {
            int prev = seq[0] - 1;
            int next = seq[1] - 1;

            seqMask[next] |= (1 << prev);
        }

        // 최단 거리 기록
        dist = new int[K][K];
        toE = new int[K];
        for(int i=0; i<K; i++) {
            bfs(i, isWall, panels);
        }

    }//init

    private void bfs(int start, boolean[][] isWall, int[][] panels) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        int[][] tmp = new int[R][C];

        int r = panels[start][1];
        int c = panels[start][2];

        q.offer(new int[] {r, c});
        visited[r][c] = true;
        dist[start][start] = 0;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];

            for(int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                if (!inRange(nr, nc) || isWall[nr][nc]) continue;
                if (visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});

                tmp[nr][nc] = tmp[r][c] + 1;
            }
        }

        // 현재 패널에서 엘리베이터까지 걸리는 시간
        toE[start] = tmp[er][ec];

        // 현재 패널에서 다른 패널까지 걸리는 시간
        for(int next=0; next<K; next++) {
            int nr = panels[next][1];
            int nc = panels[next][2];
            dist[start][next] = tmp[nr][nc];
        }
    }//bfs

    private boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }//inRange


}//class
