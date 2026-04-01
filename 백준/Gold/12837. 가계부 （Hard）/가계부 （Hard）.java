import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/12837
public class Main {
    private static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 월곡이가 살아온 날
        int Q = Integer.parseInt(st.nextToken()); // 쿼리의 개수

        tree = new long[N * 4];

        StringBuilder ans = new StringBuilder();
        while(Q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken()); // 1: 추가, 2: 출력
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (q == 1) { // 생후 a일에 b를 추가한다.
                update(1, 1, N, a, b);
            } else { // 생후 a일부터 b일까지 변화한 양을 출력한다.
                long result = getSum(1, 1, N, a, b);
                ans.append(result).append('\n');
            }
        }

        br.close();
        System.out.print(ans);
    }//main

    private static long getSum(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) return 0;
        if (from <= start && end <= to) return tree[cur];

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, from, to)
                + getSum(cur * 2 + 1, mid + 1, end, from, to);
    }//getSum

    private static void update(int cur, int start, int end, int target, int value) {
        if (target < start || end < target) return;

        tree[cur] += value;

        if (start != end) {
            int mid = (start + end) / 2;

            update(cur * 2, start, mid, target, value);
            update(cur * 2 + 1, mid + 1, end, target, value);
        }
    }//update

}//class