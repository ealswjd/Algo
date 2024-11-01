import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/24426
 알고리즘 수업 - 행렬 경로 문제 3

 중간 원소 Y를 거쳐서 가장 높은 점수
 중간 원소 Y를 거치지 않고 가장 높은 점수
*/
public class Main {
    private static int N;
    private static int yr, yc;
    private static int[][] map;

    
    public static void main(String[] args) throws IOException {
        init();
        getScore();
    }//main

    
    private static void getScore() {
        int[][] dp = new int[N+1][N+1];
        int[][] fromY = new int[N+1][N+1];
        int[][] excludeY = new int[N+1][N+1];
        excludeY[1][1] = map[1][1];

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]) + map[r][c];

                if((r == 1 && c == 1) || (r == yr && c == yc)) continue;

                int fromTop = excludeY[r-1][c] > 0 ? excludeY[r-1][c] + map[r][c] : 0;
                int fromLeft = excludeY[r][c-1] > 0 ? excludeY[r][c-1] + map[r][c] : 0;

                excludeY[r][c] = Math.max(fromTop, fromLeft);
            }
        }

        fromY[yr][yc] = dp[yr][yc];
        for(int r=yr; r<=N; r++) {
            for(int c=yc; c<=N; c++) {
                if(r == yr && c == yc) continue;

                fromY[r][c] = Math.max(fromY[r-1][c], fromY[r][c-1]) + map[r][c];
            }
        }


        // 1. Y를 거쳐서 가장 높은 점수   2. Y를 거치지 않고 가장 높은 점수
        System.out.printf("%d %d", fromY[N][N], excludeY[N][N]);
    }//getScore

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 중간 원소 Y의 행 번호 r과 열 번호 c가 주어진다.
        st = new StringTokenizer(br.readLine());
        yr = Integer.parseInt(st.nextToken());
        yc = Integer.parseInt(st.nextToken());

        br.close();
    }//init

    
}//class