import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14494
public class Main {
    static final int MOD = 1_000_000_007;
    static int N, M;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        int cnt = getCnt(0, 0);
        System.out.print(cnt);
    }//main

    private static int getCnt(int r, int c) {
        if(r == N-1 && c == M-1) return 1;
        if(dp[r][c] != -1) return dp[r][c] % MOD;

        int cnt = 0;

        if(c+1 < M) cnt = getCnt(r, c+1) % MOD;
        if(r+1 < N) cnt = (cnt + getCnt(r+1, c)) % MOD;
        if(r+1 < N && c+1 < M) cnt = (cnt + getCnt(r+1, c+1)) % MOD;

        return dp[r][c] = cnt % MOD;
    }//getCnt

    private static void init() {
        dp = new int[N][M];

        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }//init

}//class