import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2157
public class Main {
    static final int INF = 987654321;
    static int N, M;
    static int[][] score, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시 개수
        M = Integer.parseInt(st.nextToken()); // 여행할 도시 개수 제한
        int K = Integer.parseInt(st.nextToken()); // 개설된 항공로의 개수

        init();

        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a > b) continue;

            score[a][b] = Math.max(score[a][b], c);
        }

        int max = getMax(1, 1);
        System.out.print(max);
    }//main

    
    private static int getMax(int cur, int cnt) {
        if(cur == N) return 0;
        if(cnt == M) return -INF;
        if(dp[cnt][cur] != -1) return dp[cnt][cur];

        dp[cnt][cur] = -INF;
        for(int to=cur+1; to<=N; to++) {
            if(score[cur][to] == 0) continue;
            dp[cnt][cur] = Math.max(dp[cnt][cur], getMax(to, cnt+1) + score[cur][to]);
        }

        return dp[cnt][cur];
    }//getMax

    
    private static void init() {
        score = new int[N+1][N+1];
        dp = new int[N+1][N+1];

        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }//init

}//class