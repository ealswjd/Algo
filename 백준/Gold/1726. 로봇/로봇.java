import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1726
public class Main {
    static final int K = 3;
    static final int ROW = 0, COL = 1, DIR = 2, CNT = 3;
    static final int R = 0, L = 1, D = 2, U = 3; // 동 서 남 북
    static int N, M; // 행, 열
    static int[][] map; // 공장
    static int[] dr = {0, 0, 1, -1};
    static int[] dc = {1, -1, 0, 0};
    static int[] leftDir = {U, D, R, L};
    static int[] rightDir = {D, U, L, R};

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열

        map = new int[N][M]; // 공장

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] start = input(new StringTokenizer(br.readLine()), 3);
        int[] end = input(new StringTokenizer(br.readLine()), 3);

        int cnt = getCnt(start, end);
        System.out.print(cnt);
    }//main

    
    private static int getCnt(int[] start, int[] end) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][4];
        int r = start[ROW];
        int c = start[COL];
        int dir = start[DIR];
        int cnt = 0;
        q.offer(new int[] {r, c, dir, cnt});
        visited[r][c][dir] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[ROW];
            c = cur[COL];
            dir = cur[DIR];
            cnt = cur[CNT];

            if(r == end[ROW] && c == end[COL] && dir == end[DIR]) return cnt;

            for(int k=1; k<=K; k++) {
                int nr = r + (dr[dir] * k);
                int nc = c + (dc[dir] * k);
                if(rangeCheck(nr, nc)) break;
                if(visited[nr][nc][dir]) continue;
                q.offer(new int[] {nr, nc, dir, cnt+1});
                visited[nr][nc][dir] = true;
            }

            int left = leftDir[dir];
            int right = rightDir[dir];

            if(!visited[r][c][left]) {
                q.offer(new int[] {r, c, left, cnt+1});
                visited[r][c][left] = true;
            }

            if(!visited[r][c][right]){
                q.offer(new int[] {r, c, right, cnt+1});
                visited[r][c][right] = true;
            }

        }

        return 0;
    }//getCnt

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == 1;
    }//rangeCheck

    
    private static int[] input(StringTokenizer st, int len) {
        int[] arr = new int[len];

        for(int i=0; i<len; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        return arr;
    }//input

    
}//class