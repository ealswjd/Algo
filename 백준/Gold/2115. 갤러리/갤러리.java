import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2115
public class Main {
    static final int U = 0, D = 1, L = 2, R = 3;
    static final char WALL = 'X';
    static final char EMPTY = '.';
    static int N, M; // 행, 열
    static char[][] map; // 겔러리

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        map = new char[N][M]; // 갤러리

        for(int i = 0; i< N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int cnt = getCnt();
        System.out.print(cnt);
    }//main


    private static int getCnt() {
        int cnt = 0;
        boolean[][][] checked = new boolean[4][N][M];

        for(int r = 0; r< N; r++) {
            for(int c = 0; c< M; c++) {
                if(map[r][c] != WALL) continue;

                // 가로 확인
                if(isWall(r, c+1)) {
                    // 위
                    if(!checked[U][r][c] && isEmpty(r-1, c) && isEmpty(r-1, c+1)) {
                        cnt++;
                        checked[U][r][c] = true;
                        checked[U][r][c+1] = true;
                    }
                    // 아래
                    if(!checked[D][r][c] && isEmpty(r+1, c) && isEmpty(r+1, c+1)) {
                        cnt++;
                        checked[D][r][c] = true;
                        checked[D][r][c+1] = true;
                    }
                }

                // 세로 확인
                if(isWall(r+1, c)) {
                    // 왼쪽
                    if(!checked[L][r][c] && isEmpty(r, c-1) && isEmpty(r+1, c-1)) {
                        cnt++;
                        checked[L][r][c] = true;
                        checked[L][r+1][c] = true;
                    }
                    // 오른쪽
                    if(!checked[R][r][c] && isEmpty(r, c+1) && isEmpty(r+1, c+1)) {
                        cnt++;
                        checked[R][r][c] = true;
                        checked[R][r+1][c] = true;
                    }
                }

            }
        }

        return cnt;
    }//getCnt


    private static boolean isWall(int r, int c) {
        return rangeCheck(r, c) && map[r][c] == WALL;
    }//isWall


    private static boolean isEmpty(int r, int c) {
        return rangeCheck(r, c) && map[r][c] == EMPTY;
    }//isEmpty


    private static boolean rangeCheck(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }//rangeCheck

}//class