import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/33907
public class Main {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    private static int N, M, K; // 지도의 크기 N, M, 스프레이 개수
    private static int max; // 최대 위험도
    private static int[][] map; // 위험도

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // 투명 스프레이 사용 횟수가 K 이하가 되도록 하는 최소의 위험도 X를 구하라.
        // 퇴근이 불가능한 경우에는 -1을 출력한다.
        int x = -1; // 최소의 위험도
        int start = 0;
        int end = max;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if (bfs(mid)) {
                end = mid - 1;
                x = mid;
            } else {
                start = mid + 1;
            }
        }

        System.out.print(x);
    }//sol

    private static boolean bfs(int x) {
        Deque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int r=0, c=0, k=0;

        q.offerFirst(new int[] {r, c, k});
        visited[r][c] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.pollFirst();
            r = cur[0];
            c = cur[1];
            k = cur[2];

            // 퇴근
            if (r == N-1 && c == M-1) {
                return k <= K;
            }

            for(int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                if (!inRange(nr, nc) || visited[nr][nc]) continue;

                // x 초과라면 투명 스프레이 사용
                if (map[nr][nc] > x) {
                    // 사용 가능한 스프레이가 남아있으면 이동 가능
                    if (k < K) {
                        q.offerLast(new int[] {nr, nc, k+1});
                        visited[nr][nc] = true;
                    }
                } else {
                    // x 이하면 지나갈 수 있음
                    q.offerFirst(new int[] {nr, nc, k});
                    visited[nr][nc] = true;
                }
            }
        }

        return false;
    }//bfs

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }//inRange

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];

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