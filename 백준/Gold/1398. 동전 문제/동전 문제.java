import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1398
public class Main {
    static final int N = 100;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        init();

        StringBuilder ans = new StringBuilder();
        long cost, cnt;

        while(T-- > 0) {
            cost = Long.parseLong(br.readLine());
            cnt = 0;

            while(cost > 0) {
                cnt += dp[(int)(cost % N)];
                cost /= N;
            }

            ans.append(cnt);
            ans.append('\n');
        }

        System.out.print(ans);
    }//main

    
    private static void init() {
        dp = new long[N+1];

        for(int i=1; i<=N; i++) {
            dp[i] = dp[i-1] + 1;
            if(i - 10 >= 0) dp[i] = Math.min(dp[i], dp[i-10] + 1);
            if(i - 25 >= 0) dp[i] = Math.min(dp[i], dp[i-25] + 1);
        }
    }//init

    
}//class