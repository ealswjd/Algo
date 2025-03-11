import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24463
public class Main {
    private static final char EMPTY = '.', WALL = '+';
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 미로의 크기
    private static int sr, sc, er, ec; // 시작 위치, 종료 위치
    private static char[][] map; // 미로
    private static boolean[][] visited; // 방문 체크


    public static void main(String[] args) throws IOException {
        init();
        dfs(sr, sc);
        print();
    }//main


    private static boolean dfs(int r, int c) {
        visited[r][c] = true;
        if(r == er && c == ec) return true;

        int nr, nc;
        boolean flag = false;

        for(int i=0; i<4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
            if(dfs(nr, nc)) flag = true;
        }

        if(!flag) map[r][c] = '@';

        return flag;
    }//dfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j] || map[i][j] != EMPTY) ans.append(map[i][j]);
                else ans.append('@');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//print


    private static boolean isOut(int r, int c) {
        return r == 0 || r == N-1 || c == 0 || c == M-1;
    }//isOut


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 미로 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M]; // 미로
        visited = new boolean[N][M]; // 방문 체크

        sr = -1;
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(isOut(i, j) && map[i][j] == EMPTY) {
                    if(sr == -1) {
                        sr = i;
                        sc = j;
                    }
                    else {
                        er = i;
                        ec = j;
                    }
                }
            }
        }

        br.close();
    }//init


}//class