import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/15724
 주지수
 */
public class Main {
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        init(br);

        int K = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r1 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            int sum = map[r2][c2] - map[r1-1][c2] - map[r2][c1-1] + map[r1-1][c1-1];

            ans.append(sum).append('\n');
        }

        br.close();

        System.out.print(ans);
    }//main

    
    private static void init(BufferedReader br) throws IOException  {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];

        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=M; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                map[r][c] += map[r-1][c] + map[r][c-1] - map[r-1][c-1];
            }
        }
    }//init


}//class