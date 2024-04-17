import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 이름(난이도) : 정상 회담 2 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/1670
 * */
public class Main {
    static final int MOD = 987654321;
    static int N;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 정상 회담에 참가한 사람의 수
        br.close();

        init();
        sol(N);

        System.out.print(dp[N]);
    }//main

    private static long sol(int n) {
        if(dp[n] != -1) return dp[n] % MOD;

        dp[n] = 0;
        for(int i=2; i<=n; i+=2) {
            dp[n] += (sol(i-2) * sol(n-i)) % MOD;
        }

        return dp[n] %= MOD;
    }//sol

    private static void init() {
        dp = new long[N+1];
        Arrays.fill(dp, -1);
        dp[0] = 1;
        if(N >= 2) dp[2] = 1;
    }//init

}//class