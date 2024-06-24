import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4179
public class Main {
    static final int J=0, F=1;
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        init();

        int r = -1;
        int c = -1;
        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<R; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                if(map[i][j] == '.') continue;

                visited[i][j] = true;
                if(map[i][j] == 'F') q.offer(new int[] {i, j, F, 0});
                else if(map[i][j] == 'J') {
                    r = i;
                    c = j;
                }
            }
        }
        br.close();

        q.offer(new int[] {r, c, J, 0});
        String ans = bfs(q);
        System.out.print(ans);
    }//main

    private static String bfs(Queue<int[]> q) {
        int r, c, user, time;
        int[] cur;

        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];     // 행
            c = cur[1];     // 열
            user = cur[2];  // 지훈 or 불
            time = cur[3];  // 시간

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                
                if(rangeCheck(nr, nc)) { // 미로 탈출
                    if(user == J) return String.valueOf(time+1); // 지훈
                    else continue;
                }
                if(visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, user, time+1});
            }
        }

        return "IMPOSSIBLE"; // 탈출 불가능
    }//bfs

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }//rangeCheck

    private static void init() {
        map = new char[R][C];
        visited = new boolean[R][C];
    }//init

}//class