import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17244
public class Main {
    static final int R=0, C=1, CNT=2, TIME=3;
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        init();

        int xCnt = 0;
        int r = 0;
        int c = 0;
        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(tmp[j] == 'X') {
                    map[i][j] = xCnt++;
                }
                else if(tmp[j] == 'S') {
                    map[i][j] = '.';
                    r = i;
                    c = j;
                }
                else map[i][j] = tmp[j];
            }
        }
        br.close();

        int cnt = bfs(r, c, xCnt);
        System.out.print(cnt);
    }//main

    private static int bfs(int r, int c, int xCnt) {
        int x = (1 << (xCnt)) - 1;
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][x+1];
        q.offer(new int[] {r, c, 0, 0});

        int[] cur;
        int cnt, time;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[R];
            c = cur[C];
            cnt = cur[CNT];
            time = cur[TIME];

            if(map[r][c] == 'E') {
                if(cnt == x) return time;
                continue;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(rangeCheck(nr, nc) || visited[nr][nc][cnt]) continue;

                int nCnt = cnt;
                if(map[nr][nc] <= xCnt) nCnt = nCnt | 1 << map[nr][nc];

                visited[nr][nc][nCnt] = true;
                q.offer(new int[] {nr, nc, nCnt, time+1});
            }
        }

        return -1;
    }//bfs

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == '#';
    }//rangeCheck

    private static void init() {
        map = new int[N][M];
    }//init

}//class