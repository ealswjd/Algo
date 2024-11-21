import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/20005
public class Main {
    private static final char WALL = 'X', BOSS = 'B', EMPTY = '.';
    private static int N, M, HP; // 지도 크기 N과 M, 보스의 생명 HP
    private static int bossR, bossC; // 보스 위치
    private static char[][] map; // 지도
    private static int[] power; // 플레이어들의 공격력


    public static void main(String[] args) throws IOException {
        init();

        int playerCnt = getPlayerCount();
        System.out.print(playerCnt);
    }//main

    
    private static int getPlayerCount() {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        int totalCount = 0; // 전리품을 가져갈 수 있는 플레이어의 수
        int totalPower = 0; // 플레이어들 파워
        int prevTime = 0; // 이전 시간

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.offer(new int[] {bossR, bossC, 0});
        visited[bossR][bossC] = true;

        int r, c, time, player;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            time = cur[2]; // 시간

            if(time != prevTime) {
                prevTime = time;
                HP -= totalPower;

                if(HP <= 0) break;
            }

            if(map[r][c] != EMPTY) {
                player = map[r][c] - 'a';
                totalCount++; // 전리품을 가져갈 수 있는 플레이어 수 증가
                totalPower += power[player]; // 공격 파워 증가
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc, time+1});
            }
        }

        return totalCount;
    }//getPlayerCount

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 지도의 크기를 나타내는 두 정수 M, N과 플레이어의 수 P가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        power = new int[P];
        map = new char[N][M];


        // 지도의 정보가 주어진다.
        boolean flag = true;
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M && flag; j++) {
                if(map[i][j] == BOSS) { // 보스
                    map[i][j] = EMPTY;
                    bossR = i;
                    bossC = j;
                    flag = false;

                    break;
                }
            }
        }

        // 플레이어의 아이디와 dps(1 ≤ dps ≤ 10000)가 주어진다.
        for(int i=0; i<P; i++) {
            st = new StringTokenizer(br.readLine());
            int player = st.nextToken().charAt(0) - 'a';
            int dps = Integer.parseInt(st.nextToken());
            power[player] = dps;
        }

        // 보스몬스터의 HP(10 ≤ HP ≤ 1000000)가 주어진다.
        HP = Integer.parseInt(br.readLine());

        br.close();
    }//init

    
}//class