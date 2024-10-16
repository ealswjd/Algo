import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10711
public class Main {
    private static final int R = 0, C = 1, WAVE = 2;
    private static int H, W; // 행, 열
    private static int total; // 모래성 개수
    private static int[][] map; // 모래성
    private static int[][] empty; // 8방향 빈칸 개수
    private static int[] dr = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static int[] dc = {0, 0, -1, 1, -1, 1, -1, 1};


    public static void main(String[] args) throws IOException {
        Queue<int[]> q = new LinkedList<>();

        init(q);
        bfs(q);
    }//main

    
    private static void bfs(Queue<int[]> q) {
        boolean[][] visited = new boolean[H][W];
        int result = 0; // 모래성의 상태가 수렴하게 되는 파도 횟수
        int prevCnt = total; // 이전 모래
        int curCnt = total; // 현재 모래
        int prevWave = 0; // 이전 파도 횟수
        int[] cur;

        while(!q.isEmpty()) {
            cur = q.poll();
            int r = cur[R];
            int c = cur[C];
            int wave = cur[WAVE];

            visited[r][c] = true;

            if(prevWave != wave) {
                if(curCnt == prevCnt) break; // 모래성 상태 동일 

                prevCnt = curCnt; // 모래성 개수
                prevWave = wave; // 파도 횟수
                result = wave;
            }

            for(int i=0; i<8; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;

                if(++empty[nr][nc] >= map[nr][nc]) {
                    q.offer(new int[] {nr, nc, wave+1});
                    map[nr][nc] = 0;
                    curCnt--;
                }
            }
        }

        System.out.print(result);
    }//bfs


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W || map[r][c] == 0;
    }//rangeCheck


    private static void init(Queue<int[]> q) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new int[H][W];
        empty = new int[H][W];

        for(int i=0; i<H; i++) {
            char[] tmp = br.readLine().toCharArray();
            for(int j=0; j<W; j++) {
                if(tmp[j] != '.') {
                    map[i][j] = tmp[j] - '0';
                    total++;
                }
                else q.offer(new int[] {i, j, 0});
            }
        }
    }//init


    private static void print() {
        StringBuilder sb = new StringBuilder();

        for(int[] line : map) {
            for(int n : line) {
                sb.append(n).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }


}//class