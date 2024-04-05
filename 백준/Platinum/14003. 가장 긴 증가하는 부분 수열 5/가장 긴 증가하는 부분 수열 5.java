import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14003
public class Main {
    static final int VALUE=0, CNT=1, MIN = -1_000_000_001;
    static int N;
    static int[][] arr;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i][VALUE] = Integer.parseInt(st.nextToken());
        }
        br.close();

        int max = getMax();
        print(max);
    }//main

    private static void print(int max) {
        StringBuilder ans = new StringBuilder();
        ans.append(max).append('\n');

        Stack<Integer> stack = new Stack<>();
        int cnt = max;

        for(int i=N-1; i>=0 && cnt > 0; i--) {
            if(arr[i][CNT] == cnt) {
                stack.add(arr[i][VALUE]);
                cnt--;
            }
        }

        while(!stack.isEmpty()) {
            ans.append(stack.pop()).append(' ');
        }

        System.out.print(ans);
    }//print

    private static int getMax() {
        int cnt = 0;
        int idx;

        for(int i=0; i<N; i++) {
            if(arr[i][VALUE] > dp[cnt]) {
                dp[++cnt] = arr[i][VALUE];
                arr[i][CNT] = cnt;
                continue;
            }

            idx = binarySearch(cnt, arr[i][VALUE]);
            dp[idx] = arr[i][VALUE];
            arr[i][CNT] = idx;
        }//for

        return cnt;
    }//getMax

    private static int binarySearch(int end, int n) {
        int start = 0;
        int mid;

        while(start < end) {
            mid = (start+end) / 2;
            if(dp[mid] >= n) end = mid;
            else start = mid+1;
        }

        return end;
    }//binarySearch

    private static void init() {
        arr = new int[N][2];
        dp = new int[N+1];
        Arrays.fill(dp, MIN);
    }//init

}//class