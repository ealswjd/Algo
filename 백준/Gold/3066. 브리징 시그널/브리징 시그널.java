import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/3066
public class Main {
    private static int N;
    private static int[] port;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(T-- > 0) {
            init(br);

            int cnt = getCnt();
            ans.append(cnt).append('\n');
        }

        System.out.print(ans);
    }//main


    private static int getCnt() {
        int cnt = 0;
        int idx = 0;
        int[] dp = new int[N+1];

        for(int i=0; i<N; i++) {
            if(port[i] > dp[cnt]) {
                dp[++cnt] = port[i];
                continue;
            }

            idx = getIndex(cnt, port[i], dp);
            dp[idx] = port[i];
        }

        return cnt;
    }//getCnt


    private static int getIndex(int end, int target, int[] dp) {
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
        N = Integer.parseInt(br.readLine());
        port = new int[N];

        for(int i=0; i<N; i++) {
            port[i] = Integer.parseInt(br.readLine());
        }
    }//init


}//class