import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/21739
public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 얼음길 가로의 길이
        br.close();

        long[] dp = new long[N+1];
        dp[0] = 1;

        for(int i=1; i<=N; i++) {
            for(int j=0; j<i; j++) {
                dp[i] += dp[j] * dp[i-j-1];
                dp[i] %= MOD;
            }
        }

        System.out.print(dp[N]);
    }//main

}//class