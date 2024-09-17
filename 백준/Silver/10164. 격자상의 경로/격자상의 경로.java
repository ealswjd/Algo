import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10164
public class Main {
    static int N, M, K;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // ○로 표시된 칸

        init();

        int cnt = getCnt();
        System.out.println(cnt);
    }//main


    private static int getCnt() {
        if(K == 0) return dp[N][M];

        int r = (K-1) / M + 1;
        int c = (K-1) % M + 1;
        int[][] fromK = new int[N+1][M+1]; // K에서 시작해서 N*M으로 가는 경로
        fromK[r][c] = 1;

        for(int i=r; i<=N; i++) {
            for(int j=c; j<=M; j++) {
                if(i == r && j == c) continue;
                fromK[i][j] = (i > r ? fromK[i-1][j] : 0) + (j > c ? fromK[i][j-1] : 0);
            }
        }

        return dp[r][c] * fromK[N][M];
    }//getCnt


    private static void init() {
        dp = new int[N+1][M+1];
        dp[1][1] = 1;

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(i == 1 && j == 1) continue;
                dp[i][j] = (i > 1 ? dp[i - 1][j] : 0) + (j > 1 ? dp[i][j - 1] : 0);
            }
        }
    }//init

    
}//class