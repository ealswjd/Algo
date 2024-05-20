import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11054
public class Main {
    static int N;
    static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int ans = getMax();
        System.out.print(ans);
    }//main

    private static int getMax() {
        int[][] dp = new int[N+1][2]; // [N+1][증가, 감소]
        int max = 1;

        for(int i=1; i<=N; i++) {
            dp[i][0] = dp[i][1] = 1;
            for(int j=1; j<i; j++) {
                if(A[i] > A[j]) { // 증가
                    dp[i][0] = Math.max(dp[i][0], dp[j][0] + 1);
                    max = Math.max(max, dp[i][0]);
                }else if(A[i] < A[j]) { // 감소
                    dp[i][1] = Math.max(dp[i][1], Math.max(dp[j][0], dp[j][1]) + 1);
                    max = Math.max(max, dp[i][1]);
                }
            }
        }

        return max;
    }//getMax

}//main