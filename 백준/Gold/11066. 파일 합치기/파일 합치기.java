import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 파일 합치기 (골드 3)
 * 링크 : https://www.acmicpc.net/problem/11066
 * */
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int K;
    static int[] sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        while(T-->0) {
            K = Integer.parseInt(br.readLine());
            sum = new int[K+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=K; i++) {
                sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
            }//for

            ans.append(getMinCost()).append('\n');
        }//while

        System.out.print(ans);
    }//main

    private static int getMinCost() {
        int[][] dp = new int[K+1][K+1];

        for(int k=1; k<K; k++) {
            for(int s=1; s<=K-k; s++) { // 범위 시작
                int e = s+k; // 범위 종료
                dp[s][e] = INF; // s ~ e 최소 비용 초기화
                for(int m=s; m<e; m++) { // 범위 중간
                    dp[s][e] = Math.min(dp[s][e], dp[s][m] + dp[m+1][e] + sum[e] - sum[s-1]);
                }//for m
            }//for s
        }//for k

        return dp[1][K];
    }//getMinCost

}//class