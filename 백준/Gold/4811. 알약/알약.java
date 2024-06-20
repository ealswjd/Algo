import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/4811
public class Main {
    static final int N = 30;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dp = new long[N+1][N+1];

        for(int i=0; i<=N; i++) {
            for(int j=0; j<=N; j++) {
                if(j>i) continue;
                if(j == 0) dp[i][j] = 1;
                else dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        StringBuilder ans = new StringBuilder();
        int n;
        while(true) {
            n = Integer.parseInt(br.readLine());
            if(n == 0) break;

            ans.append(dp[n][n]).append('\n');
        }

        System.out.print(ans);
    }//main

}//class