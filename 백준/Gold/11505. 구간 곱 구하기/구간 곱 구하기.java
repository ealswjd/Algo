import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/11505
public class Main {
    private static final int MOD = 1_000_000_007;
    private static int[] numbers;
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 변경이 일어나는 횟수
        int K = Integer.parseInt(st.nextToken()); // 구간의 곱을 구하는 횟수
        int T = M + K;

        numbers = new int[N+1];
        tree = new long[N*4];

        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(br.readLine());
        }

        build(1, 1, N);

        StringBuilder ans = new StringBuilder();
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (q == 1) { // a번째 수를 b로 변경
                update(1, 1, N, a, b);
                numbers[a] = b;
            } else { // a ~ b 구간곱 구하기
                long result = query(1, 1, N, a, b);
                ans.append(result).append('\n');
            }
        }

        br.close();

        System.out.print(ans);
    }//main

    private static long query(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) {
            return 1;
        }
        if (from <= start && end <= to) {
            return tree[cur];
        }

        int mid = (start + end) / 2;

        return (query(cur * 2, start, mid, from, to)
                * query(cur * 2 + 1, mid + 1, end, from, to)) % MOD;
    }//query

    private static void update(int cur, int start, int end, int target, int change) {
        if (target < start || end < target) {
            return;
        }
        if (start == end) {
            tree[cur] = change;
            return;
        }

        int mid = (start + end) / 2;
        update(cur * 2, start, mid, target, change);
        update(cur * 2 + 1, mid + 1, end, target, change);

        tree[cur] = (tree[cur * 2] * tree[cur * 2 + 1]) % MOD;
    }//update

    private static void build(int cur, int start, int end) {
        if (start == end) {
            tree[cur] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;
        build(cur * 2, start, mid);
        build(cur * 2 + 1, mid + 1, end);

        tree[cur] = (tree[cur * 2] * tree[cur * 2 + 1]) % MOD;
    }//build

}//class