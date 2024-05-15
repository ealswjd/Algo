import java.io.BufferedReader;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/12852
public class Main {
    static int N;
    static int[] dp, numbers;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        numbers = new int[N+1];
        br.close();

        int cnt = getMinCnt();
        getResult(cnt);
    }//main

    
    private static void getResult(int cnt) {
        StringBuilder ans = new StringBuilder();
        ans.append(cnt).append('\n');

        while(N > 0) {
            ans.append(N).append(' ');
            N = numbers[N];
        }

        System.out.print(ans);
    }//getResult

    
    private static int getMinCnt() {
        for(int i=2; i<=N; i++) {
            dp[i] = dp[i-1] + 1;
            numbers[i] = i-1;

            if(i % 2 == 0 && dp[i/2] + 1 < dp[i]) {
                dp[i] = dp[i/2] + 1;
                numbers[i] = i/2;
            }

            if(i % 3 == 0 && dp[i/3] + 1 < dp[i]) {
                dp[i] = dp[i/3] + 1;
                numbers[i] = i/3;
            }
        }

        return dp[N];
    }//getMinCnt

}//class