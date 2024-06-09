import java.io.BufferedReader;
import java.io.InputStreamReader;

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

        int cnt = 0;
        dp = new int[N+1][10][K+1];
        for(int i=1; i<10; i++) {
            dp[1][i][1<<i] = 1;
        }

        cnt = getCnt();
        System.out.print(cnt);
    }//main

    private static int getCnt() {

        for(int i=2; i<=N; i++) {
            for(int j=0; j<10; j++) {
                for(int k=0; k<(1<<10); k++) {
                    if(j==0) dp[i][j][k | 1<<j] = (dp[i][j][k | 1<<j] + dp[i-1][j+1][k]) % MOD;
                    else if(j==9) dp[i][j][k | 1<<j] = (dp[i][j][k | 1<<j] + dp[i-1][j-1][k]) % MOD;
                    else dp[i][j][k | 1<<j] = (dp[i][j][k | 1<<j] + dp[i-1][j-1][k] + dp[i-1][j+1][k]) % MOD;
                }
            }
        }

        int cnt = 0;
        for(int i=0; i<10; i++){
            cnt = (cnt + dp[N][i][(1<<10)-1]) % MOD;
        }

        return cnt;
    }//getCnt

}//class