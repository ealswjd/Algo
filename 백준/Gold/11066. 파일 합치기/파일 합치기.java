import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11066
// 파일들을 하나의 파일로 합칠 때 필요한 최소비용을 계산하는 프로그램
public class Main {
    private static final int INF = 987654321;
    private static int K; // 소설을 구성하는 장의 수
    private static int[] sum; // 파일 크기 누적합


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;

        while(T-- > 0) {
            K = Integer.parseInt(br.readLine()); // 소설을 구성하는 장의 수
            sum = new int[K+1]; // 파일 크기 누적합
            st = new StringTokenizer(br.readLine());

            for(int i=1; i<=K; i++) {
                sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
            }

            int minCost = getMin();
            ans.append(minCost).append('\n');
        }
        br.close();

        System.out.print(ans);
    }//sol


    private static int getMin() {
        int[][] dp = new int[K+1][K+1];

        for(int k=1; k<K; k++) { // 합치는 횟수
            for(int s=1; s<=K-k; s++) { // 범위 시작
                int e = s + k; // 범위 종료
                dp[s][e] = INF;

                for(int m=s; m<e; m++) { // 범위 중간
                    int cost = dp[s][m] + dp[m+1][e] + sum[e] - sum[s-1];
                    dp[s][e] = Math.min(dp[s][e], cost);
                }
            }
        }

        return dp[1][K];
    }//getMin


}//class