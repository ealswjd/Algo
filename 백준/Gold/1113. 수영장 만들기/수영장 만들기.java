import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1113
public class Main {
    private static int N, M; // 행, 열
    private static int[][] map; // 수영장
    private static boolean[][] checked; // 물 채웠는지 확인
    private static int sum, max, min;
    private static boolean flag;
    private static int[] dr = {1, 0, -1, 0};
    private static int[] dc = {0, 1, 0, -1};

    
    public static void main(String[] args) throws IOException {
        init();
        getResult();
    }//main

    
    private static void getResult() {
        int total = 0;

        for(int h=max; h>=min; h--) {
            for(int r=1; r<N-1; r++) {
                for(int c=1; c<M-1; c++) {
                    if(checked[r][c]) continue;
                    if(map[r][c] >= h) continue;

                    sum = 0; // 채우는 물
                    flag = true;
                    fill(r, c, h, new boolean[N][M]); // 물 채우기

                    if(flag) { // 수영장 채울 수 있음
                        total += sum;
                        waterCheck(r, c, h);
                    }

                }
            }
        }

        System.out.print(total);
    }//getResult

    
    private static void waterCheck(int r, int c, int water) {
        checked[r][c] = true;

        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(rangeCheck(nr, nc) || checked[nr][nc]) continue;

            if(map[nr][nc] < water) {
                waterCheck(nr, nc, water);
            }
        }
    }//waterCheck

    
    private static void fill(int r, int c, int water, boolean[][] visited) {
        if(!flag) return;

        visited[r][c] = true;
        sum += water - map[r][c];

        for(int i=0; i<4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];

            // 수영장 뚫림
            if(rangeCheck(nr, nc)) { 
                flag = false;
                return;
            }

            if(map[nr][nc] < water && !visited[nr][nc]) {
                fill(nr, nc, water, visited);
                if(!flag) return;
            }
        }
    }//fill

    
    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= M;
    }//rangeCheck

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        checked = new boolean[N][M];
        min = 9;

        for(int i=0; i<N; i++) {
            char[] row = br.readLine().toCharArray();
            for(int j=0; j<M; j++) {
                map[i][j] = row[j] - '0';
                max = Math.max(max, map[i][j]);
                min = Math.min(min, map[i][j]);
            }
        }
    }//init

    
}//class