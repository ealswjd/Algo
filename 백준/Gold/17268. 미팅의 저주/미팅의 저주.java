import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/17268
public class Main {
    private static final int MOD = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N+1];

        dp[0] = 1;
        dp[2] = 1;

        for(int i=4; i<=N; i+=2) {
            for(int j=0; j<i; j+=2) {
                dp[i] = (dp[i] + dp[j] * dp[i-2-j]) % MOD;
            }
        }

        System.out.print(dp[N]);
    }//main

}//class