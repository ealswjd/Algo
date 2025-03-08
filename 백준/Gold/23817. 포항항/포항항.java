import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23817
public class Main {
    private static final int WALL = -2, MAX = Integer.MAX_VALUE;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 지도의 크기
    private static int K; // 과메기 식당 개수
    private static int minTime; // 식당을 방문하는 데 필요한 최소한의 시간
    private static int[][] map; // 지도
    private static int[][] dist; // 거리


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        Queue<int[]> q = new LinkedList<>();

        // 거리 먼저 구하기
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] >= 0) bfs(i, j, q);
            }
        }

        dfs(0, 0, 0, 0);
        minTime = minTime == MAX ? -1 : minTime;

        System.out.print(minTime);
    }//sol


    private static void dfs(int cur, int cnt, int visited, int total) {
        if(cnt == 5) {
            minTime = Math.min(minTime, total);
            return;
        }

        for(int i=1; i<K; i++) {
            if((visited & 1 << i) != 0 || dist[cur][i] == 0) continue;

            dfs(i, cnt + 1, visited | 1 << i, total + dist[cur][i]);
        }
    }//dfs


    private static void bfs(int r, int c, Queue<int[]> q) {
        int cnt = 0;
        int from = map[r][c];
        boolean[][] visited = new boolean[N][M];

        q.offer(new int[] {r, c, cnt});
        visited[r][c] = true;

        int[] cur;
        int to;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            cnt = cur[2];

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, cnt + 1});

                to = map[nr][nc];
                if(to >= 0) {
                    dist[from][to] = cnt + 1;
                }
            }
        }

    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 지도의 크기
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 지도

        K = 1;
        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();

            for(int j=0; j<M; j++) {
                if(tmp[j] == 'S') map[i][j] = 0;
                else if(tmp[j] == '.') map[i][j] = -1;
                else if(tmp[j] == 'X') map[i][j] = WALL;
                else map[i][j] = K++;
            }
        }

        dist = new int[K][K]; // 거리
        minTime = MAX; // 식당을 방문하는 데 필요한 최소한의 시간

        br.close();
    }//init


}//main