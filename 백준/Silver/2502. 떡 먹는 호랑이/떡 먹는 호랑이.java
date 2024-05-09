import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2502
public class Main {
    static int D, K;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken()); // 할머니가 넘어온 날
        K = Integer.parseInt(st.nextToken()); // 호랑이에게 준 떡의 개수
        br.close();

        dp = new int[D+1];
        getAB();

        System.out.print(dp[1] + "\n" + dp[2]);
    }//main

    private static void getAB() {
        dp[1] = dp[2] = 1;

        while(true) {
            for(int d=3; d<=D; d++) {
                dp[d] = dp[d-1] + dp[d-2];
            }

            if(dp[D] == K) break;
            else if(dp[D] < K) dp[2]++;
            else {
                dp[1]++;
                dp[2] = dp[1];
            }
        }

    }//getAB

}//class