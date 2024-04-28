import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/29704
public class Main {
    static final int D=0, M=1;
    static int N, T;
    static int[][] homeworks;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 문제의 개수
        T = Integer.parseInt(st.nextToken()); // 남은 제출 기한
        homeworks = new int[N][2];

        int total = 0; // 벌금 총액
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            homeworks[i][D] = Integer.parseInt(st.nextToken());
            homeworks[i][M] = Integer.parseInt(st.nextToken());

            total += homeworks[i][M];
        }
        br.close();

        int min = getMin(total);
        System.out.print(min);
    }//main

    private static int getMin(int total) {
        int[] dp = new int[T+1];

        for(int[] homework : homeworks) {
            for(int t=T; t>=homework[D]; t--) {
                dp[t] = Math.max(dp[t], dp[t-homework[D]] + homework[M]);
            }
        }

        return total - dp[T];
    }//getMin

}//class