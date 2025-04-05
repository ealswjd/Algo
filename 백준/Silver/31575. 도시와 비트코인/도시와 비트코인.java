import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/31575
public class Main {
    private static final int WALL = 0;
    private static int N, M;
    private static int[][] map;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        boolean[][] dp = new boolean[N+1][M+1];
        dp[1][1] = true;

        boolean fromLeft, fromTop;
        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                if(r == 1 && c == 1 || map[r][c] == WALL) continue;

                fromTop = dp[r-1][c];  // 위
                fromLeft = dp[r][c-1]; // 왼쪽

                dp[r][c] = fromTop || fromLeft;
            }
        }

        System.out.print(dp[N][M] ? "Yes" : "No");
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 도시의 가로 크기
        N = Integer.parseInt(st.nextToken()); // 도시의 세로 크기

        map = new int[N+1][M+1]; // 도시

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class