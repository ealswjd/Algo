import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/21735
public class Main {
    private static int N, M; // 앞마당 길이, 대회 시간
    private static int max; // 가장 크게 만들 수 있는 눈덩이의 크기
    private static int[] h; // 눈 높이

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        max = 1; // 가장 크게 만들 수 있는 눈덩이의 크기

        //dfs(0, 0, 1);
        int[][] dp = new int[M+1][N+1];

        for(int i=0; i<=M; i++) {
            Arrays.fill(dp[i], -1);
        }

        dp[0][0] = 1; // 초기 눈덩이는 1

        for(int t=0; t<M; t++) {
            for(int i=0; i<=N; i++) {
                if (dp[t][i] == -1) continue;
                max = Math.max(max, dp[t][i]);

                if (i + 1 <= N) {
                    dp[t+1][i+1] = Math.max(dp[t+1][i+1], dp[t][i] + h[i+1]);
                }
                if (i + 2 <= N) {
                    dp[t+1][i+2] = Math.max(dp[t+1][i+2], dp[t][i]/2 + h[i+2]);
                }
            }
        }

        for(int i=0; i<=N; i++) {
            max = Math.max(max, dp[M][i]);
        }

        // 가장 크게 만들 수 있는 눈덩이의 크기를 출력한다.
        System.out.print(max);
    }//sol

    private static void dfs(int cur, int time, int size) {
        if (cur == N || time == M) {
            max = Math.max(max, size);
            return;
        }

        dfs(cur + 1, time + 1, size + h[cur + 1]);
        if (cur + 2 <= N) {
            dfs(cur + 2, time + 1, size/2 + h[cur+2]);
        }
    }//dfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        h = new int[N+1]; // 눈 높이

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            h[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init

}//class