import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2253
public class Main {
    private static final int MAX_N = 10000, MAX = MAX_N + 1;
    private static final int X = (int) Math.sqrt(MAX_N * 2) + 5;
    private static int N;
    private static boolean[] isSmall;


    public static void main(String[] args) throws IOException {
        init();
        int result = sol();

        System.out.print(result);
    }//main
    

    private static int bfs() {
        if (isSmall[2]) return -1;

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N+1][X];

        int num = 2, x = 1, cnt = 1;
        q.offer(new int[] {num, x, cnt});
        visited[num][x] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            num = cur[0];
            x = cur[1];
            cnt = cur[2];

            if (num == N) return cnt;

            for(int dx=1; dx>=-1; dx--) {
                int nx = x + dx;
                int next = num + nx;

                if (nx >= 1 && next <= N && !isSmall[next]) {
                    if (visited[next][nx]) continue;

                    visited[next][nx] = true;
                    q.offer(new int[] {next, nx, cnt+1});
                }
            }
        }

        return -1;
    }//bfs
    

    private static int sol() {
        if (isSmall[2]) return -1;

        int[][] dp = new int[N+1][X];
        for(int i=0; i<=N; i++) {
            Arrays.fill(dp[i], MAX);
        }

        dp[2][1] = 1; // 2번 돌까지 1칸 점프 횟수 = 1

        for(int n=2; n<=N; n++) {
            for(int x=1; x<X; x++) {
                // 이전에 도달 불가능했음
                if (dp[n][x] == MAX) continue;

                for(int dx=-1; dx<=1; dx++) {
                    int nx = x + dx;
                    int next = n + nx;

                    if (nx >= 1 && next <= N && !isSmall[next]) {
                        dp[next][nx] = Math.min(dp[next][nx], dp[n][x] + 1);
                    }
                }
            }
        }

        int min = MAX;
        for(int x=1; x<X; x++) {
            min = Math.min(min, dp[N][x]);
        }

        return min == MAX ? -1 : min;
    }//sol
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        isSmall = new boolean[N+1];

        while(M-- > 0) {
            int num = Integer.parseInt(br.readLine());
            isSmall[num] = true;
        }

        br.close();
    }//init
    

}//class