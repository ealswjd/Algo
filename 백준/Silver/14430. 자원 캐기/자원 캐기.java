import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14430
public class Main {
    private static int N, M; // 탐사할 영역의 세로길이, 가로길이
    private static int[][] map; // // 탐사할 영역


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[N+1][M+1];

        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[1][1] = map[1][1];

        int fromTop, fromLeft;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                if(r == 1 && c == 1) continue;

                fromTop = dp[r-1][c];
                fromLeft = dp[r][c-1];

                dp[r][c] = map[r][c] + Math.max(fromLeft, fromTop);
            }
        }

        // 수집할 수 있는 최대 광석의 개수를 출력
        System.out.print(dp[N][M]);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 탐사할 영역의 세로길이
        M = Integer.parseInt(st.nextToken()); // 가로길이

        map = new int[N+1][M+1]; // 탐사할 영역

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class