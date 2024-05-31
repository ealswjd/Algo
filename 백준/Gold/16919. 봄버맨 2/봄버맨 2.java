import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16919
public class Main {
    static final char EMPTY='.', BOMB='O';
    static final int E=-1, B=3, ROW=0, COL=1, TIME=2;
    static int R, C, N;
    static int[][] map; // 크기가 R×C인 직사각형 격자판
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<int[]> emptyList, bombList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken()); // N초

        init();

        for(int i=0; i<R; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if(tmp[j] == EMPTY) { // 빈칸
                    map[i][j] = E;
                    emptyList.add(new int[] {i, j, B+1});
                }
                else { // 폭탄
                    map[i][j] = B;
                    bombList.add(new int[] {i, j, B});
                }
            }
        }
        br.close();

        int time=1;
        int T = N;
        if(N > 4) T = N%4 + 4;

        while(time <= T) {
            if(time % 2 == 0) setBomb();
            explode();
            time++;
        }

        printStatus();
    }//main

    /* 폭탄 폭발 */
    private static void explode() {
        boolean[][] visited = new boolean[R][C];
        int size = bombList.size();
        int r, c, nr, nc;

        while(size-->0) {
            int[] bomb = bombList.poll();
            r = bomb[ROW];
            c = bomb[COL];
            bomb[TIME]--;

            // 폭탄 터질 때 됐음
            if(bomb[TIME] == 0) {
                visited[r][c] = true;
                // 빈칸 리스트에 추가
                bomb[TIME] = B+1;
                emptyList.add(bomb);
                map[r][c] = E;

                // 인접한 네 칸도 함께 파괴
                for(int i=0; i<4; i++) {
                    nr = r + dr[i];
                    nc = c + dc[i];
                    if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    map[nr][nc] = E;
                    emptyList.add(new int[] {nr, nc, B+1});
                }

            }
            else { // 아직 터질 때 아님
                if(map[r][c] != E) {
                    bombList.add(bomb);
                }
            }

        }

    }//explode

    /* 폭탄 설치 */
    private static void setBomb() {

        while(!emptyList.isEmpty()) {
            int[] empty = emptyList.poll();
            bombList.add(empty);
            map[empty[ROW]][empty[COL]] = B+1;
        }

    }//setBomb

    /* 격자판 상태 출력 */
    private static void printStatus() {
        StringBuilder ans = new StringBuilder();

        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(map[i][j] == E) ans.append(EMPTY);
                else ans.append(BOMB);
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//printStatus

    /* 범위 체크 */
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }//rangeCheck

    private static void init() {
        map = new int[R][C]; // 크기가 R×C인 직사각형 격자판
        emptyList = new LinkedList<>();
        bombList = new LinkedList<>();
    }//init

}//class