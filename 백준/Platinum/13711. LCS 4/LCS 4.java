import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/13711
public class Main {
    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        init();

        int[] A = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            A[Integer.parseInt(st.nextToken())] = i;
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = A[Integer.parseInt(st.nextToken())];
        }

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int cnt = 0;
        int idx;

        for(int i=1; i<=N; i++) {
            if(arr[i] > dp[cnt]) {
                cnt++;
                dp[cnt] = arr[i];
                continue;
            }

            idx = binarySearch(cnt, arr[i]);
            dp[idx] = arr[i];
        }//for

        return cnt;
    }//getMax

    private static int binarySearch(int end, int target) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start+end)/2;
            if(dp[mid] > target) end = mid;
            else start = mid+1;
        }//while

        return end;
    }//binarySearch


    private static void init() {
        arr = new int[N+1];
        dp = new int[N+1];
    }//init

}//class