import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10653
public class Main {
    private static final int MAX = 1_000_004;
    private static int N, K; // 체크포인트의 수 N과 건너뛸 체크포인트의 수 K
    private static Position[] positions; // 체크포인트 좌표


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] dp = new int[N+1][K+1];

        for(int i=1; i<=N; i++) {
            Arrays.fill(dp[i], MAX);
        }
        dp[1][0] = 0; // 1번 체크포인트

        int skipped, dist; // 건너뛴 포인트 수, 거리
        for(int cur=2; cur<=N; cur++) {
            // 건너뛰지 않음
            dp[cur][0] = dp[cur-1][0] + getDist(positions[cur], positions[cur-1]);

            // 체크포인트를 최대 K 개 건너뛸 경우
            for(int k=1; k<=K; k++) {
                for(int prev=cur-1; prev>=1; prev--) {
                    skipped = cur - prev - 1; // prev에서 cur까지 건너뛴 포인트 수

                    if(skipped <= k && dp[prev][k - skipped] != MAX) {
                        dist = getDist(positions[cur], positions[prev]); // 거리
                        dp[cur][k] = Math.min(dp[cur][k], dp[prev][k - skipped] + dist);
                    }
                }
            }
        }

        // 체크포인트 K 개를 건너뛰고 달릴 수 있는 최소 거리를 출력
        int min = MAX;
        for(int k=0; k<=K; k++) {
            min = Math.min(min, dp[N][k]);
        }

        System.out.print(min);
    }//sol


    private static int getDist(Position p1, Position p2) {
        int xd = Math.abs(p1.x - p2.x);
        int yd = Math.abs(p1.y - p2.y);

        return xd + yd;
    }//getDist


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 체크포인트의 수 N과 건너뛸 체크포인트의 수 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 체크포인트의 수
        K = Integer.parseInt(st.nextToken()); // 건너뛸 체크포인트의 수

        positions = new Position[N+1]; // 체크포인트 좌표

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            // 첫 번째 정수는 체크포인트 i의 x 좌표, 두 번째 정수는 y 좌표
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positions[i] = new Position(x, y);
        }

        br.close();
    }//init


    private static class Position {
        int x;
        int y;

        Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }//Position
    

}//class