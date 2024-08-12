import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/4991
public class Main {
    static final int R=0, C=1, CNT=2, B=3;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int W, H; // 방의 가로 크기 w와 세로 크기 h
    static int[][] map; // 방

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken()); // 방의 가로 크기
            H = Integer.parseInt(st.nextToken()); // 세로 크기
            if(W == 0) break;

            init();

            int cnt = 0; // 더러운 칸 개수
            int r = 0; // 로봇 청소기 행
            int c = 0; // 로봇 청소기 열 

            for(int i=0; i<H; i++) {
                char[] tmp = br.readLine().toCharArray();
                for(int j=0; j<W; j++) {
                    if(tmp[j] == '*') map[i][j] = cnt++; // 더러운 칸
                    else if(tmp[j] == 'o') { // 로봇 청소기 시작 위치
                        r = i;
                        c = j;
                        map[i][j] = '.';
                    }
                    else map[i][j] = tmp[j]; // 그 외
                }
            }

            int moveCnt = bfs(r, c, cnt); // 이동 횟수
            ans.append(moveCnt).append('\n');
        }

        
        System.out.print(ans);
    }//main

    
    private static int bfs(int r, int c, int cnt) {
        int total = (1 << cnt) - 1; // 모든 더러운 칸 청소 완료 조건
        boolean[][][] visited = new boolean[H][W][total+1]; // 방문 체크
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[] {r, c, 0, 0}); // 행, 열, 이동 횟수, 청소 체크
        visited[r][c][0] = true;

        int[] cur;
        int bit;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[R]; // 행
            c = cur[C]; // 열
            cnt = cur[CNT]; // 이동 횟수
            bit = cur[B]; // 청소 체크

            if(bit == total) return cnt; // 청소 완료

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int nBit = bit;

                // 범위 체크, 방문 여부
                if(rangeCheck(nr, nc) || visited[nr][nc][bit]) continue;
                // 더러운 칸 체크
                if(isCleaningTarget(map[nr][nc])) {
                    nBit |= 1 << map[nr][nc];
                }

                visited[nr][nc][nBit] = true;
                q.offer(new int[] {nr, nc, cnt+1, nBit});
            }

        }


        return -1; // 청소 불가능한 칸 존재
    }//bfs

    
    private static boolean isCleaningTarget(int n) {
        return 0 <= n && n <= 10;
    }//isCleaningTarget

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= H || c < 0 || c >= W || map[r][c] == 'x';
    }//rangeCheck

    
    private static void init() {
        map = new int[H][W];
    }//init

    
}//class