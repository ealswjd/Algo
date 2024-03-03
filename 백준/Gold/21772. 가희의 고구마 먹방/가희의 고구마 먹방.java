import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 가희의 고구마 먹방 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/21772
 * */
public class Main {
    static int R, C, T, max;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 맵의 세로 크기
        C = Integer.parseInt(st.nextToken()); // 맵의 가로 크기
        T = Integer.parseInt(st.nextToken()); // 가희가 이동하는 시간

        map = new char[R][C];
        visited = new boolean[R][C];

        int gr=0, gc=0; // 가희 위치
        for(int r=0; r<R; r++) {
            map[r] = br.readLine().toCharArray();
            for(int c=0; c<C; c++) {
                if(map[r][c] == 'G') {
                    gr = r;
                    gc = c;
                }//if
            }//for
        }//for
        br.close();

        dfs(gr, gc, 0, 0);
        System.out.print(max);
    }//main

    private static void dfs(int r, int c, int cnt, int time) {
        if(time == T) {
            max = Math.max(max, cnt);
            return;
        }//if

        int nr, nc;
        for(int i=0; i<4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(rangeCheck(nr, nc)) continue; // 범위, 장애물 체크

            if(map[nr][nc] == 'S' && !visited[nr][nc]) { // 고구마
                visited[nr][nc] = true;
                dfs(nr, nc, cnt+1, time+1);
                visited[nr][nc] = false;
            }else { // 고구마 없음
                dfs(nr, nc, cnt, time+1);
            }
        }//for

    }//dfs

    // 범위, 장애물 체크
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C || map[r][c] == '#';
    }//rangeCheck

}//class