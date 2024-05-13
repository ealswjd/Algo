import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1904
public class Main {
    static final int MOD=15746;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int[] dp = new int[N+1];
        dp[1] = 1;
        if(N>1) dp[2] = 2;
        for(int i=3; i<=N; i++) {
            dp[i] = (dp[i-1]+dp[i-2]) % MOD;
        }

        System.out.print(dp[N]);
    }//main

}//class