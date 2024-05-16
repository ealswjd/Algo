import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12869
public class Main {
    static final int M=3, P=60, INF=987654321;
    static int N;
    static int[] scv;
    static int[][][] dp;
    static int[][] attack = {{1, 3, 9}, {1, 9, 3}, {3, 1, 9}, 
                             {3, 9, 1}, {9, 1, 3}, {9, 3, 1}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int cnt = dfs(scv[0], scv[1], scv[2]);
        System.out.print(cnt);
    }//main

    private static int dfs(int scv1, int scv2, int scv3) {
        if(scv1 < 0) scv1 = 0;
        if(scv2 < 0) scv2 = 0;
        if(scv3 < 0) scv3 = 0;
        if(scv1 == 0 && scv2 == 0 && scv3 == 0) return 0;
        if(dp[scv1][scv2][scv3] != -1) return dp[scv1][scv2][scv3];

        dp[scv1][scv2][scv3] = INF;
        for(int i=0; i<6; i++) {
            dp[scv1][scv2][scv3] = Math.min(dp[scv1][scv2][scv3],
                    dfs(scv1-attack[i][0], scv2-attack[i][1], scv3-attack[i][2]) + 1);
        }

        return dp[scv1][scv2][scv3];
    }//dfs

    private static void init() {
        scv = new int[M];
        dp = new int[P+1][P+1][P+1];
        for(int i=0; i<=P; i++) {
            for(int j=0; j<=P; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
    }//init

}//class