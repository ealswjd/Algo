import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/11058
public class Main {
    static int N; // 버튼을 누르는 횟수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 버튼을 누르는 횟수

        long max = getMax();
        System.out.print(max);
    }//main

    
    private static long getMax() {
        long[] dp = new long[N+1];

        for(int i=1; i<=N; i++) {
            // A 출력
            dp[i] = dp[i-1] + 1;

            // 복사 붙여넣기 이용
            for(int j=1; j<=i-3; j++) {
                dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
            }
        }

        // 버튼 N번 눌러서 만들 수 있는 A의 최댓값
        return dp[N];
    }//getMax

    
}//class