import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27978
public class Main {
    private static final char EMPTY = '.';
    private static final int MAX = 987654321;
    private static final int[] dr = {-1, 0, 1, -1, 1, 0, -1, 1};
    private static final int[] dc = {1, 1, 1, 0, 0, -1, -1, -1};
    private static int H, W;
    private static int sr, sc, er, ec;
    private static char[][] map;


    public static void main(String[] args) throws IOException {
        init();
        int min = getMin();

        System.out.print(min);
    }//main


    private static int getMin() {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        int[][] minCost = new int[H][W];

        for(int i=0; i<H; i++) {
            Arrays.fill(minCost[i], MAX);
        }

        int r = sr, c = sc, cost = 0;
        pq.offer(new Position(r, c, cost));
        minCost[r][c] = cost;

        Position cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur.r;
            c = cur.c;
            cost = cur.cost;

            if (minCost[r][c] < cost) {
                continue;
            }
            if (r == er && c == ec) {
                return cost;
            }

            for(int i=0; i<8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (rangeCheck(nr, nc)) continue;

                int nCost = i < 3 ? cost : cost + 1;
                if (minCost[nr][nc] > nCost) {
                    minCost[nr][nc] = nCost;
                    pq.offer(new Position(nr, nc, nCost));
                }
            }
        }

        return -1;
    }//getMin

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W || map[r][c] != EMPTY;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지도의 크기가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[H][W]; // 지도

        // 지도가 주어진다.
        for(int i=0; i<H; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                if (map[i][j] == '*') { // 보물
                    map[i][j] = EMPTY;
                    er = i;
                    ec = j;
                } else if (map[i][j] == 'K') { // 배
                    map[i][j] = EMPTY;
                    sr = i;
                    sc = j;
                }
            }
        }

        br.close();
    }//init

    private static class Position implements Comparable<Position> {
        int r;
        int c;
        int cost;
        Position(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Position p) {
            return this.cost - p.cost;
        }
    }//Position

}//class