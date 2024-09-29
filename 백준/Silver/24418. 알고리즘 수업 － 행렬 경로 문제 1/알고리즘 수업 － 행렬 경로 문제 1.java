import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/24418
public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for(int i=0; i<N; i++) {
            br.readLine();
        }

        int cnt = getCnt();
        System.out.print(cnt + " " + N*N);
    }//main

    private static int getCnt() {
        int cnt = 1;
        int[][] dp = new int[N+1][N+1];
        dp[1][1] = 1;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                if(r != 1 || c != 1) dp[r][c] = dp[r-1][c] + dp[r][c-1];
                cnt += dp[r][c];
            }
        }

        return cnt;
    }//getCnt


}//class