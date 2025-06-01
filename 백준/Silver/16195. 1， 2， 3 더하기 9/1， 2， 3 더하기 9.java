import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16195
public class Main {
    private static final int MOD = 1_000_000_009; // 방법의 수를 1,000,000,009로 나눈 나머지
    private static final int N = 1_000;
    private static long[][] dp;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int n, m;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken()); // n을 1, 2, 3의 합으로 나타내는 방법
            m = Integer.parseInt(st.nextToken()); // 사용한 수의 개수는 m개 이하

            ans.append(dp[n][m]).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new long[N+1][N+1];

        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 2; dp[3][3] = 1;

        // m개로 만들 수 있는 방법 먼저 구하기
        for(int n=4; n<=N; n++) {
            for(int m=2; m<=N; m++) {
                dp[n][m] = (dp[n-1][m-1] + dp[n-2][m-1] + dp[n-3][m-1]) % MOD;
            }
        }

        // m개 이하로 찾을 수 있는 방법 누적
        for(int n=1; n<=N; n++) {
            for(int m=1; m<=N; m++) {
                dp[n][m] = (dp[n][m] + dp[n][m-1]) % MOD;
            }
        }

    }//init


}//class