import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/16174
public class Main {
    private static int N; // 게임 구역의 크기
    private static int[][] map; // 게임 구역
    private static boolean[][] visited; // 방문체크


    public static void main(String[] args) throws IOException {
        init();

        System.out.print(dfs(0, 0) ? "HaruHaru" : "Hing");
    }//main


    private static boolean dfs(int r, int c) {
        if(rangeCheck(r, c) || visited[r][c]) return false;
        if(map[r][c] == -1) return true;

        int cnt = map[r][c]; // 이동할 수 있는 칸의 수
        visited[r][c] = true;

        return dfs(r + cnt, c) || dfs(r, c + cnt);
    }//dfs


    private static boolean rangeCheck(int r, int c) {
        return r >= N || c >= N;
    }//rangeCheck


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 게임 구역의 크기

        map = new int[N][N]; // 게임 구역
        visited = new boolean[N][N]; // 방문 체크

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class