import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16137
public class Main {
    private static final int CLIFF = 0;
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, -1, 0, 1};
    private static int N, M; // 행과 열의 크기, 오작교의 주기
    private static int[][] map; // 지형
    private static boolean[][] isCross; // 절벽 교차


    public static void main(String[] args) throws IOException {
        init();

        int time = getTime();
        System.out.print(time);
    }//main

    
    private static int getTime() {
        int minTime = Integer.MAX_VALUE; // 직녀에게 갈 수 있는 최소의 시간

        Queue<int[]> q = new LinkedList<>();
        int[][][] visited = new int[2][N][N]; // 방문 체크
        int r = 0; // 행
        int c = 0; // 열
        int m = 0; // 오작교 설치 확인
        int prev = 0; // 이전에 오작교 건넜는지
        int time = 0; // 이동 시간

        for(int i=0; i<2; i++) {
            for(int j=0; j<N; j++) {
                Arrays.fill(visited[i][j], -1);
            }
        }

        q.offer(new int[] {r, c, m, prev, time});
        visited[r][c][0] = 0;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];     // 행
            c = cur[1];     // 열
            m = cur[2];     // 오작교 설치 확인
            prev = cur[3];  // 이전에 오작교 건넜는지
            time = cur[4];  // 이동 시간

            if(r == N-1 && c == N-1) {
                // 직녀한테 도착. 최소 시간 갱신
                minTime = Math.min(minTime, time);
                continue;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc)) continue;
                if(visited[m][nr][nc] != -1 && visited[m][nr][nc] <= time+1) continue;

                if(map[nr][nc] == 1) { // 길
                    visited[m][nr][nc] = time+1;
                    q.offer(new int[] {nr, nc, m, 0, time+1});
                }
                else { // 절벽
                    if(prev == 1) continue; // 이전에 건넘

                    int nextTime = time + 1;
                    int nm = m;
                    // 오작교 주기 = 예정 없는 절벽이면 M, 아니면 예정된 주기
                    int bridge = map[nr][nc] == CLIFF ? M : map[nr][nc];

                    // 오작교를 지을 예정이 없는 절벽
                    if(map[nr][nc] == CLIFF) {
                        // 오작교 설치했거나 교차 지점이거나 이전에 건넜는지 확인
                        if(m > 0 || isCross[nr][nc]) continue;
                        nm = 1; // 오작교 설치
                    }

                    // 바로 갈 수 있는지 확인. 못가면 다음 주기로 변경
                    if(nextTime % bridge != 0) {
                        nextTime = (nextTime / bridge) * bridge + bridge;
                    }

                    q.offer(new int[] {nr, nc, nm, 1, nextTime});
                    visited[nm][nr][nc] = nextTime;
                }
            }
        }

        return minTime;
    }//getTime

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 행과 열의 크기
        M = Integer.parseInt(st.nextToken()); // 오작교의 주기

        map = new int[N][N]; // 지형
        isCross = new boolean[N][N]; // 교차 확인

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 절벽 교차 체크
        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(map[r][c] == CLIFF) {
                    int[] cnt = new int[2];

                    for(int k=0; k<4; k++) {
                        int nr = r + dr[k];
                        int nc = c + dc[k];
                        if(rangeCheck(nr, nc)) continue;
                        if(map[nr][nc] == CLIFF || map[nr][nc] > 1) cnt[k%2]++;
                    }

                    if(cnt[0] > 0 && cnt[1] > 0) isCross[r][c] = true;
                }
            }
        }

        br.close();
    }//init


}//class