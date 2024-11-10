import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/1600
 원숭이가 시작지점에서 도착지점까지 갈 수 있는 동작수의 최솟값 구하기
 */
public class Main {
    private static final int WALL = 1; // 장애물
    private static final int[] dr = {-1, 1, 0, 0, -2, -1, 1, 2, -2, -1, 1, 2};
    private static final int[] dc = {0, 0, -1, 1, -1, -2, -2, -1, 1, 2, 2, 1};
    private static int K, N, M; // 말처럼 움직일 수 있는 횟수, 행, 열
    private static int[][] map; // 격자판

    
    public static void main(String[] args) throws IOException {
        init();

        int minCnt = bfs();
        System.out.print(minCnt);
    }//main

    
    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N+1][M+1][K+1];
        int r = 1; // 시작 행
        int c = 1; // 시작 열
        int move = 0; // 이동 횟수
        int kCnt = K; // 말처럼 이동 가능한 횟수

        q.offer(new int[] {r, c, move, kCnt});
        visited[r][c][kCnt] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            move = cur[2]; // 이동 횟수
            kCnt = cur[3]; // 말처럼 이동 가능한 횟수

            // 목적지 도착
            if(r == N && c == M) return move;

            // K번 다 사용했으면 기본 이동, 남았으면 말처럼 이동
            int d = kCnt > 0 ? 12 : 4;
            for(int i=0; i<d; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nCnt = kCnt - (i >= 4 ? 1 : 0);

                if(rangeCheck(nr, nc) || visited[nr][nc][nCnt]) continue;

                visited[nr][nc][nCnt] = true;
                q.offer(new int[] {nr, nc, move+1, nCnt});
            }
        }

        // 도착 불가능
        return -1;
    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r <= 0 || r > N || c <= 0 || c > M || map[r][c] == WALL;
    }//rangeCheck

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); // 말처럼 움직일 수 있는 횟수
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 열
        N = Integer.parseInt(st.nextToken()); // 행

        map = new int[N+1][M+1]; // 격자판

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init

    
}//class