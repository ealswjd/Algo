import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/1699
public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        br.close();

        int[] dp = new int[N+1];
        for(int i=1; i<=N; i++) {
            dp[i] = i;
            for(int j=1; j*j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }//for
        }//for

        System.out.print(dp[N]);
    }//main

}//class