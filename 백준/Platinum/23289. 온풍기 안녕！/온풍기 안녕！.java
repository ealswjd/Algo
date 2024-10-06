import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23289
public class Main {
    static final int MAX = 100;
    static final int R = 1, L = 2, U = 3, D = 4; // 동 서 북 남
    static int N, M, K;
    static int[][][] map;
    static int[][] hDr = {{}, {-1, 0, 1}, {-1, 0, 1}, {-1, -1, -1}, {1, 1, 1}};
    static int[][] hDc = {{}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {-1, 0, 1}};
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int[] to = {0, L, R, D, U};
    static  List<int[]> checkList; // 조사 대상 위치
    static  List<int[]> heaterList; // 온풍기 위치

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 온도 기준

        init();

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
                if(map[i][j][0] == 0) continue;

                // 조사 대상
                if(map[i][j][0] == 5) checkList.add(new int[] {i, j});
                // 온풍기
                else heaterList.add(new int[] {i, j, map[i][j][0]});

                map[i][j][0] = 0;
            }
        }

        int W = Integer.parseInt(br.readLine());
        while(W-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1; // 행
            int c = Integer.parseInt(st.nextToken()) - 1; // 열
            int t = Integer.parseInt(st.nextToken()); // 방향 0 = (x-1), 1 = (y+1)

            if(t == 0) { // (x-1)
                map[r][c][1] |= 1 << U;
                if(r-1 >= 0) map[r-1][c][1] |= 1 << D;
            }
            else { // (y+1)
                map[r][c][1] |= 1 << R;
                if(c+1 < M) map[r][c+1][1] |= 1 << L;
            }
        }

        int cnt = getCnt();
        System.out.print(cnt);
    }//main

    
    private static int getCnt() {
        int cnt = 0;

        while(cnt <= MAX) {
            // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
            blowWind();

            // 2. 온도가 조절됨
            adjustTemperature();

            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            decreaseTemperature();

            // 4. 초콜릿을 하나 먹는다.
            cnt++;

            // 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사.
            if(isComplete()) return cnt;
        }

        return cnt;
    }//getCnt


    // 1. 집에 있는 모든 온풍기에서 바람이 한 번 나옴
    private static void blowWind() {
        int[][] tmp = new int[N][M];
        int r;
        int c;
        int dir;
        int n = 0;

        for(int[] heater : heaterList) {
            r = heater[0];
            c = heater[1];
            dir = heater[2];

            int nr = r + hDr[dir][1];
            int nc = c + hDc[dir][1];
            tmp[nr][nc] += 5;

            bfs(nr, nc, dir, new boolean[N][M], tmp);
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j][0] += tmp[i][j];
            }
        }
    }//blowWind

    
    private static void bfs(int r, int c, int dir, boolean[][] visited, int[][] tmp) {
        int max = 5;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c, max-1});
        visited[r][c] = true;
        int tem;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];
            c = cur[1];
            tem = cur[2];

            if(tem == 0) continue;

            for(int i=0; i<3; i++) {
                int nr = r + hDr[dir][i];
                int nc = c + hDc[dir][i];

                if(!rangeCheck(nr, nc)) continue;
                if(!isPossible(r, c, nr, nc, dir, i)) continue;
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                tmp[nr][nc] += tem;
                q.offer(new int[] {nr, nc, tem - 1});
            }
        }
    }//bfs

    
    private static boolean isPossible(int r, int c, int nr, int nc, int dir, int d) {
        if(dir == R || dir == L) {
            if(d == 1) return wallCheck(r, c, dir);
            else {
                if(d == 0) return wallCheck(nr, nc, to[dir]) && wallCheck(r, c, U);
                else return wallCheck(nr, nc, to[dir]) && wallCheck(r, c, D);
            }
        }
        else {
            if(d == 1) return wallCheck(r, c, dir);
            else {
                if(d == 0) return wallCheck(nr, nc, to[dir]) && wallCheck(r, c, L);
                else return wallCheck(nr, nc, to[dir]) && wallCheck(r, c, R);
            }
        }
    }//isPossible


    private static boolean rangeCheck(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }//rangeCheck

    private static boolean wallCheck(int r, int c, int dir) {
        return (map[r][c][1] & (1 << dir)) == 0;
    }//wallCheck


    // 2. 온도가 조절됨
    private static void adjustTemperature() {
        int[][] tmp = new int[N][M];

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                int cur = map[r][c][0];
                if(cur == 0) continue;

                for(int i=0; i<4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(rangeCheck(nr, nc) && wallCheck(r, c, i+1) && map[nr][nc][0] < cur) {
                        int diff = (cur - map[nr][nc][0]) / 4;
                        tmp[nr][nc] += diff;
                        tmp[r][c] -= diff;
                    }
                }
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                map[r][c][0] += tmp[r][c];
            }
        }
    }//adjustTemperature


    // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
    private static void decreaseTemperature() {

        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(isOuterBorder(r, c) && map[r][c][0] > 0) map[r][c][0]--;
            }
        }

    }//decreaseTemperature


    // 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사.
    private static boolean isComplete() {

        for(int[] pos : checkList) {
            if(map[pos[0]][pos[1]][0] < K) return false;
        }

        return true;
    }//isComplete


    private static boolean isOuterBorder(int r, int c) {
        return r == 0 || r == N-1 || c == 0 || c == M-1;
    }//isOuterBorder

    
    private static void print(int[][] arr) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                sb.append(arr[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }//print

    
    private static void init() {
        map = new int[N][M][2];
        heaterList = new ArrayList<>();
        checkList = new ArrayList<>();
    }//init

    
}//class