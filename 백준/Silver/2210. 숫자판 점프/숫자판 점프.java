import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2210
public class Main {
    static final int N = 5, CNT=6;
    static int[][] map;
    static Set<Integer> set;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }//for
        }//for
        br.close();

        set = new HashSet<>();

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                dfs(r, c, 0, map[r][c] * 100000, 10000);
            }//for
        }//for

        System.out.print(set.size());
    }//main

    private static void dfs(int r, int c, int cnt, int result, int M) {
        if(cnt == CNT) {
            set.add(result);
            return;
        }//if

        int nr, nc;
        for(int i=0; i<4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(rangeCheck(nr, nc)) continue;
            dfs(nr, nc, cnt+1, result + map[nr][nc]*M, M/10);
        }//for
    }//dfs

    private static boolean rangeCheck(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

}//class