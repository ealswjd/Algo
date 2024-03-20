import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1365
public class Main {
    static int N;
    static int[] line, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        line = new int[N];
        dp = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            line[i] = Integer.parseInt(st.nextToken());
        }//for
        br.close();

        int max = getMax();
        System.out.print(N - max);
    }//main

    private static int getMax() {
        int cnt = 0;
        int idx = 0;

        for(int i=0; i<N; i++) {
            if(line[i] > dp[cnt]) {
                cnt++;
                dp[cnt] = line[i];
                continue;
            }//if

            idx = binarySearch(cnt, line[i]);
            dp[idx] = line[i];
        }//for

        return cnt;
    }//getMax

    private static int binarySearch(int end, int target) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start + end) / 2;
            if(dp[mid] > target) end = mid;
            else start = mid+1;
        }//while

        return end;
    }//binarySearch

}//class