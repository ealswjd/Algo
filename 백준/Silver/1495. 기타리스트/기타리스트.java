import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1495
public class Main {
    static final int X = -2;
    static int N, S, M;
    static int[] vol;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 곡의 개수
        S = Integer.parseInt(st.nextToken()); // 시작 볼륨
        M = Integer.parseInt(st.nextToken()); // 볼륨 최댓값

        dp = new int[M+1][N];
        vol = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            vol[i] = Integer.parseInt(st.nextToken());
        }//for

        for(int i=0; i<=M; i++) {
            Arrays.fill(dp[i], X);
        }//for
        sol(S, 0);

        System.out.print(dp[S][0]);
    }//main

    private static int sol(int sum, int idx) {
        if(sum > M || sum < 0) return -1;
        if(idx == N) return sum;
        if(dp[sum][idx] != X) return dp[sum][idx];

        return dp[sum][idx] = Math.max(dp[sum][idx],
                Math.max(sol(sum + vol[idx], idx+1), sol(sum - vol[idx], idx+1)));
    }//sol

}//class