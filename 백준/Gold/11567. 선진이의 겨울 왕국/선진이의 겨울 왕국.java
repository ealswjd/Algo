import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11567
public class Main {
    private static final int DAMAGED = 1, EXIT = 2; // 손상된 얼음, 탈출가능
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 빙판길 크기
    private static int sr, sc, er, ec; // 선진이의 초기위치, 탈출구의 위치
    private static int[][] status; // 빙판길 상태


    public static void main(String[] args) throws IOException {
        init();

        String result = bfs();
        System.out.print(result);
    }//main


    private static String bfs() {
        Queue<int[]> q = new LinkedList<>();
        int r = sr, c = sc;

        q.offer(new int[] {r, c});

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                // 범위 체크
                if(isOutOfRange(nr, nc)) continue;

                // 탈출구
                if(nr == er && nc == ec) {
                    if(++status[nr][nc] == EXIT) return "YES"; // 탈출 가능

                    q.offer(new int[] {nr, nc});
                }
                // 탈출구 아님
                else {
                    // 손상된 길
                    if(status[nr][nc] == DAMAGED) continue;

                    status[nr][nc]++;
                    q.offer(new int[] {nr, nc});
                }
            }
        }

        // 탈출 불가능
        return "NO";
    }//bfs


    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//isOutOfRange


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 빙판길 세로 길이
        M = Integer.parseInt(st.nextToken()); // 빙판길 가로 길이

        status = new int[N][M]; // 빙판길 상태

        for(int i=0; i<N; i++) {
            char[] row = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(row[j] == 'X') {
                    status[i][j] = DAMAGED;
                }
            }
        }

        // 선진이의 초기위치
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;

        // 탈출구의 위치
        st = new StringTokenizer(br.readLine());
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        br.close();
    }//init


}//class