import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1932
public class Main {
    static int N;
    static int[][] triangle; // 삼각형
    static int[][] dp; // 합

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<i+1; j++) {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        br.close();

        int max = getMax(0, 0);
        System.out.print(max);
    }//main

    
    private static int getMax(int r, int c) {
        if(r == N-1) return triangle[r][c];

        if(dp[r][c] == -1) {
            // 왼, 오 비교
            dp[r][c] = Math.max(getMax(r+1, c), getMax(r+1, c+1)) + triangle[r][c];
        }

        return dp[r][c];
    }//getMax

    
    private static void init() {
        triangle = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }//init

    
}//class