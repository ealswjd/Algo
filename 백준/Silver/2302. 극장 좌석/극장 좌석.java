import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/2302
public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        init();

        int cnt = 1, prev = 0;

        while(M-->0) {
            int vip = Integer.parseInt(br.readLine());

            cnt *= dp[vip-prev-1];
            prev = vip;
        }

        cnt *= dp[N-prev];

        if(M == N) System.out.print(1);
        else System.out.print(cnt);
    }//main

    private static void init() {
        dp = new int[N+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }

    }//init

}//class