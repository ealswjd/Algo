import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/22871
 징검다리 건너기 (large)
 */
public class Main {
    private static final long MAX = Long.MAX_VALUE;
    private static int N; // 돌의 개수
    private static long[] A; // 돌의 수


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main


    private static void sol() {
        long[] dp = new long[N]; // 힘의 최솟값
        long power; // 이동할 때 쓰는 힘

        for(int j=1; j<N; j++) {
            dp[j] = MAX;

            for(int i=j-1; i>=0; i--) {
                // i번째 돌에서 j번째 돌로 이동할 때 쓰는 힘
                power = (j - i) * (1 + Math.abs(A[i] - A[j]));
                // 이동할 수 있는 힘의 최솟값 갱신
                dp[j] = Math.min(dp[j], Math.max(dp[i], power));
            }
        }

        // 가장 왼쪽 돌 출발 -> 가장 오른쪽에 있는 돌로 건너갈 수 있는 최대힘의 최솟값
        System.out.print(dp[N-1]);
    }//sol


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 돌의 개수

        A = new long[N]; // 돌의 수

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class