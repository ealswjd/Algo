import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24513
public class Main {
    private static final int R = 0, C = 1, TIME = 2, V = 3;
    private static int N, M; // 행, 열
    private static int[] count; // 바이러스 개수
    private static int[][] map; // 마을
    private static int[][][] visited; // 방문 체크
    private static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }//main

    private static void bfs() {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int[] virus;
        int r, c, time, v;

        while(!q.isEmpty()) {
            virus = q.poll();
            r = virus[R]; // 행
            c = virus[C]; // 열
            time = virus[TIME]; // 시간
            v = virus[V]; // 바이러스 번호

            if(map[r][c] == 3) continue; // 3번 바이러스

            map[r][c] = v;  // 바이러스 감염
            count[v]++;     // 현재 바이러스 개수 증가

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                // 범위 체크, 방문 체크
                if(rangeCheck(nr, nc) || visited[v][nr][nc] != 0) continue;

                // 1, 2번 바이러스 동시 감염
                if(visited[v^3][nr][nc] == time+1) {
                    // 3번 바이러스 탄생
                    count[3]++;
                    map[nr][nc] = 3;
                    visited[v][nr][nc] = time+1;
                }
                else if(visited[v^3][nr][nc] == 0){
                    visited[v][nr][nc] = time+1;
                    q.offer(new int[] {nr, nc, time+1, v});
                }
            }
        }

        // 바이러스 1, 바이러스 2, 바이러스 3
        System.out.print(count[1] + " " + count[2] + " " + count[3]);
    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == -1;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        count = new int[4]; // 바이러스 별 마을 개수
        map = new int[N][M]; // 마을
        visited = new int[3][N][M]; // 바이러스 방문 체크
        q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] > 0) {
                    visited[map[i][j]][i][j] = 1;
                    q.offer(new int[] {i, j, 1, map[i][j]}); // 행, 열, 시간, 바이러스 번호
                }

            }
        }

    }//init


    private static void print() {
        StringBuilder sb = new StringBuilder();
        
        for(int[] row : map) {
            for(int v : row) {
                sb.append(v).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }//print


}//class