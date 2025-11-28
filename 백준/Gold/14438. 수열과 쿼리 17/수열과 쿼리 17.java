import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14438
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N; // 수열의 크기
    private static int[] numbers; // 수열
    private static int[] minSeg; // 최솟값


    public static void main(String[] args) throws IOException {
        sol();
    }//main


    private static void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 수열의 크기 N이 주어진다. (1 ≤ N ≤ 100,000)
        N = Integer.parseInt(br.readLine());

        numbers = new int[N+1];
        minSeg = new int[N*4];

        // A1, A2, ..., AN이 주어진다. (1 ≤ Ai ≤ 109)
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 1, N);

        // 쿼리의 개수 M이 주어진다. (1 ≤ M ≤ 100,000)
        int M = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 1:변경, 2:작은 값 출력
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(q == 1) { // 1 : numbers[a] -> b로 변경
                numbers[a] = b;
                update(1, 1, N, a, b);
            } else { // 2 : numbers[a] ~ numbers[b]에서 최솟값 출력
                int min = getMin(1, 1, N, a, b);
                ans.append(min).append('\n');
            }
        }

        br.close();
        System.out.print(ans);
    }//sol


    private static int getMin(int cur, int start, int end, int from, int to) {
        if(to < start || end < from) {
            return MAX;
        }
        if(from <= start && end <= to) {
            return minSeg[cur];
        }

        int mid = (start + end) / 2;

        return Math.min(
                getMin(cur * 2, start, mid, from, to),
                getMin(cur * 2 + 1, mid + 1, end, from, to)
        );
    }//getMin


    private static void update(int cur, int start, int end, int idx, int val) {
        if(start == end) {
            minSeg[cur] = val;
            return;
        }

        int mid = (start + end) / 2;

        if(idx <= mid) update(cur * 2, start, mid, idx, val);
        else update(cur * 2 + 1, mid + 1, end, idx, val);

        minSeg[cur] = Math.min(minSeg[cur*2], minSeg[cur*2+1]);
    }//update


    private static void build(int cur, int start, int end) {
        if(start == end) { // 리프 노드
            minSeg[cur] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;

        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);

        minSeg[cur] = Math.min(minSeg[cur*2], minSeg[cur*2+1]);
    }//build


}//class