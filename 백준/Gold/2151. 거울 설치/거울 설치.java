import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// https://www.acmicpc.net/problem/2151
public class Main {
    private static final char EMPTY = '.', WALL = '*', M = '!', E = '#';
    private static final int DIR=4, MAX=3333;
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int N; // 집의 크기
    private static int sr, sc, er, ec;
    private static char[][] map; // 집
    

    public static void main(String[] args) throws IOException {
        init();
        int min = getMin();

        System.out.print(min);
    }//main
    

    private static int getMin() {
        int min = MAX;
        Queue<Position> q = new LinkedList<>();
        int[][][] minCnt = new int[DIR][N][N]; // [방향][행][열] 거울 최소 개수

        for(int i=0; i<DIR; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(minCnt[i][j], MAX);
            }
        }

        int r = sr, c = sc, cnt = 0, dir;
        for(int d=0; d<DIR; d++) {
            q.offer(new Position(r, c, cnt, d));
            minCnt[d][r][c] = cnt;
        }

        Position cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur.r;
            c = cur.c;
            cnt = cur.cnt; // 거울 개수
            dir = cur.dir; // 방향

            if (minCnt[dir][r][c] < cnt) {
                continue;
            }
            if (map[r][c] == E) {
                min = Math.min(min, cnt);
                continue;
            }

            // 거울 설치 x
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            if (!rangeCheck(nr, nc) && minCnt[dir][nr][nc] > cnt) {
                minCnt[dir][nr][nc] = cnt;
                q.offer(new Position(nr, nc, cnt, dir));
            }

            // 거울 설치
            if (map[r][c] == M) {
                // 양면 거울(2가지 방향 가능)
                for(int i=1; i<DIR; i+=2) {
                    int nDir = (dir + i) % 4; // 거울에 반사되는 방향
                    int nCnt = cnt + 1; // 거울 설치
                    nr = r + dr[nDir];
                    nc = c + dc[nDir];

                    if (!rangeCheck(nr, nc) && minCnt[nDir][nr][nc] > nCnt) {
                        minCnt[nDir][nr][nc] = nCnt;
                        q.offer(new Position(nr, nc, nCnt, nDir));
                    }
                }
            }
        }

        return min;
    }//getMin
    

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N || map[r][c] == WALL;
    }//rangeCheck
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        sr = -1;

        for(int r=0; r<N; r++) {
            map[r] = br.readLine().toCharArray();
            for(int c=0; c<N; c++) {
                if (map[r][c] == E) { // 문
                    if (sr == -1) {
                        sr = r;
                        sc = c;
                        map[r][c] = EMPTY;
                    }
                }
            }
        }

        br.close();
    }//init

    private static class Position {
        int r;
        int c;
        int cnt; // 거울 개수
        int dir; // 방향

        Position(int r, int c, int cnt, int dir) {
            this.r = r;
            this.c = c;
            this.cnt = cnt;
            this.dir = dir;
        }
    }//Position
    

}//class