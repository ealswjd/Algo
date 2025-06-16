import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15624
public class Main {
    private static final int N = 1_000_000, MOD = 1_000_000_007;
    private static long[] dp;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // n번째 피보나치 수

        System.out.print(dp[n]);
    }//sol


    private static void init() {
        dp = new long[N+1];
        dp[1] = 1;

        for(int i=2; i<=N; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % MOD;
        }
    }//init
    

}//class