import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26009
public class Main {
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int N, M;
    private static boolean[][] map;


    public static void main(String[] args) throws IOException {
        init();

        int cnt = bfs();

        if(cnt == -1) System.out.print("NO");
        else {
            System.out.printf("YES\n%d", cnt);
        }
    }//main


    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int r = 0;
        int c = 0;
        int cnt = 0;

        map[r][c] = true;
        q.offer(new int[] {r, c, cnt});

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];

            if(r == N-1 && c == M-1) return cnt;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || map[nr][nc]) continue;

                map[nr][nc] = true;
                q.offer(new int[] {nr, nc, cnt + 1});
            }
        }

        return -1;
    }//bfs


    private static void trafficCheck(int r, int c, int d) {

        for(int i=0; i<d; i++) {
            check(r - d + i, c + i);
            check(r + i, c + d - i);
            check(r + d - i, c - i);
            check(r - i, c - d + i);
        }

    }//trafficCheck


    private static void check(int r, int c) {
        if(rangeCheck(r, c)) return;
        map[r][c] = true;
    }//check


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new boolean[N][M];

        int K = Integer.parseInt(br.readLine());

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            map[r][c] = true;

            trafficCheck(r, c, d);
        }

        br.close();
    }//init


}//class

/*
10 10
3
4 4 2
2 6 5
8 8 2
 */