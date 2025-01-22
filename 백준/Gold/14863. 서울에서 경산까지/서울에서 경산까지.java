import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14863
public class Main {
    private static final int TIME = 0, MONEY = 1;
    private static int N, K;
    private static int[][] walk; // 도보 이동 정보
    private static int[][] bike; // 자전거 이동 정보
    private static int[][] dp; // 최대 모금액


    public static void main(String[] args) throws IOException {
        init();

        int max = move(1, 0);
        System.out.print(max);
    }//main


    private static int move(int n, int k) {
        if(n > N) return 0;
        if(dp[n][k] != -1) return dp[n][k];

        int walkMoney = -1; // 도보 모금액
        int bikeMoney = -1; // 자전거 모금액

        // 도보 이동
        if(walk[n][TIME] + k <= K) {
            walkMoney = move(n + 1, walk[n][TIME] + k);
            if(walkMoney != -1) walkMoney += walk[n][MONEY];
        }
        // 자전거 이동
        if(bike[n][TIME] + k <= K) {
            bikeMoney = move(n + 1, bike[n][TIME] + k);
            if(bikeMoney != -1) bikeMoney += bike[n][MONEY];
        }

        return dp[n][k] = Math.max(walkMoney, bikeMoney);
    }//move


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 서울을 제외한 도시의 개수
        K = Integer.parseInt(st.nextToken()); // 여행을 위해 보낼 수 있는 시간

        walk = new int[N+1][2]; // 도보 이동 정보
        bike = new int[N+1][2]; // 자전거 이동 정보
        dp = new int[N+1][K+1]; // 최대 모금액

        for(int i=1; i<=N; i++) {
            Arrays.fill(dp[i], -1);
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