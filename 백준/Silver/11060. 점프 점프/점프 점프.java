import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 11111;
    static int N;
    static int[] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        dp = new int[N];
        for(int i=0; i<N; i++){
            map[i] = Integer.parseInt(st.nextToken());
            dp[i] = INF;
        }//for
        br.close();

        dp[0] = 0;
        int jump, cnt;
        for(int i=0; i<N; i++) {
            jump = map[i];
            cnt = dp[i] + 1;
            for(int j=jump+i; j>i; j--) {
                if(j < N) dp[j] = Math.min(dp[j], cnt);
            }
        }//for

        if(dp[N-1] == INF) System.out.print(-1);
        else System.out.print(dp[N-1]);
    }//main

}//class