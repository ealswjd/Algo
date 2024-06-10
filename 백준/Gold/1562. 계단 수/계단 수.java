import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/1562
public class Main {
    static final int MOD = 1_000_000_000;
    static final int K = 1023;
    static int N;
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();

        init();

        int cnt = 0;
        for (int i = 1; i < 10; i++) {
            cnt = (cnt + getCnt(1, i, 1 << i)) % MOD;
        }

        System.out.print(cnt);
    }//main

    
    private static int getCnt(int n, int num, int bit) {
        if(dp[n][num][bit] != -1) return dp[n][num][bit] % MOD;
        if(n == N) {
            if(bit == K) return 1;
            return 0;
        }

        dp[n][num][bit] = 0;

        if(num - 1 >= 0) dp[n][num][bit] += getCnt(n+1, num-1, bit | (1 << num-1)) % MOD;
        if(num + 1 < 10) dp[n][num][bit] += getCnt(n+1, num+1, bit | (1 << num+1)) % MOD;

        return dp[n][num][bit] %= MOD;
    }//getCnt

    
    private static void init() {
        dp = new int[N + 1][10][K + 1]; // 길이 N, 0~9, 0~9까지 사용 여부

        for(int i=0; i<=N; i++) {
            for(int j=0; j<10; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }//init

}//class