import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21938
public class Main {
    static int N, M, T;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                int rgb = 0;
                for(int k=0; k<3; k++) {
                    rgb += Integer.parseInt(st.nextToken());
                }
                map[i][j] = rgb/3;
            }
        }

        T = Integer.parseInt(br.readLine()); // 경계값

        int cnt = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] < T || visited[i][j]) continue;
                visited[i][j] = true;
                dfs(i, j);
                cnt++;
            }
        }

        System.out.print(cnt);
    }//main

    private static void dfs(int r, int c) {
        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            dfs(nr, nc);
        }
    }//dfs

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] < T;
    }//rangeCheck

    private static void init() {
        map = new int[N][M];
        visited = new boolean[N][M];
    }//init

}//class