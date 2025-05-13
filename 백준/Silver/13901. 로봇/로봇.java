import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13901
public class Main {
    // 1은 위 방향, 2은 아래 방향, 3은 왼쪽 방향, 4는 오른쪽
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int R, C; // 방의 크기 R, C
    private static int sr, sc; // 로봇의 시작 위치 sr, sc
    private static int[] dir; // 이동 방향의 순서
    private static boolean[][] isWall; // 벽


    public static void main(String[] args) throws IOException{
        init();
        move();
    }//main


    private static void move() {
        boolean[][] visited = new boolean[R][C];
        boolean isEnd;
        int r = sr;
        int c = sc;
        int nr, nc;
        int d = 0;

        visited[r][c] = true;

        while(true) {
            isEnd = true;

            for(int i=0; i<4; i++) {
                nr = r + dr[dir[d]];
                nc = c + dc[dir[d]];

                if(rangeCheck(nr, nc, visited)) {
                    isEnd = false;
                    r = nr;
                    c = nc;
                    visited[r][c] = true;
                    break;
                }

                d = (d + 1) % 4;
            }


            if(isEnd) break;
        }

        System.out.printf("%d %d", r, c);
    }//move


    private static boolean rangeCheck(int r, int c, boolean[][] visited) {
        return r >= 0 && r < R && c >= 0 && c < C && !isWall[r][c] && !visited[r][c];
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 방의 크기 R, C
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        // 장애물의 개수
        int K = Integer.parseInt(br.readLine());

        isWall = new boolean[R][C];

        // k개의 줄에는 각 장애물 위치 r, c가 주어진다.
        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            isWall[r][c] = true;
        }

        // // 로봇의 시작 위치 sr, sc
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        // 이동 방향의 순서
        st = new StringTokenizer(br.readLine());
        dir = new int[4];
        for(int i=0; i<4; i++) {
            dir[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        br.close();
    }//init


}//class