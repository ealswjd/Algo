import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16920
public class Main {
    private static final int WALL = -1;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    
    private static int N, M, P; // 격자판의 크기 N, M, 플레이어의 수 P
    private static int total; // 남은 칸
    private static int[][] map; // NxM 격자판
    private static int[] count; // 플레이어들이 가진 성의 개수
    private static int[] S; // 확장 가능한 칸 개수
    private static boolean[][] visited; // 방문 체크
    private static PriorityQueue<Player> wait; // 다음 턴 대기


    public static void main(String[] args) throws IOException {
        init();
        game();
        print();
    }//main


    private static void game() {
        int cur = 0;

        while(!wait.isEmpty() && total > 0) {
            cur = cur % P + 1;
            bfs(cur, wait.peek().round);
        }

    }//game


    private static void bfs(int cur, int round) {
        Queue<Player> q = new LinkedList<>();

        while(!wait.isEmpty() && wait.peek().number == cur) {
            q.offer(wait.poll());
        }

        int nextRound = round + 1;

        while(!q.isEmpty()) {
            Player p = q.poll();

            if(p.cnt == 0) continue; // 확장 끝

            for(int i=0; i<4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];

                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                // 확장
                visited[nr][nc] = true;
                q.offer(new Player(cur, nr, nc, round, p.cnt - 1));
                count[cur]++;
                total--;

                // 다음 턴 대기
                wait.offer(new Player(cur, nr, nc, nextRound, S[cur]));
            }
        }

    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >=M || map[r][c] == WALL;
    }//rangeCheck


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int i=1; i<=P; i++) {
            ans.append(count[i]).append(' ');
        }

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()); // 플레이어의 수

        map = new int[N][M]; // 격자판
        visited = new boolean[N][M]; // 방문 체크
        count = new int[P+1]; // 플레이어가 가진 성의 개수
        S = new int[P+1]; // 확장 가능한 칸 개수
        wait = new PriorityQueue<>(); // 다음 턴 대기

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=P; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(tmp[j] == '#') {
                    map[i][j] = WALL;
                    continue;
                }

                if(tmp[j] == '.') total++;
                else {
                    int number = tmp[j] - '0'; // 플레이어 번호
                    count[number]++; // 성 개수
                    visited[i][j] = true;

                    wait.offer(new Player(number, i, j, 0, S[number]));
                }
            }
        }

        br.close();
    }//init


    private static class Player implements Comparable<Player> {
        int number; // 플레이어 번호
        int r; // 행
        int c; // 열
        int round; // 라운드
        int cnt; // 남은 개수

        public Player(int number, int r, int c, int round, int cnt) {
            this.number = number;
            this.r = r;
            this.c = c;
            this.round = round;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Player o) {
            if(this.round == o.round) return this.number - o.number;
            return this.round - o.round;
        }

        @Override
        public String toString() {
            return String.format("player : %d, t %d, cnt %d", number, round, cnt);
        }
    }//Player

    
}//class