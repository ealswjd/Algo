import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20167
public class Main {
    private static int N, K; // 먹이 개수, 최소 만족도
    private static int[] scores; // 만족도 점수

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        int[] dp = new int[N+1];

        for(int i=0; i<N; i++) {
            dp[i+1] = Math.max(dp[i+1], dp[i]);

            int sum = 0;
            for(int j=i+1; j<=N; j++) {
                sum += scores[j];
                if (sum >= K) {
                    dp[j] = Math.max(dp[j], dp[i] + (sum - K));
                    break;
                }
            }
        }

        System.out.print(dp[N]);
    }//solve

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 먹이 개수 N, 최소 만족도 K 가 공백으로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        scores = new int[N+1]; // 만족도 점수

        // 1 번부터 N 번 먹이의 만족도가 순서대로 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

}//class