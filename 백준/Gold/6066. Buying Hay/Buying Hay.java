import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/6066
public class Main {
    static final int INF=987654321;
    static int N, H;
    static int[] P, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        P = new int[N+1];
        C = new int[N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            P[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int min = getMin();
        System.out.print(min);
    }//main

    private static int getMin() {
        int maxH = 55555;
        int[][] dp = new int[N+1][maxH];
        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int i=1; i<=N; i++) {
            for(int j=0; j<maxH; j++) {
                if(j-P[i] < 0) dp[i][j] = dp[i-1][j];
                else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j - P[i]] + C[i]);
                }
            }
        }

        int min = INF;
        for(int h=H; h<maxH; h++) {
            min = Math.min(min, dp[N][h]);
        }
        return min;
    }//getMin

}//class