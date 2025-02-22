import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/23747
public class Main {
    private static final int[] dr = {-1, 1, 0, 0};
    private static final int[] dc = {0, 0, -1, 1};
    private static int R, C; // 격자의 크기를 나타내는 두 정수
    private static int hr, hc; // 한별이 위치
    private static int[][] map; // 격자
    private static boolean[] W; // 와드
    private static boolean[][] checked; // 여행 결과
    private static int[] order; // 한별이의 여행 기록


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main


    private static void solution() {

        for(int d : order) {
            if(d == 4) { // 와드
                int n = map[hr][hc];
                W[n] = true;
            }
            else { // 방향 이동
                hr += dr[d];
                hc += dc[d];
            }
        }

        int n;
        for(int i=0; i<R; i++) {
            for (int j=0; j<C; j++) {
                n = map[i][j];
                if(W[n]) bfs(i, j, n, checked, map);
            }
        }

        // 현재 위치 시야 확보
        checked[hr][hc] = true;

        for(int i=0; i<4; i++) {
            int nr = hr + dr[i];
            int nc = hc + dc[i];
            if(rangeCheck(nr, nc)) continue;
            checked[nr][nc] = true;
        }

        print();
    }//solution


    private static void bfs(int r, int c, int num, boolean[][] visited, int[][] arr) {
        int area = arr[r][c];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {r, c});
        visited[r][c] = true;

        int[] cur;
        while(!q.isEmpty()) {
            cur = q.poll();
            r = cur[0];
            c = cur[1];

            map[r][c] = num;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(rangeCheck(nr, nc) || visited[nr][nc]) continue;
                if(arr[nr][nc] != area) continue;

                visited[nr][nc] = true;
                q.offer(new int[] {nr, nc});
            }
        }

    }


    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= R || c < 0 || c >= C;
    }//rangeCheck


    private static void print() {
        StringBuilder ans = new StringBuilder();

        for(int r=0; r<R; r++) {
            for(int c=0; c<C; c++) {
                if(checked[r][c]) ans.append('.');
                else ans.append('#');
            }
            ans.append('\n');
        }

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 격자의 크기를 나타내는 두 정수
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C]; // 격자
        checked = new boolean[R][C]; // 여행 결과
        int[][] tmp = new int[R][C];

        // 격자의 정보가 주어진다.
        for(int i=0; i<R; i++) {
            char[] chars = br.readLine().toCharArray();
            for(int j=0; j<C; j++) {
                tmp[i][j] = chars[j];
            }
        }

        // 한별이가 이세계에 떨어진 위치를 나타내는 두 정수
        st = new StringTokenizer(br.readLine());
        hr = Integer.parseInt(st.nextToken()) - 1;
        hc = Integer.parseInt(st.nextToken()) - 1;

        // 한별이의 여행 기록
        char[] tmpOrder = br.readLine().toCharArray();
        order = new int[tmpOrder.length];

        for(int i=0; i<tmpOrder.length; i++) {
            if(tmpOrder[i] == 'W') order[i] = 4;
            else if(tmpOrder[i] == 'D') order[i] = 1;
            else if(tmpOrder[i] == 'L') order[i] = 2;
            else if(tmpOrder[i] == 'R') order[i] = 3;
        }

        boolean[][] visited = new boolean[R][C];
        int cnt = 0;
        for(int i=0; i<R; i++) {
            for(int j=0; j<C; j++) {
                if(visited[i][j]) continue;
                bfs(i, j, cnt, visited, tmp);
                cnt++;
            }
        }

        W = new boolean[cnt];

        br.close();
    }//init


}//class