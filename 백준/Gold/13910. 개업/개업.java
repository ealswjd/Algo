import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13910
public class Main {
    private static final int MAX = 10_001;
    private static int N, M; // 주문 받은 짜장면의 수, 웍의 개수
    private static Set<Integer> size; // 웍 크기들


    public static void main(String[] args) throws IOException {
        init();

        int minCnt = getMin();
        System.out.print(minCnt);
    }//main


    private static int getMin() {
        int[] dp = new int[N+1];

        Arrays.fill(dp, MAX);
        dp[0] = 0;

        for(int s : size) {
            for(int i=s; i<=N; i++) {
                dp[i] = Math.min(dp[i], dp[i-s] + 1);
            }
        }

        return dp[N] == MAX ? -1 : dp[N];
    }//getMin


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 주문 받은 짜장면의 수
        M = Integer.parseInt(st.nextToken()); // 가지고 있는 웍의 개수

        int[] woks = new int[M]; // 웍의 크기
        size = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            woks[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            size.add(woks[i]);
            for(int j=i+1; j<M; j++) {
                int sum = woks[i] + woks[j];
                size.add(sum);
            }
        }

        br.close();
    }//init


}//class