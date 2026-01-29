import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2423
public class Main {
    private static final int MAX = 2_500_025;
    private static final int[][] sdr = {{1, -1}, {-1, 1}};
    private static final int[][] sdc = {{1, -1}, {1, -1}};
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M;
    private static int[][] map;


    public static void main(String[] args) throws IOException {
        init();
        String result = dijkstra();

        System.out.print(result);
    }//main


    private static String dijkstra() {
        PriorityQueue<Line> pq = new PriorityQueue<>();
        int[][] minCnt = new int[N][M];

        for(int i=0; i<N; i++) {
            Arrays.fill(minCnt[i], MAX);
        }

        int r = 0, c = 0;
        int dir = 0;
        int cnt = 0;
        if (map[r][c] == 1) {
            map[r][c] = 0;
            cnt = 1;
        }

        pq.offer(new Line(r, c, dir, cnt));
        minCnt[r][c] = cnt;

        Line cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur.r;
            c = cur.c;
            dir = cur.dir;
            cnt = cur.changeCnt;

            if (minCnt[r][c] < cnt) continue;
            if (r == N-1 && c == M-1 && dir == 0) {
                return String.valueOf(cnt);
            }

            // 같은 방향 전선 이동
            for(int i=0; i<2; i++) {
                int nr = r + sdr[dir][i];
                int nc = c + sdc[dir][i];
                if (rangeCheck(nr, nc)) continue;

                // 같은 방향이면 그대로 이동, 아니면 변경
                int nCnt = map[nr][nc] == dir ? cnt : cnt + 1;
                if (minCnt[nr][nc] > nCnt) {
                    minCnt[nr][nc] = nCnt;
                    pq.offer(new Line(nr, nc, dir, nCnt));
                }
            }

            // 반대 방향 전선
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (rangeCheck(nr, nc)) continue;

                // 반대 방향이면 그대로 이동, 아니면 변경
                int nDir = dir^1;
                int nCnt = map[nr][nc] == nDir ? cnt : cnt + 1;
                if (minCnt[nr][nc] > nCnt) {
                    minCnt[nr][nc] = nCnt;
                    pq.offer(new Line(nr, nc, nDir, nCnt));
                }
            }
        }

        return "NO SOLUTION";
    }//dijkstra

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if (line[j] == '/') {
                    map[i][j] = 1;
                }
            }
        }

        br.close();
    }//init

    private static class Line implements Comparable<Line> {
        int r;
        int c;
        int dir;
        int changeCnt;

        Line(int r, int c, int dir, int changeCnt) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.changeCnt = changeCnt;
        }

        @Override
        public int compareTo(Line o) {
            return this.changeCnt - o.changeCnt;
        }
    }//Line

}//class