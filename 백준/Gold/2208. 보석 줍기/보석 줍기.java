import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2208
public class Main {
    private static int N, M;
    private static int[] value; // 보석 가치
    private static int[] prefixSum; // 보석 가치 누적합

    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int max = 0;
        int[] dp = new int[N+1];
        // M개 이상의 보석을 연속적으로 주워야 함
        dp[M] = prefixSum[M];

        for(int i=M+1; i<=N; i++) {
            dp[i] = Math.max(dp[i-1] + value[i], prefixSum[i] - prefixSum[i-M]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }//getMax

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 보석 개수
        M = Integer.parseInt(st.nextToken()); // 연속으로 주워야 하는 개수

        value = new int[N+1]; // 보석 가치
        prefixSum = new int[N+1]; // 보석 가치 누적합

        for(int i=1; i<=N; i++) {
            value[i] = Integer.parseInt(br.readLine());
            prefixSum[i] = prefixSum[i-1] + value[i];
        }

        br.close();
    }//init

    
}//class