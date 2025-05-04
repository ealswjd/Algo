import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14925
public class Main {
    private static int N, M; // 땅의 크기
    private static int[][] map; // 땅의 지도


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int[][] len = new int[N+1][M+1];
        int max = 0;
        int top, left;

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=M; c++) {
                if(map[r][c] > 0) continue;

                top = len[r-1][c];
                left = len[r][c-1];
                len[r][c] = Math.min(len[r-1][c-1], Math.min(top, left)) + 1;
                max = Math.max(max, len[r][c]);
            }
        }

        System.out.print(max);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1]; // 땅의 지도

        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }//init


}//class