import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
    제목 : 보스몬스터 전리품 (골드 3)
    링크 : https://www.acmicpc.net/problem/20005
 */
public class Main {
    static final char BOSS = 'B', X = 'X';
    static int R, C, P, HP;
    static char[][] map;
    static int[] dps;
    static boolean[][][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        dps = new int[P];
        visited = new boolean[R][C][P];

        Queue<int[]> q = new LinkedList<>();
        int player;
        for(int r=0; r<R; r++) {
            map[r] = br.readLine().toCharArray();
            for(int c=0; c<C; c++) {
                if(map[r][c] >= 'a' && map[r][c] <= 'z') {
                    player = map[r][c] - 'a';
                    q.offer(new int[] {r, c, player});
                    visited[r][c][player] = true;
                }
            }//for c
        }//for r

        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            dps[i] = Integer.parseInt(st.nextToken());
        }//for

        HP = Integer.parseInt(br.readLine());
        br.close();

        int cnt = bfs(q);
        System.out.print(cnt);
    }//main

    private static int bfs(Queue<int[]> q) {
        int cnt = 0;

        int[] cur;
        int r, c, player, size, sum=0;
        while(!q.isEmpty()) {
            size = q.size();
            while(size-->0) {
                cur = q.poll();
                r = cur[0];
                c = cur[1];
                player = cur[2];

                for(int i=0; i<4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if(rangeCheck(nr, nc) || visited[nr][nc][player]) continue;
                    if(map[nr][nc] == BOSS) {
                        if(HP > 0) {
                            cnt++;
                            sum += dps[player];
                        }
                    }else q.offer(new int[] {nr, nc, player});
                    visited[nr][nc][player] = true;
                }//for

            }//while

            HP -= sum;
            if(HP <= 0) return cnt;
        }//while

        return cnt;
    }//bfs

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C || map[r][c] == X;
    }//rangeCheck

}//class