import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2098
public class Main {
    static final int INF = 16_000_001;
    static int N, M, start;
    static int[][] cost;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                // i 도시 -> j 도시로 가는 비용
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int minCost = getMin(start, 1);
        System.out.print(minCost);
    }//main

    
    private static int getMin(int cur, int visited) {
        if(dp[cur][visited] != -1) return dp[cur][visited];
        if(visited == M) {
            if(cost[cur][start] == 0) return INF;
            return cost[cur][start];
        }

        dp[cur][visited] = INF;
        int min = INF;

        for(int to=0; to<N; to++) {
            // 가는길이 없거나 방문했었거나
            if(cost[cur][to] == 0 || (visited & (1 << to)) != 0) continue;

            min = Math.min(min, cost[cur][to] + getMin(to, visited | (1 << to)));
        }

        return dp[cur][visited] = min;
    }//getMin

    
    private static void init() {
        start = 0;
        M = (1 << N) - 1;
        cost = new int[N][N];
        dp = new int[N][(1 << N) + 1];

        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }//init

}//class