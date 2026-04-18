import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1395
public class Main {
    private static int[] tree;
    private static boolean[] lazy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 스위치의 개수
        int M = Integer.parseInt(st.nextToken()); // 처리할 일의 개수

        tree = new int[N * 4];
        lazy = new boolean[N * 4];

        StringBuilder ans = new StringBuilder();
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int q = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            // q -> 0: 스위치 상태 반전, 1: 켜져 있는 스위치 개수 출력
            if (q == 0) { // s부터 e번 스위치까지 상태 반전
                update(1, 1, N, s, e);
            } else { // s부터 e번 스위치까지 중 켜져 있는 스위치 개수 출력
                int cnt = getSum(1, 1, N, s, e);
                ans.append(cnt).append('\n');
            }
        }

        br.close();
        System.out.print(ans);
    }//main

    private static void propagate(int cur, int start, int end) {
        // 미룬 작업이 있다면
        if (lazy[cur]) {
            // 현재 노드 업데이트 (전체 스위치 개수 - 현재 켜진 개수)
            tree[cur] = (end - start + 1) - tree[cur];

            // 리프 노드가 아니면 자식들에게 lazy 물려주기
            if (start != end) {
                lazy[cur * 2] = !lazy[cur * 2]; // 왼쪽
                lazy[cur * 2 + 1] = !lazy[cur * 2 + 1]; // 오른쪽
            }

            // 미룬 작업 처리 완료
            lazy[cur] = false;
        }
    }//propagate

    private static void update(int cur, int start, int end, int from, int to) {
        // 미룬 작업 처리
        propagate(cur, start, end);

        // 범위 벗어남
        if (to < start || end < from) return;
        // 범위 포함됨
        if (from <= start && end <= to) {
            // 현재 구간 스위치 반전
            tree[cur] = (end - start + 1) - tree[cur];

            // 자식들에게 반전 작업 미루기
            if (start != end) {
                lazy[cur * 2] = !lazy[cur * 2];
                lazy[cur * 2 + 1] = !lazy[cur * 2 + 1];
            }
            return;
        }

        // 일부만 걸치는 경우 자식 탐색
        int mid = (start + end) / 2;
        update(cur * 2, start, mid, from, to);
        update(cur * 2 + 1, mid + 1, end, from, to);

        // 현재 노드 갱신
        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }//update

    private static int getSum(int cur, int start, int end, int from, int to) {
        // 미룬 작업 처리
        propagate(cur, start, end);

        if (to < start || end < from) return 0;
        if (from <= start && end <= to) return tree[cur];

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, from, to)
                + getSum(cur * 2 + 1, mid + 1, end, from, to);
    }//getSum

}//class