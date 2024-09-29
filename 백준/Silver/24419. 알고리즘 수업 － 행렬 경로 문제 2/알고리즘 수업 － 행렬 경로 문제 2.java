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

        int cnt = getCnt();
        System.out.println(cnt + " " + N*N);
    }//main


    private static int getCnt() {
        int[][] dp = new int[N+1][N+1];
        dp[1][1] = 1;
        int total = 1;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(r != 1 || c != 1) {
                    dp[r][c] = (dp[r-1][c] + dp[r][c-1]) % MOD;
                }
                total = (total + dp[r][c]) % MOD;
            }
        }

        return total;
    }//getCnt


}//class