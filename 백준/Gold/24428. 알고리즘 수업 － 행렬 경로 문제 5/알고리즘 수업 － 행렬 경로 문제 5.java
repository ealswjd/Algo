import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24428
public class Main {
    static int N, pCnt;
    static int[][] map;
    static int[][][] dp;
    static boolean[][] isP;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        pCnt = Integer.parseInt(br.readLine());
        for(int i=0; i<pCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            isP[r][c] = true;
        }

        int maxScore = getMaxScore();
        System.out.print(maxScore);
    }//main


    private static int getMaxScore() {

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                dp[r][c][0] = Math.max(dp[r-1][c][0], dp[r][c-1][0]) + map[r][c];

                int[] fromTop = dp[r-1][c];
                int[] fromLeft = dp[r][c-1];

                int max = Math.max(fromTop[1], fromLeft[1]);
                if(max > 0) dp[r][c][1] = max + map[r][c];

                max = Math.max(fromTop[2], fromLeft[2]);
                if(max > 0) dp[r][c][2] = max + map[r][c];

                max = Math.max(fromTop[3], fromLeft[3]);
                if(max > 0) dp[r][c][3] = max + map[r][c];

                // 중간 원소인 경우
                if(isP[r][c]) {
                    max = Math.max(fromTop[2], fromLeft[2]);
                    if(max > 0) dp[r][c][3] = Math.max(dp[r][c][3], max + map[r][c]);

                    max = Math.max(fromTop[1], fromLeft[1]);
                    if(max > 0) dp[r][c][2] = Math.max(dp[r][c][2], max + map[r][c]);

                    max = Math.max(fromTop[0], fromLeft[0]);
                    if(max > 0) dp[r][c][1] = Math.max(dp[r][c][1], max + map[r][c]);
                }
            }
        }


        return dp[N][N][3] > 0 ? dp[N][N][3] : -1;
    }//getMaxScore


    private static void init() {
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1][4];
        isP = new boolean[N+1][N+1];
    }//init


}//class