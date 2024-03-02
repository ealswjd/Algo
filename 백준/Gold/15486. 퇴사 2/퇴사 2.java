import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15486
public class Main {
    static final int T=0, P=1;
    static int N;
    static int[][] info;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        info = new int[N+1][2];
        dp = new int[N+2];

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][T] = Integer.parseInt(st.nextToken()); // 상담을 완료하는데 걸리는 기간
            info[i][P] = Integer.parseInt(st.nextToken()); // 상담을 했을 때 받을 수 있는 금액
        }//for
        br.close();

        getMax();
        System.out.print(dp[1]);
    }//main

    private static void getMax() {
        int next;
        for(int i=N; i>0; i--) {
            next = i + info[i][T];
            if(next > N+1) dp[i] = dp[i+1];
            else dp[i] = Math.max(dp[i+1], dp[next] + info[i][P]);
        }//for
    }//getMax

}//class