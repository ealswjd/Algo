import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16509
public class Main {
    private static final int R = 10, C = 9;
    // 최종 목적지 : 상하좌우 한 칸 + 대각선 두 칸
    private static final int[] dr = {-3, -3, -2, -2, 2, 2, 3, 3};
    private static final int[] dc = {-2, 2, -3, 3, -3, 3, -2, 2};
    // 최종 목적지 가는 경로
    private static final int[][] ddr = {{-1, -2}, {-1, -2}, {0, -1}, {0, -1},
                                        {0, 1}, {0, 1}, {1, 2}, {1, 2}};
    private static final int[][] ddc = {{0, -1}, {0, 1}, {-1, -2}, {1, 2},
                                        {-1, -2}, {1, 2}, {0, -1}, {0, 1}};
    private static int sr, sc, er, ec;


    public static void main(String[] args) throws IOException {
        init();
        int result = bfs();

        System.out.print(result);
    }//main


    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        q.offer(new int[] {sr, sc, 0});
        visited[sr][sc] = true;

        int[] cur;
        int r, c, cnt;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            cnt = cur[2]; // 이동 횟수

            if(r == er && c == ec) return cnt;

            for(int i=0; i<8; i++) {
                // 가는 길에 왕 있으면 이동 불가능
                if(!available(r, c, i)) continue;
                
                // 이동하는 위치
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 벗어나거나 방문한적 있음
                if(!inRange(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, cnt + 1});
            }
        }

        return -1;
    }//bfs


    private static boolean available(int r, int c, int d) {
        // 가는 경로에 왕이 있으면 이동 불가능함
        for(int i=0; i<2; i++) {
            int nr = r + ddr[d][i];
            int nc = c + ddc[d][i];

            if(nr == er && nc == ec) return false;
        }

        return true;
    }//available


    private static boolean inRange(int r, int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }//inRange


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 줄에는 상의 위치를 의미하는 정수 R1, C1이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        // 두 번째 줄에는 왕의 위치를 의미하는 정수 R2, C2가 주어진다.
        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken());
        ec = Integer.parseInt(st.nextToken());

        br.close();
    }//init


}//class