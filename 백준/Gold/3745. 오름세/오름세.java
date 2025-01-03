import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3745
public class Main {
    private static int N;
    private static int[] P; // N일 동안의 주가


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        StringTokenizer st;
        String input;

        while((input = br.readLine()) != null) {
            input = input.trim();
            if(input.isEmpty()) break;

            N = Integer.parseInt(input.trim());
            P = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                P[i] = Integer.parseInt(st.nextToken());
            }

            int length = getMaxLength();
            ans.append(length).append('\n');
        }

        System.out.print(ans);
    }//main


    private static int getMaxLength() {
        int cnt = 0;
        int idx = 0;
        int[] dp = new int[N+1];

        for(int i=0; i<N; i++) {
            if(P[i] > dp[cnt]) {
                dp[++cnt] = P[i];
                continue;
            }

            idx = getIndex(cnt, P[i], dp);
            dp[idx] = P[i];
        }

        return cnt;
    }//getMaxLength


    private static int getIndex(int end, int target, int[] dp) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;

            if(dp[mid] < target) start = mid + 1;
            else end = mid;
        }

        return end;
    }//getIndex


}//class