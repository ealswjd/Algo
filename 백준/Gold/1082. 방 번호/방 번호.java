import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1082
public class Main {
    private static final int MAX_P = 51; // 1 ≤ Pi ≤ 50
    private static int N, M; // 문방구에서 파는 숫자는 0부터 N-1까지, 준비한 금액은 M원
    private static int[] max; // 각 비용으로 살 수 있는 가장 큰 숫자


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        BigInteger[] dp = new BigInteger[M+1];

        for(int i=1; i<=M; i++) {
            dp[i] = BigInteger.valueOf(max[i]);
        }

        for(int i=1; i<=M; i++) {
            for(int j=1; j<i; j++) {
                if(max[j] == -1) continue;

                BigInteger cur = dp[i - j].multiply(BigInteger.TEN)
                                            .add(BigInteger.valueOf(max[j]));
                dp[i] = dp[i].max(cur);
            }
        }

        BigInteger result = BigInteger.valueOf(0);
        for(int i=1; i<=M; i++) {
            result = result.max(dp[i]);
        }

        System.out.print(result);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 N이 주아진다.
        N = Integer.parseInt(br.readLine()); // 문방구에서 파는 숫자는 0부터 N-1까지

        int[] P = new int[N]; // 각 숫자 i의 가격
        max = new int[MAX_P];

        Arrays.fill(max, -1);

        // 둘째 줄에는 공백으로 구분된 P0, ..., PN-1이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            P[i] = Integer.parseInt(st.nextToken()); // 숫자 i의 가격
            max[P[i]] = Math.max(max[P[i]], i); // P[i]로 살 수 있는 높은 숫자
        }

        // 마지막 줄에는 M이 주어진다.
        M = Integer.parseInt(br.readLine()); // 준비한 금액

        br.close();
    }//init
    

}//class