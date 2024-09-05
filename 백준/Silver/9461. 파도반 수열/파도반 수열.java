import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9461
public class Main {
    static final int N = 100;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        init();

        StringBuilder ans = new StringBuilder();
        while(T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            ans.append(dp[n]).append('\n');
        }

        System.out.print(ans);
    }//main

    
    private static void init() {
        dp = new long[N+1];
        dp[1] = dp[2] = 1;

        for(int i=3; i<=N; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }
    }//init

    
}//class