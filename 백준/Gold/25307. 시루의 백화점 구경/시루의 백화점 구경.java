import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/25307
public class Main {
    private static final int WALL = 1, TARGET = 2, MANNEQUIN = 3;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 백화점의 세로, 가로
    private static int sr, sc; // 시루의 시작 위치
    private static int[][] map; // 백화점의 상태
    private static boolean[][] visited; // 방문 체크


    public static void main(String[] args) throws IOException {
        init();

        int min = bfs();
        System.out.print(min);
    }//main


    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int r, c, power;

        q.offer(new int[] {sr, sc, 0});
        visited[sr][sc] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            power = cur[2]; // 체력

            // 의자 도착
            if(map[r][c] == TARGET) {
                return power;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위, 방문 여부, 벽 체크
                if(isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == WALL) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, power + 1});
            }
        }

        return -1;
    }//bfs


    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//isOut


    private static void distCheck(Queue<int[]> q) {
        int r, c, d;
        int[] cur;

        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            d = cur[2]; // 거리

            if(d == 0) continue;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위, 방문 여부 체크
                if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, d - 1});
            }
        }

    }//distCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 백화점의 세로 길이
        M = Integer.parseInt(st.nextToken()); // 백화점의 가로 길이
        int K = Integer.parseInt(st.nextToken()); // 마네킹과 떨어져야 하는 거리

        map = new int[N][M]; // 백화점의 상태
        visited = new boolean[N][M]; // 방문 체크
        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // 마네킹
                if(map[i][j] == MANNEQUIN) {
                    visited[i][j] = true;
                    q.offer(new int[] {i, j, K});
                }
                // 시루의 시작 위치
                else if(map[i][j] == 4) {
                    sr = i;
                    sc = j;
                }
            }
        }

        distCheck(q); // 마네킹 거리 체크
        br.close();
    }//init


}//class