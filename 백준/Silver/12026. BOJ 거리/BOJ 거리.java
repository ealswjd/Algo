import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// https://www.acmicpc.net/problem/12026
public class Main {
    private static final int B = 0, O = 1, J = 2, INF = 1_000_001;
    private static int N; // 보도블록 개수
    private static int[] boj; // BOJ거리의 각 보도블록


    public static void main(String[] args) throws IOException{
        init();
        sol();
    }//main


    private static void sol() {
        int[] dp = new int[N]; // 에너지 양의 최솟값
        int result = -1;

        Arrays.fill(dp, INF);
        dp[0] = 0;

        int cur, prev, k;
        for(int i=1; i<N; i++) {
            cur = boj[i];
            prev = cur == B ? J : cur - 1;
            for(int j=i-1; j>=0; j--) {
                if(boj[j] != prev) continue;

                // k칸 만큼 점프를 하는데 필요한 에너지의 양은 k*k
                k = i - j;
                dp[i] = Math.min(dp[i], dp[j] + k * k);
            }
        }

        /* 
         필요한 에너지 양의 최솟값을 출력한다. 
         만약, 스타트가 링크를 만날 수 없는 경우에는 -1을 출력
         */
        if(dp[N-1] != INF) result = dp[N-1];
        System.out.print(result);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 보도블록 개수

        boj = new int[N]; // BOJ거리의 각 보도블록

        char[] bojInput = br.readLine().toCharArray();
        for(int i=0; i<N; i++) {
            if(bojInput[i] == 'O') boj[i] = O;
            else if(bojInput[i] == 'J') boj[i] = J;
        }

        br.close();
    }//init


}//class