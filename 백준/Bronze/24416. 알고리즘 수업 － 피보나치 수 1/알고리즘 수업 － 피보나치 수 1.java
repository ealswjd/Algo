import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/24416
public class Main {
    static int N, cnt1, cnt2;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N =  Integer.parseInt(br.readLine());
        dp = new int[N];
        br.close();

        cnt1 = 0;
        cnt2 = 0;

        fib(N);
        fibonacci();

        System.out.println(cnt1 + " " + cnt2);
    }//main

    static int fib(int n){
        if(n == 1 || n == 2){
            cnt1++;
            return 1;
        }
        else return (fib(n-1) + fib(n-2));
    }//fib

    static int fibonacci(){
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < N; i++){
            cnt2++;
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N-1];
    }//fibonacci


}//class