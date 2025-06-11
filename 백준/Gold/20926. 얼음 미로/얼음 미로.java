import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20926
public class Main {
    private static final int R = -1, H = -2, E = 10, MAX = Integer.MAX_VALUE;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M, sr, sc; // 행, 열, 시작행, 시작열
    private static int[][] map; // 미로


    public static void main(String[] args) throws IOException {
        init();
        int time = sol();

        System.out.print(time);
    }//main


    private static int sol() {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        int[][] visited = new int[N][M];
        int r = sr, c = sc, t = 0;

        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], MAX);
        }

        pq.offer(new Info(r, c, t));
        visited[r][c] = t;

        Info cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur.r; // 행
            c = cur.c; // 열
            t = cur.time; // 미끌 시간

            if(map[r][c] == E) {
                return t;
            }

            int nr, nc, nt;
            for(int i=0; i<4; i++) {
                Info next = move(r, c, t, i); // 다음 이동칸 정보
                if(next == null) continue; // 이동 불가능

                nr = next.r;
                nc = next.c;
                nt = next.time;

                if(visited[nr][nc] > nt) {
                    visited[nr][nc] = nt;
                    pq.offer(next);
                }
            }
        }

        return -1;
    }//sol


    private static Info move(int r, int c, int time, int d) {
        int nr = r, nc = c, nt = time;

        while(true) {
            nr += dr[d];
            nc += dc[d];

            if(rangeCheck(nr, nc)) break;
            nt += map[nr][nc]; // 미끌 시간

            // 바위
            if(map[nr][nc] == R) return new Info(nr - dr[d], nc - dc[d], nt - R);
            // 출구
            if(map[nr][nc] == E ) return new Info(nr, nc, nt - E);
        }

        // 이동 불가능
        return null;
    }//move


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == H;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 열
        N = Integer.parseInt(st.nextToken()); // 행

        map = new int[N][M]; // 미로

        for(int i=0; i<N; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if('0' <= line[j] && line[j] <= '9') {
                    map[i][j] = line[j] - '0';
                }
                else if(line[j] == 'R') map[i][j] = R; // 바위
                else if(line[j] == 'H') map[i][j] = H; // 구멍
                else if(line[j] == 'E') map[i][j] = E; // 출구
                else { // 탐험가 테라 위치
                    sr = i;
                    sc = j;
                }
            }
        }

        br.close();
    }//init


    private static class Info implements Comparable<Info> {
        int r;
        int c;
        int time;

        Info(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        @Override
        public int compareTo(Info o) {
            return this.time - o.time;
        }
    }//Info


}//class