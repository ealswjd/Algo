import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/2780
public class Main {
    private static final int N = 1000, MOD = 1234567, NUM = 10;
    private static int[][] dp;


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
            n = Integer.parseInt(br.readLine());
            ans.append(dp[n][NUM]).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new int[N+1][NUM+1];
        Arrays.fill(dp[1], 1);

        for(int len=2; len<=N; len++) {
            for(int num=0; num<NUM; num++) {
                int cnt = 0;

                switch (num) {
                    case 0 : cnt = dp[len-1][7];
                        break;
                    case 1 : cnt = dp[len-1][2] + dp[len-1][4];
                        break;
                    case 2 : cnt = dp[len-1][1] + dp[len-1][3] + dp[len-1][5];
                        break;
                    case 3 : cnt = dp[len-1][2] + dp[len-1][6];
                        break;
                    case 4 : cnt = dp[len-1][1] + dp[len-1][5] + dp[len-1][7];
                        break;
                    case 5 : cnt = dp[len-1][2] + dp[len-1][4] + dp[len-1][6] + dp[len-1][8];
                        break;
                    case 6 : cnt = dp[len-1][3] + dp[len-1][5] + dp[len-1][9];
                        break;
                    case 7 : cnt = dp[len-1][0] + dp[len-1][4] + dp[len-1][8];
                        break;
                    case 8 : cnt = dp[len-1][7] + dp[len-1][5] + dp[len-1][9];
                        break;
                    case 9 : cnt = dp[len-1][6] + dp[len-1][8];
                        break;
                }

                dp[len][num] = cnt % MOD;
            }
        }

        for(int len=1; len<=N; len++) {
            int total = 0;
            for(int num=0; num<NUM; num++) {
                total = (total + dp[len][num]) % MOD;
            }
            dp[len][NUM] = total;
        }
    }//init


}//class