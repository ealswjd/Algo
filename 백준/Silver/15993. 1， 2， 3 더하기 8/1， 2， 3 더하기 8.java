import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/15993
public class Main {
    private static final int EVEN = 0; // 짝수
    private static final int ODD = 1;  // 홀수
    private static final int MOD = 1_000_000_009; // 방법의 수를 1,000,000,009로 나눈 나머지 출력
    private static final int N = 100_000;
    private static long[][] dp;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        int n;
        while(T-- > 0) {
            n = Integer.parseInt(br.readLine()); // n을 1, 2, 3의 합으로 나타내는 방법

            // 사용한 수의 개수가 홀수인 방법의 수, 짝수인 방법의 수를 공백으로 구분해 출력
            ans.append(dp[n][ODD]).append(' ');
            ans.append(dp[n][EVEN]).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new long[N+1][2]; // 홀짝 개수

        dp[1][ODD] = 1;
        dp[2][ODD] = dp[2][EVEN] = 1;
        dp[3][ODD] = dp[3][EVEN] = 2;

        // n으로 만들 수 있는 방법
        for(int n=4; n<=N; n++) {
            dp[n][ODD] = (dp[n-1][EVEN] + dp[n-2][EVEN] + dp[n-3][EVEN]) % MOD; // 홀
            dp[n][EVEN] = (dp[n-1][ODD] + dp[n-2][ODD] + dp[n-3][ODD]) % MOD; // 짝
        }

    }//init


}//class