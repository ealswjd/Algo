import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2159
public class Main {
    private static final long MAX = 2_000_000_000_000L;
    private static final int[] DX = {0, -1, 1, 0, 0};
    private static final int[] DY = {0, 0, 0, -1, 1};

    private static int N; // 고객 수
    private static Position[][] positions; // 위치

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }//main

    private static void solve() {
        // dp[i][j]: i번 고객의 [실제 위치, 상, 하, 좌, 우]로 갔을 때 최소거리
        long[][] dp = new long[N+1][5];

        for(int i=1; i<=N; i++) {
            Arrays.fill(dp[i], MAX);
        }

        for(int cur=1; cur<=N; cur++) { // 현재 고객
            int prev = cur-1; // 이전 고객

            for(int i=0; i<5; i++) {
                if (dp[prev][i] == MAX) continue;
                for(int j=0; j<5; j++) {
                    long dist = getDist(positions[prev][i], positions[cur][j]);
                    dp[cur][j] = Math.min(dp[cur][j], dp[prev][i] + dist);
                }
            }
        }

        long min = MAX; // 최단 거리
        for(int i=0; i<5; i++) {
            min = Math.min(min, dp[N][i]);
        }

        // 최단 거리를 출력
        System.out.print(min);
    }//solve

    private static long getDist(Position prev, Position cur) {
        return Math.abs(prev.x - cur.x) + Math.abs(prev.y - cur.y);
    }//getDist

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 고객 수

        positions = new Position[N+1][5]; // 위치

        // 선아가 일하는 레스토랑의 위치
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        for(int d=0; d<5; d++) {
            positions[0][d] = new Position(x, y);
        }

        // N명의 위치
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());

            for(int d=0; d<5; d++) {
                positions[i][d] = new Position(x + DX[d], y + DY[d]);
            }
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
/*

3
2 2
3 6
6 7
7 3
------
11

4
3 2
4 4
5 2
4 3
4 4
------
4

5
50 50
10 10
60 60
10 60
60 10
50 40
-------
361

 */