import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 나의 인생에는 수학과 함께 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/17265
 * */
public class Main {
    static int N, max, min;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {0, 1};
    static int[] dc = {1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = st.nextToken().charAt(0);
            }//for
        }//for
        br.close();

        visited = new boolean[N][N];
        visited[0][0] = true;
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        dfs(0, 0, map[0][0]-'0');

        StringBuilder ans = new StringBuilder();
        ans.append(max).append(' ').append(min);
        System.out.print(ans);
    }//main

    private static void dfs(int r, int c, int sum) {
        if(r == N-1 && c == N-1) {
            min = Math.min(min, sum);
            max = Math.max(max, sum);
            return;
        }//if

        int n;
        for(int i=0; i<2; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= N || nc >= N || visited[nr][nc]) continue;
            visited[nr][nc] = true;
            n = map[nr][nc] - '0';
            if(map[r][c] == '+') dfs(nr, nc, sum + n);
            else if(map[r][c] == '-') dfs(nr, nc, sum - n);
            else if(map[r][c] == '*') dfs(nr, nc, sum * n);
            else dfs(nr, nc, sum);
            visited[nr][nc] = false;
        }//for

    }//dfs

}//class