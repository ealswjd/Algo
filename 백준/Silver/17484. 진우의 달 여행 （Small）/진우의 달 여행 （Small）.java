import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17484
public class Main {
    private static final int MAX = 100_001;
    private static final int[] dc = {-1, 0, 1};
    private static int N, M; // 행렬의 크기
    private static int[][] map; // 지구와 달 사이 공간을 나타내는 행렬


    public static void main(String[] args) throws IOException {
        init();

        int min = getMin();
        System.out.print(min);
    }//main


    private static int getMin() {
        int[][][] dp = new int[3][N+1][M+2];

        for(int i=0; i<3; i++) {
            for(int j=0; j<=N; j++) {
                Arrays.fill(dp[i][j], MAX);
            }
        }

        for(int i=1; i<=M; i++) {
            dp[0][1][i] = dp[1][1][i] = dp[2][1][i] = map[1][i];
        }

        int prev1, prev2, prevC;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                for(int d=0; d<3; d++) {
                    prev1 = (d + 1) % 3;
                    prev2 = (d + 2) % 3;

                    prevC = c + dc[prev1];
                    dp[d][r][c] = Math.min(dp[d][r][c], dp[prev1][r-1][prevC] + map[r][c]);

                    prevC = c + dc[prev2];
                    dp[d][r][c] = Math.min(dp[d][r][c], dp[prev2][r-1][prevC] + map[r][c]);
                }
            }
        }

        int min = MAX;
        for(int i=0; i<3; i++) {
            for(int c=1; c<=M; c++) {
                min = Math.min(min, dp[i][N][c]);
            }
        }

        return min;
    }//getMin


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행렬의 크기
        M = Integer.parseInt(st.nextToken()); // 행렬의 크기

        map = new int[N+1][M+1]; // 지구와 달 사이 공간을 나타내는 행렬

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class