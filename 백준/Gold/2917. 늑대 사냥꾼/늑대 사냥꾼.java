import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2917
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int R, C; // 숲의 크기
    private static int vr, vc, er, ec; // 늑대 위치, 오두막 위치
    private static int[][] distMap; // 가장 가까운 나무와의 거리


    public static void main(String[] args) throws IOException{
        init();
        sol();
    }//main

    
    private static void sol() {
        PriorityQueue<Position> pq = new PriorityQueue<>();
        int[][] maxDist = new int[R][C]; // 가장 가까운 나무와 거리 최댓값
        int r = vr, c = vc, dist = distMap[vr][vc];

        pq.offer(new Position(r, c, dist));
        maxDist[r][c] = dist;

        Position cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            r = cur.r;
            c = cur.c;
            dist = cur.dist;

            if (maxDist[r][c] > dist) continue;
            if (r == er && c == ec) break;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (rangeCheck(nr, nc)) continue;

                // 이동하는 모든 칸에서 나무와 거리의 최솟값
                int nDist = Math.min(dist, distMap[nr][nc]);
                
                if (maxDist[nr][nc] < nDist) {
                    maxDist[nr][nc] = nDist;
                    pq.offer(new Position(nr, nc, nDist));
                }
            }
        }

        System.out.print(maxDist[er][ec]);
    }//sol
    

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }//rangeCheck
    

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 숲의 크기
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C]; // 숲의 지도
        distMap = new int[R][C]; // 가장 가까운 나무와의 거리

        for(int r=0; r<R; r++) {
            map[r] = br.readLine().toCharArray();
            for(int c=0; c<C; c++) {
                if (map[r][c] == 'V') { // 현우
                    map[r][c] = '.';
                    vr = r;
                    vc = c;
                } else if (map[r][c] == 'J') { // 오두막
                    map[r][c] = '.';
                    er = r;
                    ec = c;
                }
            }
        }

        br.close();
        bfs(map); // 가장 가까운 나무의 거리 구하기
    }//init
    

    private static void bfs(char[][] map) {
        Queue<Position> q = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if (map[r][c] == '+') { // 나무
                    q.offer(new Position(r, c, 0));
                    visited[r][c] = true;
                }
            }
        }

        Position cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            distMap[cur.r][cur.c] = cur.dist;

            for(int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (rangeCheck(nr, nc) || visited[nr][nc]) {
                    continue;
                }

                visited[nr][nc] = true;
                q.offer(new Position(nr, nc, cur.dist + 1));
            }
        }
    }//bfs
    

    private static class Position implements Comparable<Position> {
        int r;
        int c;
        int dist;

        Position(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int compareTo(Position p) {
            return p.dist - this.dist;
        }
    }//Position
    

}//class