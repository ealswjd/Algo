import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14628
public class Main {
    private static final int INF = 1_000_000_000;
    private static final int MAX = 101;
    private static int N, M; // 스킬의 개수, 적의 체력
    private static int K; // 같은 스킬을 사용할 때마다 추가되는 마나 포인트
    private static int[] X; // 스킬에 필요한 마나 포인트 X
    private static int[] Y; // 상대방의 체력을 깎는 수치인 Y


    public static void main(String[] args) throws IOException {
        init();

        int min = getMin();
        System.out.print(min);
    }//main


    private static int getMin() {
        int[][] dp = new int[MAX][MAX];
        int point;

        for(int i=0; i<MAX; i++) {
            Arrays.fill(dp[i], INF);
            dp[i][0] = 0;
        }

        for(int n=1; n<=N; n++) {
            for(int m=1; m<=M; m++) {
                for(int k=m/Y[n]; k>=0; k--) {
                    point = dp[n - 1][m - k * Y[n]] + (X[n] * k) + (K * ((k * (k - 1)) / 2));
                    dp[n][m] = Math.min(dp[n][m], point);
                }
            }
        }

        return dp[N][M];
    }//getMin


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 스킬의 개수 N, 적의 체력 M, 같은 스킬을 사용할 때마다 추가되는 마나 포인트 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 스킬에 필요한 마나 포인트 X와 상대방의 체력을 깎는 수치인 Y가 주어진다.
        X = new int[N + 1];
        Y = new int[N + 1];

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            Y[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class