import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2411
public class Main {
    private static final int ITEM = 1, WALL = -1;
    private static int N, M, A;
    private static int[][] map; // N×M 모양의 맵

    
    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int[][][] dp = new int[A+1][N+1][M+1];
        dp[0][1][1] = 1;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                if(map[r][c] == WALL) continue;

                int cnt = map[r][c];

                for(int i=0; i<=A; i++) { // 아이템
                    if(i - cnt < 0) continue;
                    
                    dp[i][r][c] += dp[i-cnt][r-1][c] + dp[i-cnt][r][c-1];
                }
            }
        }


        return dp[A][N][M];
    }//getCnt

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        A = Integer.parseInt(st.nextToken()); // 아이템 개수
        int B = Integer.parseInt(st.nextToken()); // 장애물 개수

        map = new int[N+1][M+1]; // N×M 모양의 맵

        // 아이템의 위치
        for(int i=0; i<A; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = ITEM;
        }

        // 장애물의 위치
        for(int i=0; i<B; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = WALL;
        }

        br.close();
    }//init

    
}//class