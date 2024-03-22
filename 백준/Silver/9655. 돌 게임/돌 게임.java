import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/9655
public class Main {
    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 돌의 개수
        br.close();

        dp = new int[N+1];
        dp[1] = 1;
        if(N > 1) dp[2] = 2;
        for(int i=3; i<=N; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-3]) + 1;
        }//for

        if(dp[N]%2 != 0) System.out.print("SK");
        else System.out.print("CY");
    }//main

}//class