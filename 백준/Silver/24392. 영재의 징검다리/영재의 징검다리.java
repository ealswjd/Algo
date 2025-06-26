import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// Main_https://www.acmicpc.net/problem/24392
public class Main {
    private static final int MOD = 1_000_000_007;
    private static int N, M; // 다리의 크기
    private static int[][] map; // 다리의 정보


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[N][M]; // 다리를 건널 수 있는 경우의 수

        for(int c=0; c<M; c++) {
            // 강화유리
            if(map[0][c] == 1) dp[0][c] = 1;
        }

        for(int r=1; r<N; r++) {
            for(int c=0; c<M; c++) {
                // 일반 유리
                if(map[r][c] == 0) continue;

                // 위
                dp[r][c] = dp[r-1][c];

                // 좌측 상단
                if(rangeCheck(c-1)) {
                    dp[r][c] = (dp[r][c] + dp[r-1][c-1]) % MOD;
                }
                // 우측 상단
                if(rangeCheck(c+1)) {
                    dp[r][c] = (dp[r][c] + dp[r-1][c+1]) % MOD;
                }
            }
        }

        int cnt = 0; // 영재가 무사히 다리를 건널 수 있는 경우의 수
        for(int c=0; c<M; c++) {
            cnt = (cnt + dp[N-1][c]) % MOD;
        }

        System.out.print(cnt);
    }//sol


    private static boolean rangeCheck(int c) {
        return c >= 0 && c < M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 다리

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class