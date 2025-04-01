import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/30894
public class Main {
    private static final int EMPTY = 0, WALL = -1;
    private static final int[] dr = {0, 1, 0, -1}; // 우하좌상
    private static final int[] dc = {1, 0, -1, 0};
    private static int N, M; // 유령의 집의 크기
    private static int sr, sc, er, ec; // 유령의 집의 입구 좌표, 출구 좌표
    private static int[][] map; // 유령의 집
    private static boolean[][][] visited; // 방문 체크


    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }//main


    private static void bfs() {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {sr, sc, 0});
        visited[0][sr][sc] = true;

        int[] cur;
        int r, c, time, t;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            time = cur[2];
            t = (time + 1) % 4;

            // 탈출 성공
            if(r == er && c == ec) {
                System.out.print(time);
                return;
            }

            // 제자리 머무르기
            if(!visited[t][r][c]) {
                visited[t][r][c] = true;
                q.offer(new int[] {r, c, time + 1});
            }

            // 이동
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[t][nr][nc]) continue;

                visited[t][nr][nc] = true;
                q.offer(new int[] {nr, nc, time + 1});
            }
        }

        // 탈출 실패
        System.out.print("GG");
    }//bfs


    private static void setGhost(List<int[]> ghostList) {
        int r, c, time, dist;

        for(int[] ghost : ghostList) {
            r = ghost[0];
            c = ghost[1];
            time = ghost[2];
            dist = ghost[3];

            while(true) {
                r += dr[dist];
                c += dc[dist];
                if(rangeCheck(r, c)) break;

                visited[time][r][c] = true;
            }
        }
    }//setGhost


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 유령의 집의 크기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 유령의 집의 입구 좌표, 출구 좌표
        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken()) - 1;
        sc = Integer.parseInt(st.nextToken()) - 1;
        er = Integer.parseInt(st.nextToken()) - 1;
        ec = Integer.parseInt(st.nextToken()) - 1;

        map = new int[N][M]; // 유령의 집
        visited = new boolean[4][N][M]; // 방문 체크
        List<int[]> ghostList = new ArrayList<>();

        // 유령의 집의 상태가 주어집니다.
        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(tmp[j] == '.') continue;

                if(tmp[j] == '#') map[i][j] = WALL; // 벽
                else { // 유령
                    int d = tmp[j] - '0'; // 방향
                    map[i][j] = WALL;

                    for(int t=0; t<4; t++) {
                        // 1초 뒤 시계 방향으로 90도 회전
                        int nd = (d + t) % 4;
                        ghostList.add(new int[] {i, j, t, nd}); // 행, 열, 시간, 방향
                    }

                }
            }
        }

        // 유령 시야 설정
        setGhost(ghostList);

        br.close();
    }//init


}//class