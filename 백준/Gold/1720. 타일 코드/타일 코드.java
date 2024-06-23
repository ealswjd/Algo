import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1720
public class Main {
    static int N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();

        dp = new long[N+1];
        dp[0] = 1;
        dp[1] = 1;
        if(N > 1) dp[2] = 3;

        for(int i=3; i<=N; i++) {
            dp[i] = dp[i-1] + dp[i-2] * 2;
        }

        long cnt;
        if(N % 2 == 0) cnt = (dp[N] + dp[N/2] + 2 * dp[(N-2)/2]) / 2;
        else cnt = (dp[N] + dp[(N-1)/2]) / 2;

        System.out.print(cnt);
    }//main

}//class