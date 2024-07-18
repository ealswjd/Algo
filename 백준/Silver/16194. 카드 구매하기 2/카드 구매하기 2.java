import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16194
public class Main {
    static int N;
    static int[] P;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            P[i] = Integer.parseInt(st.nextToken());
        }

        br.close();

        int min = getMin();
        System.out.print(min);
    }//main

    
    private static int getMin() {
        int[] dp = new int[N+1];

        for(int i=1; i<=N; i++) {
            dp[i] = P[i];
            for(int j=1; j<=i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j] + P[j]);
            }
        }

        return dp[N];
    }//getMin

    
}//class