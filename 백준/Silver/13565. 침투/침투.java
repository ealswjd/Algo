import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13565
public class Main {
    private static final char W = '0';
    private static final int[] dr = {0, 0, -1, 1};
    private static final int[] dc = {-1, 1, 0, 0};
    private static int N, M;
    private static char[][] map; // 격자
    private static boolean[][] visited; // 방문체크


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        String result = "NO";

        init();

        for(int c=0; c<M; c++) {
            if(map[0][c] == W && !visited[0][c]) {
                visited[0][c] = true;
                if(dfs(0, c)) {
                    result = "YES";
                    break;
                }
            }
        }

        System.out.print(result);
    }//sol


    private static boolean dfs(int r, int c) {
        if(r == N-1) return true;

        boolean result = false;

        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(!isRange(nr, nc) || visited[nr][nc]) continue;

            visited[nr][nc] = true;
            result = dfs(nr, nc);

            if(result) break;
        }

        return result;
    }//dfs


    private static boolean isRange(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M && map[r][c] == W;
    }//isRange


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M]; // 격자
        visited = new boolean[N][M]; // 방문체크

        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        br.close();
    }//init


}//class