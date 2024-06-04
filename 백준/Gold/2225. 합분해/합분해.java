import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2225
public class Main {
    static final int MOD = 1_000_000_000;
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        br.close();

        System.out.print(getCnt());
    }//main

    private static long getCnt() {
        long[][] dp = new long[K+1][N+1];

        for(int i=0; i<=N; i++) {
            dp[1][i] = 1;
        }

        for(int k=2; k<=K; k++) {
            for(int n=0; n<=N; n++) {
                for(int i=0; i<=n; i++) {
                    dp[k][n] += dp[k-1][i];
                }
                dp[k][n] %= MOD;
            }
        }

        return dp[K][N] % MOD;
    }//getCnt

}//class