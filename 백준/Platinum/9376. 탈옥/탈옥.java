import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/9376
public class Main {
    private static final int WALL = -1, DOOR = 8, MAX = 22_222;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static int H, W; // 지도의 크기
    private static int[][] map; // 지도
    private static Position[] players; // 죄수 두 명 위치
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        StringBuilder ans = new StringBuilder();
        while(T-- > 0) {
            init(br);

            // 두 죄수를 탈옥시키기 위해서 열어야 하는 문의 최솟값
            int openCnt = getCnt();
            ans.append(openCnt).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static int getCnt() {
        int[][] costA = bfs(players[0].r, players[0].c); // 죄수 1
        int[][] costB = bfs(players[1].r, players[1].c); // 죄수 2
        int[][] costOut = bfs(0, 0); // 밖부터

        int cnt = MAX; // 탈옥시키기 위해서 열어야 하는 문의 최솟값

        for(int r=0; r<=H; r++) {
            for(int c=0; c<=W; c++) {
                // 벽이거나 죄수 1, 2가 도달 못하면 불가능함
                if (map[r][c] == WALL) continue;
                if (costA[r][c] == -1 || costB[r][c] == -1) continue;

                // 세 지점이 만날 수 있으면 열린문 개수 합산
                int total = costA[r][c] + costB[r][c] + costOut[r][c];
                // 해당 지점이 문이면 중복 개수 차감
                if (map[r][c] == DOOR) total -= 2;

                cnt = Math.min(cnt, total);
            }
        }

        return cnt;
    }//getCnt

    private static int[][] bfs(int r, int c) {
        int[][] cost = new int[H + 1][W + 1]; // 해당 지점에 도착하기 위해 열어야 하는 문의 개수
        Deque<Position> q = new ArrayDeque<>();

        for(int i=0; i<=H; i++) {
            Arrays.fill(cost[i], -1);
        }

        q.offer(new Position(r, c));
        cost[r][c] = 0;

        Position cur;
        while(!q.isEmpty()) {
            cur = q.pollFirst();
            r = cur.r;
            c = cur.c;

            for(int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                // 범위 밖이거나 벽이거나 이미 지나간 적 있음
                if (!inRange(nr, nc)) continue;
                if (map[nr][nc] == WALL || cost[nr][nc] != -1) continue;

                if (map[nr][nc] == DOOR) { // 문 - 열기
                    cost[nr][nc] = cost[r][c] + 1;
                    q.offerLast(new Position(nr, nc));
                } else { // 빈 공간 - 지나가기
                    cost[nr][nc] = cost[r][c];
                    q.offerFirst(new Position(nr, nc));
                }
            }
        }

        return cost;
    }//bfs

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r <= H && c <= W;
    }//inRange

    private static void init(BufferedReader br) throws IOException {
        // 평면도의 높이 h와 너비 w가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken()) + 1;
        W = Integer.parseInt(st.nextToken()) + 1;

        map = new int[H + 1][W + 1]; // 지도 (테두리에 빈 공간 생성)
        players = new Position[2];   // 죄수 두 명 위치

        int p = 0;
        for(int r=1; r<H; r++) {
            char[] tmp = br.readLine().toCharArray();
            for(int c=1; c<W; c++) {
                if (tmp[c-1] == '.') continue;

                if (tmp[c-1] == '*') { // 벽
                    map[r][c] = WALL;
                } else if (tmp[c-1] == '#') { // 문
                    map[r][c] = DOOR;
                } else { // 죄수
                    players[p++] = new Position(r, c);
                }
            }
        }
    }//init

    private static class Position {
        int r;
        int c;
        Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }//Position

}//class