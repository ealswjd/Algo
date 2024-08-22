import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2629
public class Main {
    static final int MAX_CNT = 30, MAX_WEIGHT = 500;
    static int N, M;
    static int[] weights;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        init();

        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 추 개수

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
        }

        check(0, 0);

        M = Integer.parseInt(br.readLine()); // 구슬 개수

        int max = MAX_CNT * MAX_WEIGHT;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int w = Integer.parseInt(st.nextToken());

            if(w > max) ans.append("N ");
            else {
                if(dp[N][w]) ans.append("Y ");
                else ans.append("N ");
            }
        }

        System.out.print(ans);
    }//main

    
    private static void check(int idx, int weight) {
        if(idx > N || dp[idx][weight]) return;

        dp[idx][weight] = true; // 알 수 있음

        check(idx+1, weight); // 추 사용 x
        check(idx+1, weights[idx] + weight); // 구슬 반대편에 추
        check(idx+1, Math.abs(weights[idx] - weight)); // 구슬이랑 같이
    }//check

    
    private static void init() {
        weights = new int[MAX_CNT + 1];
        dp = new boolean[MAX_CNT + 1][MAX_WEIGHT * MAX_CNT + 1];
    }//init

    
}//class