import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1513
public class Main {
    static final int MOD = 1_000_007;
    static int N, M, C;
    static int[][] map;
    static int[][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        C = Integer.parseInt(st.nextToken()); // 오락실 개수

        init(); // 초기화

        for(int i=1; i<=C; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = i;
        }//for
        br.close();

        StringBuilder ans = new StringBuilder();
        int r=0, c=0;
        for(int i=0; i<=C; i++) {
            ans.append(dfs(r, c, 0, i) % MOD).append(' ');
        }//for

        System.out.print(ans);
    }//main


    private static int dfs(int r, int c, int prev, int cnt) {
        if(rangeCheck(r, c)) return 0;
        if(r==N-1 && c==M-1) {
            if(cnt == 0 && map[r][c]==0) return 1;
            else if(cnt == 1 && map[r][c] > prev) return 1;
            return 0;
        }
        if(dp[prev][cnt][r][c] != -1) return dp[prev][cnt][r][c] % MOD;

        dp[prev][cnt][r][c] = 0;
        if(map[r][c] == 0) {
            dp[prev][cnt][r][c] = (dfs(r+1, c, prev, cnt) + dfs(r, c+1, prev, cnt)) % MOD;
        }else if(map[r][c] > prev && cnt > 0) {
            dp[prev][cnt][r][c] = (dfs(r+1, c, map[r][c], cnt-1) + dfs(r, c+1, map[r][c], cnt-1)) % MOD;
        }

        return dp[prev][cnt][r][c] % MOD;
    }//dfs


    private static boolean rangeCheck(int r, int c) {
        return r>=N || c>=M;
    }//rangeCheck


    private static void init() {
        map = new int[N][M];
        dp = new int[C+1][C+1][N][M]; // [마지막 방문][개수][행][열]
        for(int i=0; i<=C; i++) {
            for(int j=0; j<=C; j++) {
                for(int k=0; k<N; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }//for
    }//init

}//class