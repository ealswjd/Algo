import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17267
public class Main {
    private static final char WALL = '1';
    private static int N, M; // 지도의 행과 열 N, M
    private static int L, R; // 왼쪽과 오른쪽으로 갈수 있는 최대 횟수
    private static int sr, sc;
    private static char[][] map;


    public static void main(String[] args) throws IOException {
        init();

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        int cnt = 1;
        int[] dir = {-1, 1};
        Queue<int[]> q = new LinkedList<>();
        boolean[][] checked = new boolean[N][M];

        q.offer(new int[] {sr, sc, L, R});
        checked[sr][sc] = true;

        int r, c, left, right;
        int[] cur;

        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            left = cur[2];  // 왼쪽 이동 가능 횟수
            right = cur[3]; // 오른쪽 이동 가능 횟수

            for(int i=0; i<2; i++) {
                int nr = r + dir[i];

                while(!rangeCheck(nr, c) && !checked[nr][c]) {
                    q.offer(new int[] {nr, c, left, right});
                    checked[nr][c] = true;
                    cnt++;

                    nr += dir[i];
                }
            }

            if(left > 0) {
                int nc = c - 1;
                if(!rangeCheck(r, nc) && !checked[r][nc]) {
                    q.offer(new int[] {r, nc, left-1, right});
                    checked[r][nc] = true;
                    cnt++;
                }
            }
            if(right > 0) {
                int nc = c + 1;
                if(!rangeCheck(r, nc) && !checked[r][nc]) {
                    q.offer(new int[] {r, nc, left, right-1});
                    checked[r][nc] = true;
                    cnt++;
                }
            }
        }

        return cnt;
    }//getCnt

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지도의 행과 열 N, M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 왼쪽과 오른쪽으로 갈수 있는 최대 횟수
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        // 지도
        map = new char[N][M];
        sr = -1;
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();

            if(sr != -1) continue;
            for(int j=0; j<M; j++) {
                if(map[i][j] == '2') {
                    sr = i;
                    sc = j;
                }
            }

        }

        br.close();
    }//init


}//class