import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14948
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int N, M; // 병영의 세로 길이 N, 가로 길이 M
    private static int minLevel, maxLevel; // 최소 레벨, 최고 레벨
    private static int[][] map; // 병영의 블록별 레벨 제한


    public static void main(String[] args) throws IOException {
        init();

        int level = getLevel();
        System.out.print(level);
    }//main


    private static int getLevel() {
        Queue<int[]> q = new LinkedList<>();
        int level = 0; // 탈출하기 위해 달성해야 하는 최소한의 레벨
        int start = minLevel;
        int end = maxLevel;      
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(isAvailable(mid, q)) { // 현재 레벨로 통과 가능
                end = mid - 1;
                level = mid;
            }
            else { // 불가능
                start = mid + 1;
            }

            q.clear();
        }

        return level;
    }//getLevel


    private static boolean isAvailable(int level, Queue<int[]> q) {
        if(level < map[0][0]) return false;
        
        boolean[][][] visited = new boolean[2][N][M]; // 방문 체크
        int r = 0, c = 0, used = 0;

        q.offer(new int[] {r, c, used});
        visited[used][r][c] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            used = cur[2]; // 장비 사용 여부 (0:사용 가능 1:사용 불가능)

            if(r == N-1 && c == M-1) { // 탈출 완료
                return true;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[used][nr][nc]) continue;

                if(level >= map[nr][nc]) {
                    q.offer(new int[] {nr, nc, used});
                    visited[used][nr][nc] = true;
                }
                else {
                    if(used == 1) continue;

                    // 타일 건너뛰기
                    nr += dr[i];
                    nc += dc[i];

                    if(rangeCheck(nr, nc) || level < map[nr][nc]) continue;
                    if(visited[1][nr][nc]) continue;
                  
                    q.offer(new int[] {nr, nc, 1});
                    visited[1][nr][nc] = true;
                }
            }
        }

        return false;
    }//isAvailable


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 병영의 세로 길이 N, 가로 길이 M
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M]; // 병영의 블록별 레벨 제한
        minLevel = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                minLevel = Math.min(minLevel, map[i][j]); // 최소 레벨
                maxLevel = Math.max(maxLevel, map[i][j]); // 최고 레벨
            }
        }

        br.close();
    }//init


}//class

/*
5 7
0 0 0 0 0 0 3
3 3 3 3 3 0 3
0 0 0 0 3 0 3
0 3 3 3 3 3 3
0 0 0 0 0 3 0
--
3

7 7
0 0 0 0 0 0 3
3 3 3 3 3 0 3
3 3 3 3 3 0 3
0 0 0 0 3 0 3
0 3 3 3 3 3 3
0 3 3 3 3 3 3
0 0 0 0 0 0 0
--
0
 */