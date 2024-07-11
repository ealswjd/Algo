import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1175
public class Main {
    static final int C = 3; // 선물을 배달해야 하는 곳은 정확하게 2개
    static int N, M;
    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        int r = 0;
        int c = 0;
        int num = 0;
        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = tmp[j];

                if(map[i][j] == 'S') {
                    map[i][j] = '.';
                    r = i;
                    c = j;
                }
                else if(map[i][j] == 'C') {
                    map[i][j] = num++;
                }
            }
        }
        br.close();

        int time = getTime(r, c);
        System.out.print(time);
    }//main

    private static int getTime(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][M][4][C+1];
        q.offer(new int[] {r, c, 0, -1, 0});

        int[] cur;
        int time, dir, cnt;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            time = cur[2]; // 시간
            dir = cur[3]; // 이전 방향
            cnt = cur[4]; // 배달 완료

            if(cnt == C) return time;

            for(int i=0; i<4; i++) {
                if(i == dir) continue;

                int nr = r + dr[i];
                int nc = c + dc[i];
                int nCnt = cnt;

                if(rangeCheck(nr, nc) || visited[nr][nc][i][cnt]) continue;
                if(map[nr][nc] < C) nCnt |= (1 << map[nr][nc]);

                q.offer(new int[]{nr, nc, time+1, i, nCnt});
                visited[nr][nc][i][nCnt] = true;
            }
        }

        return -1;
    }//getTime

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == '#';
    }//rangeCheck

    private static void init() {
        map = new int[N][M];
    }//init

}//class