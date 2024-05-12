import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2600
public class Main {
    static final int K=500, LOSE=0, WIN=1;
    static int[] cnts; // 한 통에서 꺼낼 수 있는 구슬의 개수
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        init();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<3; i++) {
            cnts[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder ans = new StringBuilder();
        int M = 5;
        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            // 두 구슬 통 속에 들어있는 구슬의 수인 k1, k2
            int k1 = Integer.parseInt(st.nextToken());
            int k2 = Integer.parseInt(st.nextToken());

            if(getResult(k1, k2) == WIN) ans.append("A\n");
            else ans.append("B\n");
        }

        System.out.print(ans);
    }//main

    
    private static int getResult(int k1, int k2) {
        if(dp[k1][k2] != -1) return dp[k1][k2];

        for(int i=0; i<3; i++) {
            if(k1 >= cnts[i] && getResult(k1-cnts[i], k2) == LOSE) {
                return dp[k1][k2] = dp[k2][k1] = 1;
            }
        }

        for(int i=0; i<3; i++) {
            if(k2 >= cnts[i] && getResult(k1, k2-cnts[i]) == LOSE) {
                return dp[k1][k2] = dp[k2][k1] = 1;
            }
        }

        return dp[k1][k2] = dp[k2][k1] = LOSE;
    }//getResult

    
    private static void init() {
        cnts = new int[3]; // 한 통에서 꺼낼 수 있는 구슬의 개수는 세 가지
        dp = new int[K+1][K+1]; // 1 ≤ k1, k2 ≤500
        for(int i=0; i<=K; i++) {
            Arrays.fill(dp[i], -1);
        }
    }//init

}//class