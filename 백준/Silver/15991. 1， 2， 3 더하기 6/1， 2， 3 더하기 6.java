import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15991
public class Main {
    private static final int N = 100_000, MOD = 1_000_000_009;
    private static long[] dp;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException{
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n;

        while(T-- > 0) {
            n = Integer.parseInt(br.readLine());
            ans.append(dp[n]).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new long[N+1];
        
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        dp[4] = 3;
        dp[5] = 3;
        dp[6] = 6;

        for(int i=7; i<=N; i++) {
            dp[i] = (dp[i-2] + dp[i-4] + dp[i-6]) % MOD;
        }
    }//init


}//class