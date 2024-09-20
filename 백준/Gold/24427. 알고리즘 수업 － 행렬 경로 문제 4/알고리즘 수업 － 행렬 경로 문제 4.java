import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/24427
public class Main {
    static int N; // 행렬의 크기
    static boolean[][] checked; // 중간 원소
    static int[][] map; // 행렬
    static int[][] dp; // 최대 점수

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();
        map = new int[N][N];

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int P = Integer.parseInt(br.readLine());
        while(P-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            checked[r][c] = true;
        }

        int maxScore = getMaxScore();
        System.out.print(maxScore);
    }//main


    private static int getMaxScore() {
        int[][] fromP = new int[N][N]; // 중간 원소 거친 최대 점수 저장
        dp[0][0] = map[0][0];

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {

                int fromTop = r > 0 ? dp[r-1][c] : 0; // 위쪽에서
                int fromLeft = c > 0 ? dp[r][c-1] : 0; // 왼쪽에서

                dp[r][c] = map[r][c] + Math.max(fromTop, fromLeft);
                if(checked[r][c]) fromP[r][c] = dp[r][c];
            }
        }

        for(int r=0; r<N; r++) {
            for(int c=0; c<N; c++) {
                if(r>0 && checked[r-1][c]) {
                    fromP[r][c] = Math.max(fromP[r][c], fromP[r-1][c] + map[r][c]);
                    checked[r][c] = true;
                }
                if(c>0 && checked[r][c-1]) {
                    fromP[r][c] = Math.max(fromP[r][c], fromP[r][c-1] + map[r][c]);
                    checked[r][c] = true;
                }
            }
        }

        return fromP[N-1][N-1];
    }//getMaxScore


    private static void init() {
        map = new int[N][N];
        dp = new int[N][N];
        checked = new boolean[N][N];
    }//init

    
}//class