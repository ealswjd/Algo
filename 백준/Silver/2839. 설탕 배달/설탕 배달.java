import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2839
public class Main {
    static final int INF = 55555;
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        br.close();

        int minCnt = getMinCnt();
        System.out.print(minCnt);
    }//main

    private static int getMinCnt() {
        int[] dp = new int[N+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        dp[3] = 1;
        if(N >= 5) dp[5] = 1;

        for(int i=6; i<=N; i++) {
            if(i % 3 == 0) dp[i] = i / 3;
            if(i % 5 == 0) dp[i] = Math.min(dp[i], i / 5);

            for(int j=i/3; j>0; j--) {
                dp[i] = Math.min(dp[i], dp[3*j] + dp[i - (3*j)]);
            }

            for(int j=i/5; j>0; j--) {
                dp[i] = Math.min(dp[i], dp[5*j] + dp[i - (5*j)]);
            }
        }

        if(dp[N] == INF) return -1;
        return dp[N];
    }//getMinCnt

}//class