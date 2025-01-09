import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1311
public class Main {
    private static final int INF = 987654321;
    private static int N;
    private static int[][] cost;
    private static int[][] dp;


    public static void main(String[] args) throws IOException {
       init();

       int minCost = getMinCost(0, 0);
       System.out.println(minCost);
    }//main


    private static int getMinCost(int cur, int status) {
        if(cur == N) return 0;
        if(dp[cur][status] != 0) return dp[cur][status];

        int min = INF;
        for(int i=0; i<N; i++) {
            if((status & (1 << i)) != 0) continue;
            min = Math.min(min, getMinCost(cur + 1, status | (1 << i)) + cost[cur][i]);
        }

        return dp[cur][status] = min;
    }//getMinCost


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cost = new int[N][N];
        dp = new int[N][1 << N];
        StringTokenizer st;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class