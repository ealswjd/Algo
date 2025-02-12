import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12014
public class Main {
    private static final String TITLE = "Case #";
    private static int N, K; // 주가를 알 수 있는 날 수, 거래의 회수
    private static int[] price; // 주가


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        StringBuilder ans = new StringBuilder();
        int result;

        for(int t=1; t<=T; t++) {
            init(br);
            result = getResult();

            ans.append(TITLE).append(t).append("\n");
            ans.append(result).append("\n");
        }

        System.out.print(ans);
    }//main


    private static int getResult() {
        int[] dp = new int[N+1];
        int cnt = 0;
        int idx;

        for(int i=0; i<N; i++) {
            if(price[i] > dp[cnt]) {
                dp[++cnt] = price[i];
                continue;
            }

            idx = getIndex(price[i], cnt, dp);
            dp[idx] = price[i];
        }

        return cnt >= K ? 1 : 0;
    }//getResult


    private static int getIndex(int target, int end, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] >= target) end = mid;
            else start = mid + 1;
        }

        return end;
    }//getIndex


    private static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 앞으로 주가를 알 수 있는 날 수
        K = Integer.parseInt(st.nextToken()); // 거래의 회수

        price = new int[N]; // 주가

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
    }//init


}//class