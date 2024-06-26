import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12865
public class Main {
    static int N, K;
    static int[] W, V;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물품의 수
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        init();

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken()); // 무게
            V[i] = Integer.parseInt(st.nextToken()); // 가치
        }
        br.close();

        int max = getMax();
        System.out.println(max);
    }//main

    private static int getMax() {
        int[] dp = new int[K+1];

        for(int i=1; i<=N; i++) {
            for(int k=K; k>0; k--) {
                if(k < W[i]) break;
                dp[k] = Math.max(dp[k], dp[k-W[i]] + V[i]);
            }
        }

        return dp[K];
    }//getMax

    private static void init() {
        W = new int[N+1]; // 무게
        V = new int[N+1]; // 가치
    }//init

}//class