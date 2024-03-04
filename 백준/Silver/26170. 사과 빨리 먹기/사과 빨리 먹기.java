import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26170
public class Main {
    static final int N=5, M=3, INF=Integer.MAX_VALUE;
    static int R, C, min;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];
        visited = new boolean[N][N];

        // 보드의 정보
        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }//for
        }//for

        // 학생의 현재 위치
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        br.close();

        min = INF;
        int cnt = 0;
        if(map[R][C] == 1) cnt = 1;
        visited[R][C] = true;
        dfs(R, C, 0, cnt);

        if(min == INF) min = -1;
        System.out.print(min);
    }//main

    private static void dfs(int r, int c, int move, int cnt) {
        if(cnt == M) {
            min = Math.min(min, move);
            return;
        }//if

        int nr, nc;
        for(int i=0; i<4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
            
            visited[nr][nc] = true;
            dfs(nr, nc, move+1, cnt+map[nr][nc]);
            visited[nr][nc] = false;
        }//for
    }//dfs

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N || map[r][c] == -1;
    }//rangeCheck

}//class