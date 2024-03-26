import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/5502
public class Main {
    static final int INF=50000;
    static int N;
    static char[] str;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine().toCharArray();

        dp = new int[N][N];
        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], INF);
        }

        int cnt = dfs(0, N-1);
        System.out.print(cnt);
    }//main

    private static int dfs(int s, int e) {
        if(s>=e) return 0;
        if(dp[s][e] != INF) return dp[s][e];

        if(str[s] == str[e]) dp[s][e] = dfs(s+1, e-1);
        else dp[s][e] = Math.min(dfs(s, e-1), dfs(s+1, e)) + 1;

        return dp[s][e];
    }//dfs

}//class