import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/23563
public class Main {
    private static final int X = -1, EMPTY = 1, WALL = 0, MAX = 250_025;
    private static final int[] DR = {-1, 1, 0, 0};
    private static final int[] DC = {0, 0, -1, 1};
    private static int H, W; // 지도의 크기
    private static int sr, sc, er, ec; // 시작, 끝 위치
    private static int[][] map; // 지도


    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }//main


    private static void bfs() {
        Deque<int[]> q = new ArrayDeque<>();
        int[][] minTime = new int[H][W];

        for(int i=0; i<H; i++) {
            Arrays.fill(minTime[i], MAX);
        }

        int r = sr, c = sc, time = 0;
        q.offer(new int[] {r, c, time});
        minTime[sr][sc] = time;

        int[] cur;
        boolean isWall;
        while(!q.isEmpty()) {
            cur = q.pollFirst();
            r = cur[0];
            c = cur[1];
            time = cur[2];

            if (minTime[r][c] < time) {
                continue;
            }
            if (r == er && c == ec) {
                break;
            }

            isWall = map[r][c] == WALL;
            for(int i=0; i<4; i++) {
                int nr = r + DR[i];
                int nc = c + DC[i];
                if (rangeCheck(nr, nc) || map[nr][nc] == X) continue;

                int t = (isWall && map[nr][nc] == WALL) ? 0 : 1;
                int nTime = time + t;

                if (minTime[nr][nc] > nTime) {
                    minTime[nr][nc] = nTime;

                    if (t == WALL) {
                        q.addFirst(new int[] {nr, nc, nTime});
                    } else {
                        q.addLast(new int[] {nr, nc, nTime});
                    }
                }
            }
        }

        System.out.print(minTime[er][ec]);
    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W;
    }//rangeCheck


    private static void setWall() {

        for(int r=1; r<H-1; r++) {
            for(int c=1; c<W-1; c++) {
                if (map[r][c] == X) continue;

                for(int i=0; i<4; i++) {
                    int nr = r + DR[i];
                    int nc = c + DC[i];
                    if (!rangeCheck(nr, nc) && map[nr][nc] == X) {
                        map[r][c] = WALL;
                        break;
                    }
                }
            }
        }

    }//setWall


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for(int i=0; i<H; i++) {
            char[] line = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                if (line[j] == '#') {
                    map[i][j] = X;
                    continue;
                }

                if (line[j] == 'S') {
                    sr = i;
                    sc = j;
                } else if (line[j] == 'E') {
                    er = i;
                    ec = j;
                }
                map[i][j] = EMPTY;

            }
        }
        br.close();

        setWall();
    }//init
    

}//class