import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/15730
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 수영장 크기
    private static int[][] map; // 수영장


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
        boolean[][] visited = new boolean[N][M];
        int result = 0;
        int totalCnt = N * M;
        int cnt = 0;

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(isOutLine(i, j)) {
                    pq.offer(new int[] {i, j, map[i][j]});
                    visited[i][j] = true;
                    cnt++;
                }
            }
        }

        int[] cur;
        int r, c, h, diff;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur[0];
            c = cur[1];
            h = cur[2];

            if(cnt == totalCnt) break;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(isOutOfRange(nr, nc) || visited[nr][nc]) continue;

                diff = h - map[nr][nc];
                result += Math.max(diff, 0);

                pq.offer(new int[] {nr, nc, Math.max(h, map[nr][nc])});
                visited[nr][nc] = true;
                cnt++;
            }
        }

        // 최대한 물을 채웠을 때 얼마만큼의 물을 채울 수 있는지를 출력
        System.out.print(result);
    }//sol


    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//isOutOfRange


    private static boolean isOutLine(int r, int c) {
        return r == 0 || r == N-1 || c == 0 || c == M-1;
    }//isOutLine


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수영장 세로 길이
        M = Integer.parseInt(st.nextToken()); // 수영장 가로 길이

        map = new int[N][M]; // 수영장

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class