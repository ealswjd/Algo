import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14923
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static final int WALL = 1;
    private static int N, M;
    private static int hr, hc; // 홍익이 위치
    private static int er, ec; // 탈출 위치
    private static int[][] map;


    public static void main(String[] args) throws IOException {
        init();

        int result = bfs();
        System.out.print(result);
    }//main


    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[2][N][M];

        q.offer(new int[] {hr, hc, 0, 0});
        visited[0][hr][hc] = true;

        int r, c, cnt, dist;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];
            dist = cur[3];

            if(r == er && c == ec) return dist;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[cnt][nr][nc]) continue;

                if(map[nr][nc] == WALL) {
                    if(cnt > 0) continue;
                    visited[WALL][nr][nc] = true;
                    q.offer(new int[] {nr, nc, WALL, dist+1});
                }
                else {
                    visited[cnt][nr][nc] = true;
                    q.offer(new int[] {nr, nc, cnt, dist+1});
                }
            }
        }

        return -1;
    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >=M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        // 홍익이 위치
        st = new StringTokenizer(br.readLine());
        hr = Integer.parseInt(st.nextToken()) - 1;
        hc = Integer.parseInt(st.nextToken()) - 1;

        // 탈출 위치
        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class