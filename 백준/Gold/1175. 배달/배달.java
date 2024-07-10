import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1175
public class Main {
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
        int num = 1;
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
        boolean[][][][][] visited = new boolean[N][M][4][2][2];
        q.offer(new int[] {r, c, 0, -1, 0, 0});

        int[] cur;
        int time, dir, c1, c2;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            time = cur[2]; // 시간
            dir = cur[3]; // 이전 방향
            c1 = cur[4]; // 선물 1
            c2 = cur[5]; // 선물 2

            if(c1 == 1 && c2 == 1) return time;

            for(int i=0; i<4; i++) {
                if(i == dir) continue;
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nc1 = c1;
                int nc2 = c2;
                
                if(rangeCheck(nr, nc)) continue;
                if(visited[nr][nc][i][c1][c2]) continue;

                if(map[nr][nc] == 1) nc1 = 1;
                else if(map[nr][nc] == 2) nc2 = 1;

                q.offer(new int[]{nr, nc, time+1, i, nc1, nc2});
                visited[nr][nc][i][nc1][nc2] = true;
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