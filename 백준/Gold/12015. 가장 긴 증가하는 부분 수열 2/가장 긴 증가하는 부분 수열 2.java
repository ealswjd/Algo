import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12015
public class Main {
    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int max = getMax();
        System.out.print(max);
    }//main

    private static int getMax() {
        int cnt = 0;
        int idx = 0;

        for(int i=0; i<N; i++) {
            if(arr[i] > dp[cnt]) {
                dp[++cnt] = arr[i];
                continue;
            }

            idx = binarySearch(cnt, arr[i]);
            dp[idx] = arr[i];
        }//for

        return cnt;
    }//getMax

    private static int binarySearch(int end, int n) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start+end)/2;
            if(dp[mid] >= n) end = mid;
            else start = mid+1;
        }

        return end;
    }//binarySearch

}//class