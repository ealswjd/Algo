import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1577
public class Main {
    private static final int[] dx = {1, 0};
    private static final int[] dy = {0, 1};
    private static int N, M; // 도로의 가로 크기 N과 세로 크기 M
    private static boolean[][][] impossible; // 공사중인 도로
    private static long[][] dp; // (0, 0)에서 (N, M)까지 가는 경우의 수


    public static void main(String[] args) throws IOException {
        init();

        long cnt = getCnt(0, 0);
        System.out.print(cnt);
    }//main


    private static long getCnt(int x, int y) {
        if(x == N && y == M) return 1;
        if(dp[x][y] != -1) return dp[x][y];

        long total = 0;
        for(int i=0; i<2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 벗어나거나 공사중인 도로면 못감
            if(rangeCheck(nx, ny) || impossible[x][y][i]) continue;
            total += getCnt(nx, ny);
        }

        return dp[x][y] = total;
    }//getCnt


    private static boolean rangeCheck(int x, int y) {
        return x > N || y > M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 도로의 가로 크기 N과 세로 크기 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine()); // 공사중인 도로의 개수 K

        impossible = new boolean[N+1][M+1][2]; // 공사중인 도로
        dp = new long[N+1][M+1];

        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], -1);
        }

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 공사중인 도로의 정보가 a b c d와 같이 주어진다.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            if(a != c) {
                if(a < c) impossible[a][b][0] = true;
                else impossible[c][d][0] = true;
            }
            else {
                if(b < d) impossible[a][b][1] = true;
                else impossible[c][d][1] = true;
            }
        }

        br.close();
    }//init


}//class