import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/10422
public class Main {
    static final int N = 5_000, MOD = 1_000_000_007;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        init();

        StringBuilder ans = new StringBuilder();
        while(T-->0) {
            int L = Integer.parseInt(br.readLine());
            
            if(L%2 == 0) ans.append(sol(L/2, L/2));
            else ans.append(0);
            
            ans.append('\n');
        }//while
        br.close();

        System.out.print(ans);
    }//main

    private static int sol(int s, int e) {
        if(s < e || s < 0 || e < 0) return 0;
        if(s==0 && e==0) return 1;
        if(dp[s][e] != -1) return dp[s][e] % MOD;

        dp[s][e] = (sol(s-1, e) + sol(s, e-1)) % MOD;

        return dp[s][e] % MOD;
    }//sol

    private static void init() {
        dp = new int[N+1][N+1];
        for(int i=1; i<=N; i++){
            Arrays.fill(dp[i], -1);
        }
    }//init

}//class