import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6144
public class Main {
    static final int W=0, D=1;
    static int N, M;
    static int[][] charms;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        charms = new int[N+1][2];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            charms[i][W] =  Integer.parseInt(st.nextToken()); // 무게
            charms[i][D] =  Integer.parseInt(st.nextToken()); // 점수
        }
        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int[][] dp = new int[N+1][M+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(charms[i][W] > j) dp[i][j] = dp[i-1][j];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-charms[i][W]] + charms[i][D]);
            }
        }

        return dp[N][M];
    }//getMax

}//class