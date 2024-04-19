import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22115
public class Main {
    static final int INF = 101;
    static int N, K;
    static int[] coffees;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coffees = new int[N+1];
        int cnt = 0;
        int sum = 0;
        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++){
            coffees[i] = Integer.parseInt(st.nextToken());
            sum += coffees[i];

            if(coffees[i] == K) cnt = 1;
        }
        br.close();

        if(cnt == 1 || K == 0) System.out.print(cnt);
        else if(sum < K) System.out.print(-1);
        else {
            int minCnt = getMinCnt();
            System.out.print(minCnt);
        }
    }//main

    private static int getMinCnt() {
        int[] dp = new int[K+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i=1; i<=N; i++) {
            for(int k=K; k>=coffees[i]; k--) {
                dp[k] = Math.min(dp[k], dp[k - coffees[i]] + 1);
            }
        }

        if(dp[K] == INF) return -1;
        return dp[K];
    }//getMinCnt

}//class