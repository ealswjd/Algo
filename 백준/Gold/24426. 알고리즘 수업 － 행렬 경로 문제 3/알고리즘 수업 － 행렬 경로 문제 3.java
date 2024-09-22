import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24426
public class Main {
    static int N; // 행렬 크기
    static int[][] map; // 행렬

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 행렬 크기
        map = new int[N+1][N+1];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 중간 원소 Y의 행 번호 r과 열 번호 c
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        getMaxScore(new int[] {r, c});
        
    }//main

    
    private static void getMaxScore(int[] Y) {
        int yr = Y[0];
        int yc = Y[1];
        int[][] dp = new int[N+1][N+1];        // 전체 경로 점수
        int[][] fromY = new int[N+1][N+1];     // 중간 원소 y 포함 경로 점수
        int[][] excludeY = new int[N+1][N+1];  // 중간 원소 y 제외 경로 점수
        excludeY[1][1] = map[1][1];

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]) + map[r][c];

                if(r == 1 && c == 1) continue;
                if(r == yr && c == yc) continue;

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


        // 중간 원소 y 거친 최고 점수, y 거치지 않은 최고 점수
        System.out.print(fromY[N][N] + " " + excludeY[N][N]);
    }//getMaxScore

    
}//class