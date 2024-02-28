import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2748
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        long[] dp = new long[N+1];
        dp[1] = 1;
        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }//for

        System.out.print(dp[N]);
    }//main

}//class