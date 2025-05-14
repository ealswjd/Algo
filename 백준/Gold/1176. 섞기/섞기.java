import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1176
public class Main {
    private static int N, K; // 학생의 수, 최소 넘어야할 키의 차이 값
    private static int M; // 비트마스크
    private static int[] height; // 학생들의 키


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        long[][] dp = new long[N][M];
        long total = 0;

        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }

        for(int i=0; i<N; i++) {
            total += getCnt(i, 1 << i, dp);
        }

        System.out.print(total);
    }//sol


    private static long getCnt(int cur, int status, long[][] dp) {
        if(status == M - 1) return 1;
        if(dp[cur][status] != -1) return dp[cur][status];

        long cnt = 0;

        for(int next=0; next<N; next++) {
            if((status & (1 << next)) != 0) continue;

            if(Math.abs(height[cur] - height[next]) > K) {
                cnt += getCnt(next, status | (1 << next), dp);
            }
        }

        return dp[cur][status] = cnt;
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생의 수
        K = Integer.parseInt(st.nextToken()); // 최소 넘어야할 키의 차이 값
        M = 1 << N;

        height = new int[N]; // 학생들의 키

        for(int i=0; i<N; i++) {
            height[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init


}//class