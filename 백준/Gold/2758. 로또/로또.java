import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2758
public class Main {
    private static final int MAX_N = 11, MAX_M = 2001; // (1 ≤ n ≤ 10), (1 ≤ m ≤ 2,000)
    private static long[][] dp; // 1~m 까지의 숫자 중에서 n개를 고르는 방법의 수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        StringTokenizer st;
        int N, M;

        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // n개의 수를 고르는
            M = Integer.parseInt(st.nextToken()); // 1부터 m까지 숫자 중

            ans.append(dp[N][M]).append('\n');
        }

        br.close();

        System.out.print(ans);
    }//sol


    private static void init() {
        dp = new long[MAX_N][MAX_M]; // 1~m 까지의 숫자 중에서 n개를 고르는 방법의 수

        Arrays.fill(dp[0], 1);

        for(int cnt=1; cnt<MAX_N; cnt++) {
            for(int num=1; num<MAX_M; num++) {
                // dp[cnt][num] = num을 고른 경우 + num을 고르지 않은 경우
                dp[cnt][num] = dp[cnt-1][num/2] + dp[cnt][num-1];
            }
        }
    }//init


}//class