import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1275
public class Main {
    private static long[] numbers;
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        numbers = new long[N + 1];
        tree = new long[N * 4];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 1, N);

        StringBuilder ans = new StringBuilder();
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            // x~y까지의 합을 구하여라, a번째 수를 b로 바꾸어라 라는 뜻
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int from = Math.min(x, y);
            int to = Math.max(x, y);

            // x~y까지의 합을 구하여라
            long sum = getSum(1, 1, N, from, to);

            // a번째 수를 b로 바꾸어라
            long diff = b - numbers[a];
            update(1, 1, N, a, diff);
            numbers[a] = b;

            ans.append(sum).append('\n');
        }

        br.close();
        System.out.print(ans);
    }//main

    private static long getSum(int cur, int start, int end, int from, int to) {
        // 해당 범위 아님
        if (to < start || end < from) return 0;
        // 해당 범위임
        if (from <= start && end <= to) return tree[cur];

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, from, to)
                + getSum(cur * 2 + 1, mid + 1, end, from, to);
    }//getSum

    private static void update(int cur, int start, int end, int idx, long val) {
        // 해당 범위 아님
        if (idx < start || end < idx) return;

        tree[cur] += val;

        if (start != end) {
            int mid = (start + end) / 2;

            update(cur * 2, start, mid, idx, val);
            update(cur * 2 + 1, mid + 1, end, idx, val);
        }
    }//update

    private static void build(int cur, int start, int end) {
        // leaf node
        if (start == end) {
            tree[cur] = numbers[start];
            return;
        }

        int mid = (start + end) / 2;

        // 왼쪽 자식
        build(cur * 2, start, mid);
        // 오른쪽 자식
        build(cur * 2 + 1, mid + 1, end);

        // 현재 노드: 왼쪽 자식 + 오른쪽 자식 합
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }//build

}//class