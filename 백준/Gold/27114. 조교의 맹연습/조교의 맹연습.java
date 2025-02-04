import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27114
public class Main {
    private static final int[] dir = {3, 1, 2};
    private static int[] cost; // 좌로 돌아, 우로 돌아, 뒤로 돌아에 들어가는 에너지
    private static int K;      // 사용하고자 하는 총 에너지양


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        int[][] dp = new int[K+1][4]; // 제식 수행 횟수의 최솟값
        int INF = K+1;
        int p, nextD;

        for(int i=0; i<=K; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;

        for(int k=0; k<=K; k++) {
            for(int d=0; d<4; d++) {
                if(dp[k][d] == INF) continue;

                for(int i=0; i<3; i++) {
                    // 에너지 초과
                    if(k + cost[i] > K) continue;

                    p = cost[i]; // 회전 에너지
                    nextD = (d + dir[i]) % 4; // 다음 방향

                    dp[k + p][nextD] = Math.min(dp[k + p][nextD], dp[k][d] + 1);
                }
            }

        }

        return dp[K][0] != INF ? dp[K][0] : -1;
    }//getCnt


    private static void init() throws IOException {
        cost = new int[3];
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());      
        cost[0] = Integer.parseInt(st.nextToken()); // 좌로 돌아
        cost[1] = Integer.parseInt(st.nextToken()); // 우로 돌아
        cost[2] = Integer.parseInt(st.nextToken()); // 뒤로 돌아
        K = Integer.parseInt(st.nextToken());       // 사용하고자 하는 총 에너지양

        br.close();
    }//init


}//class