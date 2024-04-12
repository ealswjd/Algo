import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17845
public class Main {
    static final int SCORE=0, TIME=1;
    static int N, K;
    static int[][] subjects, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 최대 공부시간
        K = Integer.parseInt(st.nextToken()); // 과목 수

        init();

        for(int i=1; i<=K; i++) {
            st = new StringTokenizer(br.readLine());
            subjects[i][SCORE] = Integer.parseInt(st.nextToken());
            subjects[i][TIME] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int max = getMax();
        System.out.println(max);
    }//main

    
    private static int getMax() {

        for(int i=1; i<=K; i++) {
            for(int t=1; t<=N; t++) {
                if(subjects[i][TIME] > t) dp[i][t] = dp[i-1][t];
                else dp[i][t] = Math.max(dp[i-1][t], 
                                         dp[i-1][t-subjects[i][TIME]] + subjects[i][SCORE]);
            }
        }

        return dp[K][N];
    }//getMax

    
    private static void init() {
        subjects = new int[K+1][2];
        dp = new int[K+1][N+1];
    }//init

}//class