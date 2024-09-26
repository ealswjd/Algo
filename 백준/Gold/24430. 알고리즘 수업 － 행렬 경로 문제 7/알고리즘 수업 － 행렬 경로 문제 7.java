import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/24430
 알고리즘 수업 - 행렬 경로 문제 7
*/
public class Main {
    static int N;
    static int[][] map;
    static int[][] dp;
    static int[][] P;

    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int r=1; r<=N; r++) {
            st = new StringTokenizer(br.readLine());
            for(int c=1; c<=N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        int pCnt = Integer.parseInt(br.readLine());
        while(pCnt-- > 0) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            P[r][c] = 1;
        }

        sol();
    }//main

    
    private static void sol() {
        StringBuilder ans = new StringBuilder();

        int maxScore = getMaxScore();     // 가장 높은 점수
        int maxCnt = getMaxCnt(); // 중간 원소 개수의 최댓값

        ans.append(maxScore).append(' ').append(maxCnt);
        System.out.print(ans);
    }//sol

    
    private static int getMaxScore() {

        for(int r=1; r<=N; r++) {
            for(int c=1; c<=N; c++) {
                dp[r][c] = Math.max(dp[r-1][c], dp[r][c-1]) + map[r][c];
            }
        }

        return dp[N][N];
    }//getMaxScore

    
    private static int getMaxCnt() {
        int[][] count = new int[N+1][N+1];
        boolean[][] checked = new boolean[N+1][N+1];
        checked[N][N] = true;

        for(int r=N; r>0; r--) {
            for(int c=N; c>0; c--) {
                if(r == N && c == N) continue;

                if(r+1 <= N && checked[r+1][c] && dp[r+1][c] - map[r+1][c] == dp[r][c]) {
                    count[r][c] = count[r+1][c] + P[r][c];
                    checked[r][c] = true;
                }
                if(c+1 <= N && checked[r][c+1] && dp[r][c+1] - map[r][c+1] == dp[r][c]) {
                    count[r][c] = Math.max(count[r][c], count[r][c+1] + P[r][c]);
                    checked[r][c] = true;
                }

            }
        }

        return count[1][1];
    }//getMaxCnt

    
    private static void init() {
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        P = new int[N+1][N+1];
    }//init

    
}//class