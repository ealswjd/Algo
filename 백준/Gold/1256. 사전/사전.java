import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1256
public class Main {
    static final int MAX = 101, INF = 1_000_000_001;
    static int N, M, K;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // a 개수
        M = Integer.parseInt(st.nextToken()); // z 개수
        K = Integer.parseInt(st.nextToken()); // k번째 문자열

        setDP();
        printResult();
    }//main

    private static void printResult() {
        if(dp[N][M] < K) {
            System.out.print(-1);
            return;
        }

        int a = N;
        int z = M;
        int k;
        StringBuilder ans = new StringBuilder();

        for(int i=0; i<N+M; i++) {
            if(a == 0) {
                ans.append('z');
                z--;
                continue;
            }else if(z == 0) {
                ans.append('a');
                a--;
                continue;
            }

            k = dp[a-1][z];

            if(k >= K) {
                ans.append('a');
                a--;
            }else {
                ans.append('z');
                z--;
                K -= k;
            }
        }

        System.out.print(ans);
    }//printResult

    private static void setDP() {
        dp = new int[MAX][MAX];

        for(int i = 1; i< MAX; i++) {
            dp[0][i] = dp[i][0] = 1;
        }

        for(int i = 1; i< MAX; i++) {
            for(int j = 1; j< MAX; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
                if(dp[i][j] > INF) dp[i][j] = INF;
            }
        }
    }//setDP

}//class