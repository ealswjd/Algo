import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 https://www.acmicpc.net/problem/30621
 대회장에 줄 수 있는 최종 혼란의 최댓값
 */
public class Main {
    private static int N; // '어?'를 외칠 수 있는 시각의 개수
    private static int[] t, b, c; // '어?'를 외칠 수 있는 시각, 이전 시각, 혼란


    public static void main(String[] args) throws IOException {
        init();

        long max = getMax();
        System.out.print(max);
    }//main


    private static long getMax() {
        long[] dp = new long[N];
        dp[0] = c[0];
        int prevIdx, target;

        for(int i=1; i<N; i++) {
            target = t[i] - b[i];
            prevIdx = getPrevIndex(i, target);

            dp[i] = Math.max(dp[i - 1], c[i]);
            if(prevIdx >= 0) dp[i] = Math.max(dp[i], dp[prevIdx] + c[i]);
        }

        return dp[N - 1];
    }//getMax


    private static int getPrevIndex(int end, int target) {
        int idx = -1;
        int start = 0;
        int mid;

        while(start <= end) {
            mid = (start + end) / 2;

            if(t[mid] < target) {
                start = mid + 1;
                idx = mid;
            }
            else {
                end = mid - 1;
            }
        }

        return idx;
    }//getPrevIndex


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        t = input(br); // 시각
        b = input(br); // 이전 시각
        c = input(br); // 혼란

        br.close();
    }//init


    private static int[] input(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        return arr;
    }//input


}//class