import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22255
public class Main {
    private static final int K = 3, WALL = -1, MAX = 987654321;
    private static final int[][] dr = {{-1, 1, 0, 0}, {-1, 1}, {0, 0}};
    private static final int[][] dc = {{0, 0, -1, 1}, {0, 0}, {-1, 1}};
    private static int N, M; // 격자의 크기
    private static int sr, sc, er, ec; // 시작 지점, 도착 지점
    private static int[][] map; // 지도


    public static void main(String[] args) throws IOException {
        init();
        int ans = getMin();

        System.out.print(ans);
    }//main


    private static int getMin() {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        int[][][] minCost = new int[3][N][M]; // 최소 충격량

        for(int i=0; i<3; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(minCost[i][j], MAX);
            }
        }

        int r = sr, c = sc, cnt = 0, cost = 0;
        pq.offer(new Position(r, c, cnt, cost));
        minCost[cnt][r][c] = cost;

        Position cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur.r;
            c = cur.c;
            cnt = cur.cnt;
            cost = cur.cost;

            if (minCost[cnt][r][c] < cost) {
                continue;
            }
            if (r == er && c == ec) { // 탈출구
                return cost;
            }

            int k = (cnt + 1) % K; // 이동할 수 있는 방향
            for(int i=0, len=dr[k].length; i<len; i++) {
                int nr = r + dr[k][i];
                int nc = c + dc[k][i];
                if (rangeCheck(nr, nc)) continue;

                int nCost = cost + map[nr][nc]; // 이동 시 충격량
                if (minCost[k][nr][nc] > nCost) {
                    pq.offer(new Position(nr, nc, k, nCost));
                    minCost[k][nr][nc] = nCost;
                }
            }
        }

        return -1;
    }//getMin

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 격자의 크기 N, M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 지도

        // 시작 지점과 도착 지점의 정보인 sr, sc, er, ec가 주어진다.
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        // N개 줄에 걸쳐서 지도의 정보가 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


    private static class Position implements Comparable<Position> {
        int r;
        int c;
        int cnt;
        int cost;
        Position(int r, int c, int cnt, int cost) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.cost = cost;
        }

        @Override
        public int compareTo(Position p) {
            return this.cost - p.cost;
        }
    }//Position


}//class