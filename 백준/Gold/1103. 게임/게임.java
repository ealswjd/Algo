import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1103
public class Main {
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    
    private static int N, M; // 보드의 크기
    private static int[][] map; // 보드
    private static int[][] dp; // 최대 횟수
    private static boolean[][] visited; // 방문 체크
    private static boolean isLoop; // 반복 여부

    public static void main(String[] args) throws IOException {
        init();
        int max = sol(0, 0);

        System.out.print(max);
    }//main

    private static int sol(int r, int c) {
        if (isLoop) return -1;
        if (!inRange(r, c)) return 0;
        if (visited[r][c]) {
            isLoop = true;
            return -1;
        }
        if (dp[r][c] != 0) return dp[r][c];

        int max = -1;
        int x = map[r][c];
        visited[r][c] = true;

        for(int i=0; i<4; i++) {
            int nr = r + DR[i] * x;
            int nc = c + DC[i] * x;

            max = Math.max(max, sol(nr, nc));
        }
        visited[r][c] = false;

        return dp[r][c] = isLoop ? -1 : max + 1;
    }//sol

    private static boolean inRange(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M && map[r][c] != 0;
    }//inRange

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 보드의 세로 크기 N과 가로 크기 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 보드
        dp = new int[N][M]; // 최대 횟수
        visited = new boolean[N][M]; // 방문 체크

        // 보드의 상태가 주어진다
        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if (tmp[j] != 'H') {
                    map[i][j] = tmp[j] - '0';
                }
            }
        }

        br.close();
    }//init

}//class