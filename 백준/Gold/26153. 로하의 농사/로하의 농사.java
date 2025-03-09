import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/26153
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 마을 크기
    private static int R, C, P; // 로하 위치, 파이프 재료의 개수
    private static int[][] map; // 마을
    private static boolean[][] visited; // 방문체트
    private static int max; // 물의 최대량


    public static void main(String[] args) throws IOException {
        init();
        findMax(R, C, -1, 0, map[R][C]);

        System.out.print(max);
    }//main


    private static void findMax(int r, int c, int prev, int cnt, int total) {
        if(cnt > P) return;
        max = Math.max(max, total);
        if(cnt == P) return;

        int nextCnt;
        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

            nextCnt = cnt + 1;
            if(prev != -1 && prev != i) nextCnt++;

            visited[nr][nc] = true;
            findMax(nr, nc, i, nextCnt, total + map[nr][nc]);
            visited[nr][nc] = false;
        }
    }//findMax


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 마을의 크기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 마을
        visited = new boolean[N][M]; // 방문체크

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 로하가 사는 땅의 위치, 파이프 재료의 개수
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        visited[R][C] = true;
        br.close();
    }//init


}//class