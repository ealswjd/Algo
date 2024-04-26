import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15817
public class Main {
    static final int L=0, C=1;
    static int N, X;
    static int[][] pipes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        pipes = new int[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i][L] = Integer.parseInt(st.nextToken()); // 파이프의 길이
            pipes[i][C] = Integer.parseInt(st.nextToken()); // 파이프의 수량
        }
        br.close();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int[] dp = new int[X+1];
        dp[0] = 1;

        for(int[] pipe : pipes) {
            for(int i=X; i>=pipe[L]; i--) {
                for(int j=pipe[C]; j>0; j--) {
                    if(i-pipe[L] * j >= 0) dp[i] += dp[i-pipe[L] * j];
                }
            }
        }

        return dp[X];
    }//getCnt

}//class