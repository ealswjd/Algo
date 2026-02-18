import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18290
public class Main {
    private static final int MAX = 987654321;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};

    private static int N, M, K, max;
    private static int[][] map;
    private static boolean[][] selected;

    public static void main(String[] args) throws IOException {
        init();
        sol(0, 0, 0);

        System.out.print(max);
    }//main

    private static void sol(int idx, int cnt, int sum) {
        if (cnt == K) {
            max = Math.max(max, sum);
            return;
        }
        if (idx == N * M) return;
        if (N * M - idx < K - cnt) return;

        int r = idx / M;
        int c = idx % M;

        // 선택
        if (canSelect(r, c)) {
            selected[r][c] = true;
            sol(idx+1, cnt+1, sum + map[r][c]);
            selected[r][c] = false;
        }

        // 선택 x
        sol(idx+1, cnt, sum);
    }//sol

    private static boolean canSelect(int r, int c) {
        int nr, nc;

        for(int i=0; i<4; i++) {
            nr = r + DR[i];
            nc = c + DC[i];

            if (inRange(nr, nc) && selected[nr][nc]) {
                return false;
            }
        }

        return true;
    }//canSelect

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }//inRange

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        selected = new boolean[N][M];
        max = -MAX;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init

}//class