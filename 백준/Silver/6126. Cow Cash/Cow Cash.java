import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6126
public class Main {
    static int N, V;
    static int[] coins;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        coins = new int[V];

        for(int i=0; i<V; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        long cnt = getCnt();
        System.out.print(cnt);
    }//main

    private static long getCnt() {
        long[] dp = new long[N+1];
        dp[0] = 1;

        for(int coin : coins) {
            for(int i=coin; i<=N; i++) {
                dp[i] += dp[i-coin];
            }
        }

        return dp[N];
    }//getCnt

}//class