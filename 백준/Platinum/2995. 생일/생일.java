import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/2995
public class Main {
    private static final int MAX = 1_000_001;
    private static int N; // 구간의 수
    private static Pair[] pairs; // 구간
    private static int[] length; // 길이


    public static void main(String[] args) throws IOException {
        init();
        solution();
    }//main


    private static void solution() {
        StringBuilder ans = new StringBuilder();

        int max = getMax();
        ans.append(max).append('\n');

        Stack<Integer> stack = new Stack<>();
        int cnt = max;

        for(int i=N-1; i>=0 && cnt > 0; i--) {
            if(length[i] == cnt) {
                stack.add(i);
                cnt--;
            }
        }

        while(!stack.isEmpty()) {
            int idx = stack.pop();
            ans.append(pairs[idx]).append('\n');
        }

        System.out.print(ans);
    }//solution


    private static int getMax() {
        int[] dp = new int[N+1];
        int cnt = 0;
        int idx;
        dp[0] = MAX;

        for(int i=0; i<N; i++) {
            if(pairs[i].e <= dp[cnt]) {
                dp[++cnt] = pairs[i].e;
                length[i] = cnt;
                continue;
            }

            idx = getIndex(pairs[i].e, cnt, dp);
            dp[idx] = pairs[i].e;
            length[i] = idx;
        }

        return cnt;
    }//getMax


    private static int getIndex(int target, int end, int[] dp) {
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
        N = Integer.parseInt(br.readLine()); // 구간의 수

        pairs = new Pair[N]; // 구간
        length = new int[N]; // 길이

        StringTokenizer st;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(a, b);
        }

        Arrays.sort(pairs);
        br.close();
    }//init


    private static class Pair implements Comparable<Pair> {
        int s;
        int e;
        public Pair(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.s == o.s) return o.e - this.e;
            return this.s - o.s;
        }

        @Override
        public String toString() {
            return s + " " + e;
        }
    }//Pair


}//class