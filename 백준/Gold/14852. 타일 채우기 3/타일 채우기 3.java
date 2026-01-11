import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/14852
public class Main {
    private static final int MOD = 1_000_000_007, MAX_N = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        long[] dp = new long[MAX_N + 1];

        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;

        for(int n=3; n<=N; n++) {
            dp[n] = (3 * dp[n-1] + dp[n-2] - dp[n-3] + MOD) % MOD;
        }

        System.out.print(dp[N]);
    }//main

}//class