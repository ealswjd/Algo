import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12785
public class Main {
    private static final int MOD = 1_000_007;
    private static int R, C, tr, tc;


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int toast = getCnt(1, 1, tr, tc, 1);
        int result = getCnt(tr, tc, R, C, toast);

        System.out.print(result);
    }//sol


    private static int getCnt(int sr, int sc, int er, int ec, int cnt) {
        int[][] dp = new int[R+1][C+1];
        int fromTop, fromLeft, sum;
        dp[sr][sc] = cnt;

        for(int r=sr; r<=er; r++) {
            for(int c=sc; c<=ec; c++) {
                if(r == sr && c == sc) continue;

                fromTop = dp[r-1][c];
                fromLeft = dp[r][c-1];

                sum = (fromTop + fromLeft) % MOD;

                dp[r][c] = sum;
            }
        }

        return dp[er][ec];
    }//getCnt


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시의 크기가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 토스트 가게의 좌표가 주어진다.
        st = new StringTokenizer(br.readLine());
        tr = Integer.parseInt(st.nextToken());
        tc = Integer.parseInt(st.nextToken());

        br.close();
    }//init
    

}//class