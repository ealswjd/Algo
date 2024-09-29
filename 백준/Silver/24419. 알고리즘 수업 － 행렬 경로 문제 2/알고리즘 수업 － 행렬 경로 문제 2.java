import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/24419
public class Main {
    static final int MOD = 1_000_000_007;
    static int N;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            br.readLine();
        }

        long cnt = getCnt();
        System.out.println(cnt + " " + N*N);
    }//main

    
    private static long getCnt() {
        long[][] dp = new long[N][N];

        for(int i=0; i<N; i++) {
            dp[i][0] = 1;
            dp[0][i] = 1;
        }

        for(int r=1; r<N; r++) {
            for(int c=1; c<N; c++) {
                dp[r][c] = (dp[r-1][c] + dp[r][c-1]) % MOD;
            }
        }

        long total = 1;
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                total = (total + dp[r][c]) % MOD;
            }
        }

        return total % MOD;
    }//getCnt

    
}//class