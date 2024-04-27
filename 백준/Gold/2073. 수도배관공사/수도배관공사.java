import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2073
public class Main {
    static final int L=0, C=1, INF=Integer.MAX_VALUE;
    static int D, P;
    static int[][] pipes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        D = Integer.parseInt(st.nextToken()); // 거리
        P = Integer.parseInt(st.nextToken()); // 파이프 개수
        pipes = new int[P][2];

        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            pipes[i][L] = Integer.parseInt(st.nextToken());
            pipes[i][C] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int[] dp = new int[D+1];
        dp[0] = INF;

        for(int[] pipe : pipes) {
            for(int i=D; i>=pipe[L]; i--) {
                dp[i] = Math.max(dp[i], Math.min(dp[i-pipe[L]], pipe[C]));
            }
        }

        return dp[D];
    }//getMax

}//class