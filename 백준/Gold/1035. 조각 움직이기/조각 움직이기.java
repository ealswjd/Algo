import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
 
// https://www.acmicpc.net/problem/1035
public class Main {
    private static final int N = 5, TOTAL = 25;
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int origin;   // 보드의 초기 상태
    private static int pieceCnt; // 조각 개수
 
 
    public static void main(String[] args) throws IOException {
        init();
 
        int cnt = getCnt();
        System.out.print(cnt);
    }//main
 
 
    private static int getCnt() {
        Queue<int[]> moveQ = new LinkedList<>();  // 조각 이동
        Queue<int[]> checkQ = new LinkedList<>(); // 조각 연결 확인
        boolean[] visited = new boolean[1 << TOTAL]; // 방문체크
 
        moveQ.offer(new int[] {origin, 0}); // 현재 상태, 이동 횟수
        visited[origin] = true;
 
        int[] cur;
        int r, c, status, moveCnt;
 
        while(!moveQ.isEmpty()) {
            cur = moveQ.poll();
            status = cur[0];  // 현재 상태
            moveCnt = cur[1]; // 이동 횟수
 
            // 모든 조각이 연결되었는지 확인
            if(isComplete(status, checkQ)) {
                return moveCnt;
            }
 
            for(int i=0, m=1; i<TOTAL; i++, m<<=1) {
                if((status & m) == 0) continue; // 현재 칸이 조각이 아님
 
                r = i / N; // 행
                c = i % N; // 열
 
                for(int d=0; d<4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
 
                    // 범위를 벗어남
                    if(rangeCheck(nr, nc)) continue;
 
                    // 다음칸으로 이동 가능한지 확인
                    int nm = 1 << (nr * N + nc);
                    if((status & nm) != 0) continue; // 다음 칸에 이미 조각이 있음
 
                    // 다음칸으로 이동
                    int next = (status ^ m) | nm;
                    if(visited[next]) continue;
 
                    visited[next] = true;
                    moveQ.offer(new int[] {next, moveCnt + 1});
                }
            }
        }
 
        return -1;
    }//getCnt
 
 
    private static boolean isComplete(int status, Queue<int[]> q) {
        int r = 0, c = 0;
 
        // 첫 번째 조각 위치 찾기
        for(int i=0, m=1; i<TOTAL; i++, m<<=1) {
            if ((status & m) == 0) continue;
 
            r = i / N; // 행
            c = i % N; // 열
 
            break;
        }
 
 
        // 첫 번째 조각과 상하좌우로 인접한 조각 개수 구하기
        int cnt = bfs(r, c, status, q);
 
        return cnt == pieceCnt;
    }//isComplete
 
 
    private static int bfs(int r, int c, int status, Queue<int[]> q) {
        boolean[][] visited = new boolean[N][N]; // 방문 체크
        int cnt = 1; // 인접한 조각 개수
 
        q.offer(new int[] {r, c});
        visited[r][c] = true;
 
        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
 
            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                int next = (nr * N + nc);
 
                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
                if(((status >> next) & 1) == 0) continue;
 
                cnt++;
                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
        }
 
        return cnt;
    }//bfs
 
 
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }//rangeCheck
 
 
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        for(int r=0; r<N; r++) {
            char[] row = br.readLine().toCharArray();
            for(int c=0; c<N; c++) {
                if(row[c] == '*') {
                    origin |= 1 << (r * N + c);
                    pieceCnt++;
                }
            }
        }
 
        br.close();
    }//init
 
 
}//class