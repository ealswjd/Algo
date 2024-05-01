import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/5913
public class Main {
    static final int N=5;
    static int K;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        visited = new boolean[N][N];

        StringTokenizer st;
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            visited[r][c] = true;
        }
        br.close();

        visited[0][0] = true;

        int cnt = getCnt(0, 0, K+1);
        System.out.print(cnt);
    }//main

    private static int getCnt(int r, int c, int moveCnt) {
        if(r==N-1 && c==N-1) {
            if(moveCnt == N*N) return 1;
            else return 0;
        }

        int cnt = 0;
        int nr, nc;

        for(int i=0; i<4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(!checkAvailability(nr, nc)) continue;

            visited[nr][nc] = true;
            cnt += getCnt(nr, nc, moveCnt+1);
            visited[nr][nc] = false;
        }

        return cnt;
    }//getCnt

    private static boolean checkAvailability(int r, int c) {
        return r < N && r >= 0 && c < N && c >= 0 && !visited[r][c];
    }

}//class