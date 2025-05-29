import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/27971
public class Main {
    private static final int INF = 987654321;
    private static int N, A, B; // 키우기를 원하는 강아지의 수, A마리, B마리 생성 마법
    private static int[] dp; // 최소의 행동 횟수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        int ans = -1;
        int a, b;

        dp[0] = 0;

        for(int i=1; i<=N; i++) {
            if(dp[i] == -1) continue;

            a = i - A;
            b = i - B;

            if(a >= 0 && dp[a] != -1) {
                dp[i] = Math.min(dp[i], dp[a] + 1);
            }
            if(b >= 0 && dp[b] != -1) {
                dp[i] = Math.min(dp[i], dp[b] + 1);
            }
        }

        if(dp[N] != INF) ans = dp[N];
        System.out.print(ans);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 키우기를 원하는 강아지의 수
        int M = Integer.parseInt(st.nextToken()); // 닫힌구간의 개수
        A = Integer.parseInt(st.nextToken()); // 강아지 A마리를 호무라의 집에 생성
        B = Integer.parseInt(st.nextToken()); // 강아지 B마리를 호무라의 집에 생성

        dp = new int[N+1];
        Arrays.fill(dp, INF);

        int l, r;
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            l = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());

            for(int i=l; i<=r; i++) {
                dp[i] = -1;
            }
        }

        br.close();
    }//init
    

}//class