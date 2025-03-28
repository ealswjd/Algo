import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20419
public class Main {
    private static final int U = 0, D = 1, L = 2, R = 3;
    private static final int[] dr = {-1, 1, 0, 0}; // 상하좌우
    private static final int[] dc = {0, 0, -1, 1}; // 상하좌우
    private static final int[] tl = {L, R, D, U};  // 좌회전
    private static final int[] tr = {R, L, U, D};  // 우회전
    private static int N, M, K; // 미로의 행 N, 열 M, 준서가 가진 주문서 세트의 개수 K
    private static int[][] map; // 미로


    public static void main(String[] args) throws IOException {
        init();

        String result = bfs();
        System.out.print(result);
    }//main


    private static String bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][K+1][K+1]; // [행][열][좌][우]

        q.offer(new int[] {0, 0, K, K});
        visited[0][0][K][K] = true;

        int[] cur;
        int r, c, left, right, d, nr, nc;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            left = cur[2]; // 좌회전 가능 횟수
            right = cur[3]; // 우회전 가능 횟수
            d = map[r][c]; // 화살표 방향

            // 탈출 가능
            if(r == N-1 && c == M-1) {
                return "Yes";
            }

            // 기존 방향
            nr = r + dr[d];
            nc = c + dc[d];
            if(rangeCheck(nr, nc) && !visited[nr][nc][left][right]) {
                q.offer(new int[] {nr, nc, left, right});
                visited[nr][nc][left][right] = true;
            }

            // 좌회전
            if(left > 0) {
                int nd = tl[d];
                nr = r + dr[nd];
                nc = c + dc[nd];

                if(rangeCheck(nr, nc) && !visited[nr][nc][0][right]) {
                    q.offer(new int[] {nr, nc, 0, right});
                    visited[nr][nc][0][right] = true;
                }
            }
            // 우회전
            if(right > 0) {
                int nd = tr[d];
                nr = r + dr[nd];
                nc = c + dc[nd];

                if(rangeCheck(nr, nc) && !visited[nr][nc][left][0]) {
                    q.offer(new int[] {nr, nc, left, 0});
                    visited[nr][nc][left][0] = true;
                }
            }
        }

        return "No";
    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 준서가 가진 주문서 세트의 개수

        map = new int[N][M]; // 미로

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(tmp[j] == 'D') map[i][j] = D;
                else if(tmp[j] == 'L') map[i][j] = L;
                else if(tmp[j] == 'R') map[i][j] = R;
            }
        }

        br.close();
    }//init


}//class