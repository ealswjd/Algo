import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14863
public class Main {
    private static final int TIME = 0, MONEY = 1;
    private static int N, K;
    private static int[][] walk; // 도보 이동 정보
    private static int[][] bike; // 자전거 이동 정보


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main


    private static int getMax() {
        int[][] dp = new int[N + 1][K + 1];

        dp[1][walk[1][TIME]] = walk[1][MONEY];
        dp[1][bike[1][TIME]] = Math.max(dp[1][bike[1][TIME]], bike[1][MONEY]);

        for(int n=1; n<=N; n++) {
            for(int k=1; k<=K; k++) {
                if(dp[n - 1][k] == 0) continue;

                // 도보
                if(walk[n][TIME] + k <= K) {
                    int time = walk[n][TIME] + k;
                    dp[n][time] = Math.max(dp[n][time], dp[n - 1][k] + walk[n][MONEY]);
                }
                // 자전거
                if(bike[n][TIME] + k <= K) {
                    int time = bike[n][TIME] + k;
                    dp[n][time] = Math.max(dp[n][time], dp[n - 1][k] + bike[n][MONEY]);
                }
            }
        }

        int max = 0;
        for(int k=0; k<=K; k++) {
            max = Math.max(max, dp[N][k]);
        }

        return max;
    }//getMax


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 서울을 제외한 도시의 개수
        K = Integer.parseInt(st.nextToken()); // 여행을 위해 보낼 수 있는 시간

        walk = new int[N+1][2]; // 도보 이동 정보
        bike = new int[N+1][2]; // 자전거 이동 정보

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            // 도보
            walk[i][TIME] = Integer.parseInt(st.nextToken()); // 걸리는 시간
            walk[i][MONEY] = Integer.parseInt(st.nextToken()); // 얻게 되는 모금액
            // 자전거
            bike[i][TIME] = Integer.parseInt(st.nextToken()); // 걸리는 시간
            bike[i][MONEY] = Integer.parseInt(st.nextToken()); // 얻게 되는 모금액
        }

        br.close();
    }//init


}//class