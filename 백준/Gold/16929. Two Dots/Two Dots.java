import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : Two Dots - 기능성 (골드 4)
 * 링크 : https://www.acmicpc.net/problem/16929
 * */
public class Main {
    static int N, M;
    static char[][] map; // 게임판
    static boolean[][] visited; // 방문 체크
    static boolean isCycle; // 사이클 여부
    static int[] dr = {0, 1, 0, -1}; // 우 하 좌 상
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M]; // 게임판
        visited = new boolean[N][M]; // 방문 체크

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }//for
        br.close();

        out:for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(visited[r][c]) continue;
                visited[r][c] = true;
                dfs(r, r, c, 0, map[r][c], 1);
                if(isCycle) break out;
            }//for
        }//for

        if(isCycle) System.out.print("Yes");
        else System.out.print("No");
    }//main

    private static void dfs(int startR, int r, int c, int d, char color, int cnt) {
        if(isCycle) return;

        int nr = r + dr[d];
        int nc = c + dc[d];
        if(!rangeCheck(nr, nc, color) && nr >= startR) {
            if(d == 3 && visited[nr][nc]){
                isCycle = true;
                return;
            }else if(!visited[nr][nc]){
                visited[nr][nc] = true;
                dfs(startR, nr, nc, d, color, cnt+1);
            }
        }

        int nd = (d+1)%4;
        nr = r + dr[nd];
        nc = c + dc[nd];
        if(!rangeCheck(nr, nc, color) && nr >= startR) {
            if(nd == 3 && visited[nr][nc]){
                isCycle = true;
                return;
            }else if(!visited[nr][nc] && cnt > 1){
                visited[nr][nc] = true;
                dfs(startR, nr, nc, nd, color, cnt+1);
            }
        }



    }//dfs

    private static boolean rangeCheck(int r, int c, char color) {
        return r<0 || r>=N || c<0 || c>=M || map[r][c] != color;
    }

}//class