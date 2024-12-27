import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13302
public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static boolean[] unavailable;

    
    public static void main(String[] args) throws IOException {
        init();

        int minCost = getMinCost();
        System.out.print(minCost);
    }//main


    private static int getMinCost() {
        int D = 105;
        int[][] dp = new int[D+1][42];
        
        for(int i=0; i<=D; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<40; j++) {
                if(dp[i][j] == INF) continue;

                // 리조트에 갈 수 없는 날
                if(unavailable[i+1]) {
                    dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]);
                }

                // 쿠폰 사용
                if(j >= 3) {
                    dp[i+1][j-3] = Math.min(dp[i+1][j-3], dp[i][j]);
                }

                // 1일권
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j] + 10000);

                // 3일권 (쿠폰 1장)
                for(int k=1; k<=3; k++) {
                    if(i + k <= N) {
                        dp[i+k][j+1] = Math.min(dp[i+k][j+1], dp[i][j] + 25000);
                    }
                }

                // 5일권 (쿠폰 2장)
                for(int k=1; k<=5; k++) {
                    if(i + k <= N) {
                        dp[i+k][j+2] = Math.min(dp[i+k][j+2], dp[i][j] + 37000);
                    }
                }
            }
        }

        int result = INF;
        for(int i=0; i<40; i++) {
            result = Math.min(result, dp[N][i]);
        }

        return result;
    }//getMinCost

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        unavailable = new boolean[N+1];

        if(M > 0) {
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++) {
                int day = Integer.parseInt(st.nextToken());
                unavailable[day] = true;
            }
        }

        br.close();
    }//init

    
}//class