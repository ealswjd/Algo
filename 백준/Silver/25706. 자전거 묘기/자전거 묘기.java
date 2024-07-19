import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25706
public class Main {
    static int N;
    static int[] heights;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        sol();
    }//main

    private static void sol() {
        int[] dp = new int[N];
        int h;

        for(int i=N-1; i>=0; i--) {
            dp[i] = 1;
            h = heights[i] + 1;

            if(i + h < N) dp[i] += dp[i + h];
        }

        StringBuilder ans = new StringBuilder();
        for(int cnt : dp) {
            ans.append(cnt).append(' ');
        }

        System.out.print(ans);
    }//sol

    private static void init() {
        heights = new int[N];
    }//init

}//class