import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/34513
public class Main {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static int N; // 체스판 크기
    private static int sr, sc; // 룩 위치
    private static char[][] map; // 체스판

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[4][N][N];
        int r = sr, c = sc, cnt = 0;

        q.offer(new int[] {r, c, cnt});
        visited[0][r][c] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];

            if (map[r][c] == 'K') {
                System.out.print(cnt);
                return;
            }

            for(int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                while(inRange(nr, nc) && !visited[i][nr][nc]) {
                    visited[i][nr][nc] = true;
                    q.offer(new int[] {nr, nc, cnt+1});

                    if (map[nr][nc] != '.') break;

                    nr += DR[i];
                    nc += DC[i];
                }
            }
        }

        System.out.print(-1);
    }//sol

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N && map[r][c] != 'B';
    }//inRange

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<N; j++) {
                if (map[i][j] == 'R') {
                    map[i][j] = '.';
                    sr = i; sc = j;
                }
            }
        }

        br.close();
    }//init

}//class