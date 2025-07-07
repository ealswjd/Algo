import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3987
public class Main {
    private static final int U = 0, R = 1, D = 2, L = 3, C = -1, MAX = Integer.MAX_VALUE;
    private static final char[] DIR = {'U', 'R', 'D', 'L'};
    private static final int[] DR = {-1, 0, 1, 0};
    private static final int[] DC = {0, 1, 0, -1};
    private static final int[][] N_DIR = {{}, {R, U, L, D}, {L, D, R, U}};
    private static int N, M; // 항성계 크기
    private static int pr, pc; // 탐사선 위치
    private static int[][] map; // 항성계


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int[] times = new int[4];
        int maxTime = 0;
        int maxDir = 0;

        for(int d=0; d<4; d++) {
            times[d] = bfs(pr, pc, d);

            // 만약, 방향이 여러 가지가 존재한다면, U, R, D, L의 순서 중 앞서는 것 출력
            if(maxTime < times[d]) {
                maxDir = d;
                maxTime = times[d];
            }
        }

        // 시그널을 보내는 방향을 출력
        ans.append(DIR[maxDir]).append('\n');
        // 가장 긴 시간을 출력한다. 무한히 전파될 수 있다면 "Voyager" 출력
        ans.append(maxTime == MAX ? "Voyager" : maxTime);

        System.out.print(ans);
    }//sol


    private static int bfs(int r, int c, int dir) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][][] visited = new boolean[N][M][4];
        int time = 1;

        q.offer(new int[] {r, c, dir, time});

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];
            dir = cur[2];
            time = cur[3];

            // 블랙홀, 항성계를 벗어났는지 확인
            if(isEnd(r, c)) return time;
            // 무한히 전파될 수 있는지 확인
            if(visited[r][c][dir]) return MAX;
            // 행성을 만났을 경우에는 전파되는 방향이 90도로 바뀌게 된다.
            if(map[r][c] > 0) {
                int nd = map[r][c];
                dir = N_DIR[nd][dir];
            }

            visited[r][c][dir] = true;
            int nr = r + DR[dir];
            int nc = c + DC[dir];

            // 블랙홀, 항성계를 벗어났는지 확인
            if(isEnd(nr, nc)) return time;

            q.offer(new int[] {nr, nc, dir, time + 1});
        }

        return MAX;
    }//bfs


    private static boolean isEnd(int r, int c) {
        // 블랙홀이 있는 칸을 만나거나 항성계를 벗어날 때 까지 계속 전파
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == C;
    }//isEnd


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 항성계

        // '/'와 '\'는 행성을, C는 블랙홀을, '.'는 빈 칸을 나타낸다.
        for(int i=0; i<N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(tmp[j] == '.') continue;
                if(tmp[j] == '/') map[i][j] = 1;
                else if(tmp[j] == '\\') map[i][j] = 2;
                else map[i][j] = C;
            }
        }

        // 탐사선이 있는 위치 PR과 PC가 주어진다.
        st = new StringTokenizer(br.readLine());
        pr = Integer.parseInt(st.nextToken()) - 1;
        pc = Integer.parseInt(st.nextToken()) - 1;

        br.close();
    }//init


}//class