import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15992
public class Main {
    private static final int MOD = 1_000_000_009;
    private static final int N = 1_000;
    private static long[][] dp;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int n, m;
        StringTokenizer st;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            ans.append(dp[n][m]).append('\n');
        }

        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new long[N+1][N+1]; // dp[n][m] : n을 m개의 수를 사용하여 만드는 방법의 수

        dp[1][1] = 1;
        dp[2][1] = dp[2][2] = 1;
        dp[3][1] = dp[3][3] = 1;
        dp[3][2] = 2;

        for(int n=4; n<=N; n++) {
            for(int m=2; m<=n; m++) {
                dp[n][m] = (dp[n-1][m-1] + dp[n-2][m-1] + dp[n-3][m-1]) % MOD;
            }
        }

    }//init
    

}//class