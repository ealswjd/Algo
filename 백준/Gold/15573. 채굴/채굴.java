import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15573
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 광산 크기
    private static int K; // 원하는 광물의 수
    private static int max; // 광물 최대 강도
    private static int[][] map; // 광산


    public static void main(String[] args) throws IOException {
        init();

        int D = getResult();
        System.out.print(D);
    }//main


    private static int getResult() {
        int result = max;
        int start = 1;
        int end = max;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isPossible(mid)) {
                end = mid - 1;
                result = mid;
            }
            else {
                start = mid + 1;
            }
        }

        return result;
    }//getResult


    private static boolean isPossible(int d) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int cnt = 0; // 채굴 가능한 광물 개수

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                //공기와 맞닿아 있고, 강도가 d 이하인 광물 채굴
                if(isEdge(i, j) && map[i][j] <= d) {
                    q.offer(new int[] {i, j});
                    visited[i][j] = true;
                    cnt++;
                }
            }
        }

        int[] cur;
        int r, c;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];

            if(cnt >= K) return true;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(rangeCheck(nr, nc, d) || visited[nr][nc]) continue;

                cnt++;
                q.offer(new int[] {nr, nc});
                visited[nr][nc] = true;
            }
        }

        return false;
    }//isPossible


    // 범위 체크
    private static boolean rangeCheck(int r, int c, int d) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] > d;
    }//rangeCheck


    // 공기와 맞닿아 있는 광물인지 체크
    private static boolean isEdge(int r, int c) {
        return r == 0 || c == 0 || c == M-1;
    }//isEdge


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 광산 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 원하는 광물의 수
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 광산 

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        br.close();
    }//init


}//class