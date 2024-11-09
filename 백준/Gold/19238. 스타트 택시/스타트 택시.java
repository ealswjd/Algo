import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/19238
public class Main {
    private static final int R = 0, C = 1, D = 2, F = 3, P = 4;
    private static final int WALL = -1;
    private static int N, M, K;
    private static int startR, startC; // 백준이 운전을 시작하는 칸의 행 번호와 열 번호
    private static int[][] map; // 지도
    private static int[][] goal; // 승객들 목적지
    private static int[] dr = {-1, 1, 0, 0};
    private static int[] dc = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        init();

        int result = move();
        System.out.print(result);
    }//main


    private static int move() {
        int passengerCount = M; // 승객 수

        while(passengerCount > 0){
            int passenger = findPassenger();
            if(passenger == -1) return -1;

            if(bfs(passenger)) passengerCount--;
            else return -1;
        }

        return K;
    }//move


    private static boolean bfs(int passenger) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[] {startR, startC, 0, K});
        visited[startR][startC] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[R]; // 행
            int c = cur[C]; // 열
            int dist = cur[D]; // 이동 거리
            int fuel = cur[F]; // 남은 연료

            if(r == goal[passenger][R] && c == goal[passenger][C]) {
                // 목적지 도착 -> 연료 충전
                K = fuel + dist * 2;
                startR = r;
                startC = c;
                return true;
            }
            if(fuel == 0) continue;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, dist+1, fuel-1});
            }
        }

        return false;
    }//bfs


    private static int findPassenger() {
        int fuel = K;

        /*  행, 열, 거리, 승객 번호
            승객이 여러 명이면 그중 행 번호가 가장 작은 승객을,
            그런 승객도 여러 명이면 그중 열 번호가 가장 작은 승객을 고른다.
         */
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[R] == o2[R]) return o1[C] - o2[C];
            else if(o1[D] == o2[D]) return o1[R] - o2[R];
            return o1[D] - o2[D];
        });

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        q.offer(new int[] {startR, startC, 0, fuel});
        visited[startR][startC] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[R]; // 행
            int c = cur[C]; // 열
            int dist = cur[D]; // 이동 거리
            fuel = cur[F]; // 남은 연료

            // 더 가까운 승객이 있음
            if(!pq.isEmpty() && pq.peek()[D] < dist) continue;
            // 승객이면 후보에 담음
            if(map[r][c] > 0) {
                pq.offer(new int[] {r, c, dist, fuel, map[r][c]});
                continue;
            }
            // 연료 바닥
            if(fuel == 0) continue;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, dist+1, fuel-1});
            }
        }

        if(pq.isEmpty()) return -1;

        // 승객 태움
        startR = pq.peek()[R];
        startC = pq.peek()[C];
        map[startR][startC] = 0;
        K = pq.peek()[F]; // 연료 줄어듬

        return pq.poll()[P]; // 승객 번호
    }//findPassenger


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N || map[r][c] == WALL;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 격자 크기
        M = Integer.parseInt(st.nextToken()); // 승객 수
        K = Integer.parseInt(st.nextToken()); // 초기 연로의 양

        map = new int[N][N]; // 지도
        goal = new int[M+1][2]; // 승객들 목적지

        // 백준이 활동할 영역의 지도가 주어진다.
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) map[i][j] = WALL;
            }
        }

        // 운전을 시작하는 칸의 행 번호와 열 번호가 주어진다.
        st = new StringTokenizer(br.readLine());
        startR = Integer.parseInt(st.nextToken()) - 1;
        startC = Integer.parseInt(st.nextToken()) - 1;

        // 각 승객의 출발지의 행과 열 번호, 그리고 목적지의 행과 열 번호
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            // 출발지의 행과 열
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            // 목적지의 행과 열
            goal[i][R] = Integer.parseInt(st.nextToken()) - 1;
            goal[i][C] = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = i;
        }

        br.close();
    }//init


}//class