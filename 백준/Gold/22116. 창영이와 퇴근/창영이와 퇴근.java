import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 창영이와 퇴근 (골드 4)
 * 링크 : https://www.acmicpc.net/problem/22116
 * */
public class Main {
    static final int R=0, C=1, MAX=2, INF = 1_000_000_000;
    static int N;
    static int[][] map, minCost;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minCost[i][j] = INF;
            }//for
        }//for
        br.close();

        int min = dijkstra(0, 0);
        System.out.print(min);
    }//main

    private static int dijkstra(int r, int c) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[MAX] - o2[MAX];
            }
        });

        minCost[r][c] = 0;
        pq.offer(new int[] {r, c, minCost[r][c]});

        int[] cur;
        int max, maxTmp;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur[R];
            c = cur[C];
            max = cur[MAX];

            if(r == N-1 && c == N-1) return max;
            if(visited[r][c]) continue;

            visited[r][c] = true;
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(rangeCheck(nr, nc)) continue;
                maxTmp = Math.max(max, Math.abs(map[nr][nc] - map[r][c]));
                if(minCost[nr][nc] > maxTmp) {
                    minCost[nr][nc] = maxTmp;
                    pq.offer(new int[] {nr, nc, minCost[nr][nc]});
                }//if
            }//for

        }//while

        return minCost[N-1][N-1];
    }//dijkstra

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N || visited[r][c];
    }//rangeCheck

    
    private static void init() {
        map = new int[N][N];
        visited = new boolean[N][N];
        minCost = new int[N][N];
    }//init

}//class