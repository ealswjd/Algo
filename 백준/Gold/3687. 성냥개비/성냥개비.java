import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/3687
public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static final int MAX_N = 101; // (2 ≤ n ≤ 100)
    private static final int[] min = {0, 0, 1, 7, 4, 2, 0, 8}; // i개로 만들 수 있는 작은 수
    private static long[] dp; // i개의 성냥으로 만들 수 있는 최솟값


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        int n;
        String max;

        while(T-- > 0) {
            n = Integer.parseInt(br.readLine()); // 성냥개비의 개수
            max = getMax(n);

            ans.append(dp[n]).append(' ');
            ans.append(max).append('\n');
        }

        System.out.print(ans);
        br.close();
    }//sol


    private static String getMax(int n) {
        StringBuilder max = new StringBuilder();
        int start = (n % 2 == 0) ? 2 : 3; // 1은 성냥 2개, 7은 성냥 3개
        long num = n - start;

        max.append(min[start]);
        // 나머지는 1로 채우기
        for(int i=0; i<num; i+=2) {
            max.append(1);
        }

        return String.valueOf(max);
    }//getMax


    private static void init() {
        dp = new long[MAX_N]; // n개의 성냥으로 만들 수 있는 최솟값
        Arrays.fill(dp, INF);

        for(int i=2; i<8; i++) {
            dp[i] = min[i];
        }
        dp[1] = 9; // 1개는 불가능하므로 가장 큰 9
        dp[6] = 6; // 숫자는 0으로 시작할 수 없으므로 0 다음으로 작은 6

        for(int i=8; i<MAX_N; i++) {
            for(int j=2; j<8; j++) {
                dp[i] = Math.min(dp[i], dp[i-j] * 10 + min[j]);
            }
        }

    }//init


}//class