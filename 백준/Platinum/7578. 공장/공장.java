import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/7578
public class Main {
    private static final int MAX_ID = 1_000_000; // 식별번호 최댓값
    private static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] idx = new int[MAX_ID + 1]; // 식별번호의 A열 위치
        tree = new int[N * 4];

        // A
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int id = Integer.parseInt(st.nextToken());
            idx[id] = i; // 해당 식별번호가 A의 i번째에 있음
        }

        // B
        long total = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int id = Integer.parseInt(st.nextToken());
            int targetIdx = idx[id];

            // 현재 케이블 연결 위치보다 더 큰 위치에 연결된 개수
            total += getSum(1, 1, N, targetIdx + 1, N);
            // 현재 케이블 연결
            update(1, 1, N, targetIdx);
        }

        br.close();
        System.out.print(total);
    }//main

    // 방문 횟수 갱신
    private static void update(int cur, int start, int end, int target) {
        if (target < start || end < target) return;

        tree[cur]++; // 구간에 속하면 방문 횟수 증가

        if (start != end) {
            int mid = (start + end) / 2;
            update(cur * 2, start, mid, target);
            update(cur * 2 + 1, mid + 1, end, target);
        }
    }//update

    // 구간합 구하기
    private static long getSum(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) return 0;
        if (from <= start && end <= to) {
            return tree[cur];
        }

        int mid = (start + end) / 2;

        return getSum(cur * 2, start, mid, from, to)
                + getSum(cur * 2 + 1, mid + 1, end, from, to);
    }//getSum

}//class