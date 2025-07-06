import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/23029
public class Main {
    private static final int MAX_N = 100_000;
    private static int N; // 시식코너의 개수
    private static int[] food; // 시식코너에서 제공하는 음식의 개수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[MAX_N+1][3];

        dp[1][1] = dp[1][2] = food[1];
        dp[2][0] = food[2];
        dp[2][1] = food[1] + food[2] / 2;
        dp[2][2] = food[1];

        for(int i=3; i<=N; i++) {
            dp[i][0] = dp[i-1][2] + food[i];
            dp[i][1] = dp[i-1][0] + food[i] / 2;
            dp[i][2] = Math.max(dp[i-1][0], Math.max(dp[i-1][1], dp[i-1][2]));
        }

        int max = Math.max(dp[N][0], Math.max(dp[N][1], dp[N][2]));
        System.out.print(max);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 시식코너의 개수

        food = new int[MAX_N+1]; // 시식코너에서 제공하는 음식의 개수

        for(int i=1; i<=N; i++) {
            food[i] = Integer.parseInt(br.readLine());
        }

        br.close();
    }//init


}//class