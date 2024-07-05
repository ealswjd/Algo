import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14497
public class Main {
    static final int J='*', T='#', INF=90001;
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int x = Integer.parseInt(st.nextToken()) - 1;
        int y = Integer.parseInt(st.nextToken()) - 1;

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = getCnt(r, c, x, y);
        System.out.print(cnt);
    }//main

    private static int getCnt(int r, int c, int x, int y) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2] - o2[2]);
        pq.offer(new int[] {r, c, 1});
        int[][] min = new int[N][M];
        for(int i=0; i<N; i++) {
            Arrays.fill(min[i], INF);
        }

        int[] cur;
        int cnt = 0;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];

            if(r == x && c == y) return cnt;
            if(visited[r][c]) continue;
            visited[r][c] = true;

            for(int i=0; i<4; i++) {
                int nr = r;
                int nc = c;
                while(true) {
                    nr += dr[i];
                    nc += dc[i];
                    if(rangeCheck(nr, nc) || visited[nr][nc]) break;
                    if(map[nr][nc] == '1') {
                        if(min[nr][nc] > cnt+1) {
                            min[nr][nc] = cnt+1;
                            pq.offer(new int[] {nr, nc, min[nr][nc]});
                        }
                        break;
                    }
                    else {
                        if(min[nr][nc] > cnt) {
                            min[nr][nc] = cnt;
                            pq.offer(new int[] {nr, nc, min[nr][nc]});
                        }
                    }
                }
            }
        }


        return cnt;
    }//getCnt

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck

    private static void init() {
        map = new char[N][M];
        visited = new boolean[N][M];
    }//init

}//class