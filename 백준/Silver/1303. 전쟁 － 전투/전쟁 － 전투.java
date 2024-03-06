import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1303
public class Main {
    static final char WHITE='W', BLUE='B';
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }//for
        br.close();

        int whiteSum = 0;
        int blueSum = 0;
        int cnt;
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                if(visited[r][c]) continue;
                visited[r][c] = true;
                cnt = dfs(r, c, 1, map[r][c]);
                if(map[r][c] == WHITE) whiteSum += (int) Math.pow(cnt, 2);
                else blueSum += (int) Math.pow(cnt, 2);
            }//for
        }//for

        StringBuilder ans = new StringBuilder();
        ans.append(whiteSum).append(' ').append(blueSum);
        System.out.print(ans);
    }//main

    private static int dfs(int r, int c, int cnt, char color) {
        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(rangeCheck(nr, nc, color) || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            cnt = dfs(nr, nc, cnt+1, color);
        }

        return cnt;
    }//dfs

    private static boolean rangeCheck(int r, int c, char color) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] != color;
    }//rangeCheck

}//class