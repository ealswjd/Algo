import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18427
public class Main {
    static final int MOD = 10007;
    static int N, M, H;
    static int[][] blocks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 최대 블록 개수
        H = Integer.parseInt(st.nextToken()); // 목표 높이

        blocks = new int[N+1][];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            blocks[i] = new int[st.countTokens()];
            for(int j=0; j<blocks[i].length; j++) {
                blocks[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    private static int getCnt() {
        int[][] dp = new int[N+1][H+1];
        dp[0][0] = 1;

        for(int i=1; i<=N; i++) {
            dp[i][0] = 1;
            for(int j=1; j<=H; j++) {
                dp[i][j] += dp[i-1][j] % MOD;
                for(int b : blocks[i]) {
                    if(j >= b) dp[i][j] += dp[i-1][j-b] % MOD;
                }
            }
        }

        return dp[N][H] % MOD;
    }//getCnt

}//class