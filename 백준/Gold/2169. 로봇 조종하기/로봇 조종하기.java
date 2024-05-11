import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2169
public class Main {
    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int[][] dp = new int[N][M];
        int[][] tmp = new int[2][M]; // 0:max(왼->오, 위->아래) 1:max(오->왼, 위->아래)
        dp[0][0] = map[0][0];

        for(int c=1; c<M; c++) {
            dp[0][c] = map[0][c] + dp[0][c-1];
        }

        for(int r=1; r<N; r++) {

            dp[r][0] = dp[r-1][0] + map[r][0];
            tmp[0][0] = dp[r][0];
            for(int c=1; c<M; c++) {
                tmp[0][c] = Math.max(dp[r-1][c], tmp[0][c-1]) + map[r][c];
            }

            dp[r][M-1] = dp[r-1][M-1] + map[r][M-1];
            tmp[1][M-1] = dp[r][M-1];
            for(int c=M-2; c>=0; c--) {
                tmp[1][c] = Math.max(dp[r-1][c], tmp[1][c+1]) + map[r][c];
            }

            for(int c=0; c<M; c++) {
                dp[r][c] = Math.max(tmp[0][c], tmp[1][c]);
            }
        }

        return dp[N-1][M-1];
    }

    private static void init() {
        map = new int[N][M];
    }

}//class