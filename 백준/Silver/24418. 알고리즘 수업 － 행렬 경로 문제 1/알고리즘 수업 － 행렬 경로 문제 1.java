import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24418
public class Main {
    static int N;
    static int cnt;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        getMax(N-1, N-1);

        System.out.println(cnt+1 + " " + N*N);
    }//main

    
    private static int getMax(int r, int c) {
        if(r < 0 || c < 0) return 0;

        cnt++;
        return map[r][c] + Math.max(getMax(r-1, c), getMax(r, c-1));
    }//getMax

    
}//class