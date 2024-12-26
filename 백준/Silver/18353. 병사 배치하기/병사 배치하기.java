import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/18353
public class Main {
    private static final int INF = 10_000_001;
    private static int N;
    private static int[] powers;


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(N - max);
    }//main


    private static int getMax() {
        int[] dp = new int[N+1];
        int cnt = 0;
        dp[0] = INF;

        for(int i=0; i<N; i++) {
            if(dp[cnt] > powers[i]) {
                dp[++cnt] = powers[i];
                continue;
            }

            int idx = getIndex(cnt, powers[i], dp);
            dp[idx] = powers[i];
        }

        return cnt;
    }//getMax


    private static int getIndex(int end, int target, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] < target) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        powers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            powers[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }//init


}//class