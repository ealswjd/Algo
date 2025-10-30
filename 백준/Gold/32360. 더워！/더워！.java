import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/32360
public class Main {
    private static final char END = 'E', INDOOR = 'H', WALL = '#';
    private static final int MAX_B = 100;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M, K, C; // 격자의 크기, 실내 감소 불쾌함, 실외 증가 불쾌함
    private static int sr, sc; // 출발 위치
    private static char[][] map; // 격자
    

    public static void main(String[] args) throws IOException {
        init();
        int time = getTime();

        System.out.print(time);
    }//main

    /**
     * 민규가 약속 장소에 쓰러지지 않고 도달할 수 있는 최소 시간을 출력
     * 만약 그런 방법이 없다면 -1 출력
      */
    private static int getTime() {
        Queue<Player> q = new LinkedList<>();
        boolean[][][] visited = new boolean[MAX_B][N][M];

        q.offer(new Player(sr, sc, 0, 0));
        visited[0][sr][sc] = true;

        Player cur;
        while(!q.isEmpty()) {
            cur = q.poll();

            // 약속 장소에 도달
            if(map[cur.r][cur.c] == END) {
                return cur.time;
            }

            // 벽이 아닌 인접한 칸으로 이동
            for(int i=0; i<4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int nScore = cur.score;

                if(rangeCheck(nr, nc)) continue;

                if(map[nr][nc] == INDOOR) {
                    // 실내라면 불쾌함이 max(0, B-K)까지 감소한다.
                    nScore = Math.max(0, nScore - K);
                } else {
                    // 실외라면 불쾌함이 min(100, B+C)까지 증가
                    nScore = Math.min(MAX_B, nScore + C);
                }

                // 불쾌함이 100이 되면 행동을 멈춘다.
                if(nScore == MAX_B) continue;
                if(visited[nScore][nr][nc]) continue;

                visited[nScore][nr][nc] = true;
                q.offer(new Player(nr, nc, cur.time+1, nScore));
            }

            // 이동하지 않고 가만히 있는다.
            if(map[cur.r][cur.c] == INDOOR) {
                int nScore = Math.max(0, cur.score - K);
                if(!visited[nScore][cur.r][cur.c]) {
                    visited[nScore][cur.r][cur.c] = true;
                    q.offer(new Player(cur.r, cur.c, cur.time+1, nScore));
                }
            }
        }

        return -1;
    }//getTime


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M || map[r][c] == WALL;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 줄에 격자의 크기를 나타내는 정수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 두 번째 줄에 실내에서 감소하는 불쾌함, 실외에서 증가하는 불쾌함을 나타내는 정수
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 실내에서 감소
        C = Integer.parseInt(st.nextToken()); // 실외에서 증가

        // 세 번째 줄부터 거리의 상태
        map = new char[N][M];
        for(int i=0; i<N; i++) {
            map[i] = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                if(map[i][j] == 'S') {
                    sr = i;
                    sc = j;
                }
            }
        }

        br.close();
    }//init


    private static class Player {
        int r;
        int c;
        int time; // 시간
        int score; // 불쾌지수

        Player(int r, int c, int time, int score) {
            this.r = r; this.c = c;
            this.time = time;
            this.score = score;
        }
    }//Player


}//class