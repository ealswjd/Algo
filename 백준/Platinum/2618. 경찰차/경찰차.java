import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2618
public class Main {
    private static final int X = 0, Y = 1;
    private static int N, W; // 도로의 개수, 사건의 개수
    private static int[][] points; // 사건의 위치
    private static int[][] dp; // dp[p1][p2] : 1번이 p1, 2번이 p2 사건을 해결 후 남은 최소 거리


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int p1 = 0, p2 = 0;

        int min = getMinDist(p1, p2);

        // 두 경찰차가 이동한 총 거리를 출력
        ans.append(min).append('\n');
        // i(1 ≤ i ≤ W)번째 사건이 맡겨진 경찰차 번호 1 또는 2를 출력
        for(int next = 1; next<=W; next++) {
            int cost1 = getDist(1, p1, next) + dp[next][p2];
            int cost2 = getDist(2, p2, next) + dp[p1][next];

            if (cost1 <= cost2) {
                ans.append("1\n");
                p1 = next;
            } else {
                ans.append("2\n");
                p2 = next;
            }
        }

        System.out.print(ans);
    }//sol


    private static int getMinDist(int p1, int p2) {
        int next = Math.max(p1, p2) + 1;

        if (next > W) return 0;
        if (dp[p1][p2] != -1) return dp[p1][p2];

        // 1번 경찰 출동
        int cost1 = getMinDist(next, p2) + getDist(1, p1, next);
        // 2번 경찰 출동
        int cost2 = getMinDist(p1, next) + getDist(2, p2, next);

        return dp[p1][p2] = Math.min(cost1, cost2);
    }//getMinDist
    

    private static int getDist(int type, int s, int e) {
        int[] start;
        int[] end = points[e];

        if (s == 0) {
            if (type == 1) start = new int[] {1, 1}; // 1번 경찰
            else start = new int[] {N, N}; // 2번 경찰
        } else {
            start = points[s];
        }

        return Math.abs(start[X] - end[X]) + Math.abs(start[Y] - end[Y]);
    }//getDist
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 도로의 개수
        W = Integer.parseInt(br.readLine()); // 사건의 개수

        points = new int[W + 1][2]; // 사건의 위치
        dp = new int[W + 1][W + 1]; // dp[p1][p2]: 1번이 p1, 2번이 p2 사건을 해결 후 남은 최소 거리

        StringTokenizer st;
        for(int i=1; i<=W; i++) {
            st = new StringTokenizer(br.readLine());
            points[i][X] = Integer.parseInt(st.nextToken());
            points[i][Y] = Integer.parseInt(st.nextToken());
        }

        br.close();

        for(int i=0; i<=W; i++) {
            Arrays.fill(dp[i], -1);
        }
    }//init
    

}//class