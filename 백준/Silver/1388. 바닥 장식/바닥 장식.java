import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1388
public class Main {
    static final int H=0, W=1;
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }//for
        br.close();

        int cnt = 0;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(visited[r][c]) continue;

                visited[r][c] = true;
                if(map[r][c] == '-') dfs(r, c, W, map[r][c]);
                else dfs(r, c, H, map[r][c]);
                cnt++;
            }//for c
        }//for r

        System.out.print(cnt);
    }//main

    private static void dfs(int r, int c, int d, char pattern) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(check(nr, nc)|| map[nr][nc] != pattern) return;

        visited[nr][nc] = true;
        dfs(nr, nc, d, pattern);
    }//dfs

    private static boolean check(int r, int c) {
        return r >= N || c >= M || visited[r][c] ;
    }//check

}//class