import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5557
public class Main {
    static final int PLUS=0, MINUS=1, MAX=20;
    static int N, target;
    static int[] numbers;
    static long[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        dp = new long[MAX+1][2][N];
        for(int i=0; i<=MAX; i++) {
            for(int j=0; j<N; j++) {
                dp[i][PLUS][j] = -1;
                dp[i][MINUS][j] = -1;
            }
        }

        target = numbers[N-1];
        long cnt = getCnt(0, PLUS, numbers[0]);
        System.out.print(cnt);
    }//main

    private static long getCnt(int idx, int prev, int sum) {
        if(sum < 0 || sum > MAX) return 0;
        if(idx == N-2) {
            if(sum != target) return 0;
            return 1;
        }
        if(dp[sum][prev][idx] != -1) return dp[sum][prev][idx];

        long cnt = 0;
        cnt += getCnt(idx+1, PLUS, sum+numbers[idx+1]);
        cnt += getCnt(idx+1, MINUS, sum-numbers[idx+1]);

        return dp[sum][prev][idx] = cnt;
    }//getCnt

}//class