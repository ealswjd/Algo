import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/22869
public class Main {
    private static int N, K; // 돌의 개수, 쓸 수 있는 최대 힘
    private static int[] A; // 돌의 수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        boolean[] dp = new boolean[N+1]; // 이동 가능 여부 저장
        int power;

        dp[1] = true;

        for(int i=1; i<=N; i++) {
            if(!dp[i]) continue;

            for(int j=i+1; j<=N; j++) {
                // 이동할 때 쓰는 힘
                power = (j-i) * (1 + Math.abs(A[i] - A[j]));
                // 쓸 수 있는 최대 힘은 K
                if(power <= K) dp[j] = true;
            }
        }

        // 가장 오른쪽에 있는 돌로 이동할 수 있다면 YES 출력, 이동하지 못하면 NO 출력
        System.out.print(dp[N] ? "YES" : "NO");
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 돌의 개수
        K = Integer.parseInt(st.nextToken()); // 쓸 수 있는 최대 힘

        A = new int[N + 1]; // 돌의 수

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class