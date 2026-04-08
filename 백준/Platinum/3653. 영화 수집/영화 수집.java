import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/3653
public class Main {
    private static int N, M, total; // 영화 개수, 영화를 보는 횟수
    private static int[] tree; // 영화 존재 개수
    private static int[] positions; // dvd 위치
    private static StringBuilder ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수

        ans = new StringBuilder();
        while(T-- > 0) {
            init(br);
            solve(br);
        }

        br.close();
        System.out.print(ans);
    }//main

    private static void solve(BufferedReader br) throws IOException {
        // 보려고 하는 영화의 번호가 순서대로 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());

        int top = M; // 꼭대기
        for(int i=0; i<M; i++) {
            int dvd = Integer.parseInt(st.nextToken()); // 현재 보려고 하는 dvd 번호
            int idx = positions[dvd]; // dvd의 현재 위치

            // 현재 dvd 위에 있는 dvd 개수
            int cnt = getCnt(1, 1, total, 1, idx - 1);
            // 영화를 다 본 이후에는 가장 위에 놓는다.
            update(1, 1, total, idx, -1); // 현재 위치에서 제거
            update(1, 1, total, top, 1); // 맨 위로 이동
            positions[dvd] = top--;

            ans.append(cnt).append(' ');
        }

        ans.append('\n');
    }//solve

    private static void update(int cur, int start, int end, int target, int diff) {
        if (target < start || end < target) return;

        tree[cur] += diff;

        if (start != end) {
            int mid = (start + end) / 2;

            update(cur * 2, start, mid, target, diff);
            update(cur * 2 + 1, mid + 1, end, target, diff);
        }
    }//update

    private static int getCnt(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) return 0;
        if (from <= start && end <= to) return tree[cur];

        int mid = (start + end) / 2;

        return getCnt(cur * 2, start, mid, from, to)
                + getCnt(cur * 2 + 1, mid + 1, end, from, to);
    }//getCnt

    private static void build(int cur, int start, int end, int from, int to) {
        if (to < start || end < from) return;
        if (start == end) {
            tree[cur] = 1;
            return;
        }

        int mid = (start + end) / 2;

        build(cur * 2, start, mid, from, to);
        build(cur * 2 + 1, mid + 1, end, from, to);

        tree[cur] = tree[cur * 2] + tree[cur * 2 + 1];
    }//build

    private static void init(BufferedReader br) throws IOException {
        // 상근이가 가지고 있는 영화의 수 n과 보려고 하는 영화의 수 m이 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 영화 개수
        M = Integer.parseInt(st.nextToken()); // 영화를 보는 횟수
        total = N + M; // M개의 영화를 위로 이동하기 위해 여분 공간을 M만큼 추가

        tree = new int[total * 4];
        positions = new int[N + 1];

        for(int i=1; i<=N; i++) {
            positions[i] = i + M;
        }

        build(1, 1, total, M + 1, total);
    }//init

}//class