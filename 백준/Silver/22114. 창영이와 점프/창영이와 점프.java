import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22114
public class Main {
    private static int N, K; // 빨간 보도블럭 개수, 창영이의 보폭
    private static int[] L; // 각 빨간 보도블럭 사이의 거리


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[2][N]; // 연속해서 밟을 수 있는 빨간 보도블럭의 최대 개수
        int max = 0;
        
        if(L[0] <= K) dp[1][0] = 1;
        else dp[0][0] = 1;

        for(int i=1; i<N-1; i++) {
            if(L[i] <= K) {
                dp[1][i] = dp[1][i-1] + 1;
                if(dp[0][i-1] != 0) dp[0][i] = dp[0][i-1] + 1;
            }
            else {
                dp[0][i] = dp[1][i-1] + 1;
                max = Math.max(max, dp[0][i-1]);
            }
        }

        max = Math.max(max, Math.max(dp[1][N-2], dp[0][N-2]));
        System.out.print(max + 1);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫째 줄에 빨간 보도블럭의 개수 N, 창영이의 보폭 K가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        L = new int[N-1]; // 각 빨간 보도블럭 사이의 거리

        // 둘째 줄에 각 빨간 보도블럭 사이의 거리 Li가 N-1개 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class