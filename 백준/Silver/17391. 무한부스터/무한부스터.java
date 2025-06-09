import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17391
public class Main {
    private static int N, M; // 맵의 세로 길이와 가로 길이
    private static int[][] map; // 카트라이더 맵
    

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int max = N * M;
        Queue<int[]> q = new LinkedList<>();
        int[][] visited = new int[N][M];
        int r = 0, c = 0, cnt = 0;

        for(int i=0; i<N; i++) {
            Arrays.fill(visited[i], max);
        }

        q.offer(new int[] {r, c, cnt});
        visited[r][c] = cnt;

        int[] cur;
        int booster;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0]; // 행
            c = cur[1]; // 열
            cnt = cur[2]; // 아이템 획득한 횟수
            booster = map[r][c]; // 부스터 아이템 개수

            // 도착지점
            if(r == N-1 && c == M-1) {
                break;
            }

            int nr, nc;
            for(int x=1; x<=booster; x++) {
                // 아래
                nr = r + x;
                if(rangeCheck(nr, c) && visited[nr][c] > cnt + 1) {
                    visited[nr][c] = cnt + 1;
                    q.offer(new int[] {nr, c, cnt + 1});
                }

                // 오른쪽
                nc = c + x;
                if(rangeCheck(r, nc) && visited[r][nc] > cnt + 1) {
                    visited[r][nc] = cnt + 1;
                    q.offer(new int[] {r, nc, cnt + 1});
                }
            }
        }

        System.out.print(visited[N-1][M-1]);
    }//sol


    private static boolean rangeCheck(int r, int c) {
        return r < N && c < M;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 맵의 세로 길이와 가로 길이를 나타내는 양의 정수 N과 M
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class