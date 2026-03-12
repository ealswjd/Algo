import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17490
public class Main {
    private static final int ROOT = 0; // 와우도
    private static int N, M; // 강의동 개수, 공사구간 개수
    private static long K; // 돌 개수 (0 ≤ K ≤ 5,000,000,000)
    private static int[] parents;
    private static long[] edges; // 돌의 개수 + 강의동 번호
    private static boolean[] blocked; // 공사중

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // 공사 구간이 1개 이하면 모든 강의동 연결 가능
        if (M <= 1) {
            System.out.print("YES");
            return;
        }

        // 필요한 돌의 개수를 기준으로 정렬
        Arrays.sort(edges);

        boolean linked = false; // 모든 강의동 연결 여부
        int cnt = 0; // 연결 개수
        long usedCnt = 0; // 사용한 돌 개수

        // 이동 가능한 이웃 강의동 먼저 연결
        for(int cur=1; cur<=N; cur++) {
            int next = (cur == N) ? 1 : cur + 1;

            if (blocked[cur] || !union(cur, next)) continue;
            cnt++;
        }

        // 남은 강의동은 와우도 연결
        for(long cur : edges) {
            int cost = (int) (cur >> 32); // 와우도 연결 돌 개수
            int idx = (int) cur; // 현재 강의동 번호

            // 돌 부족함
            if ((usedCnt + cost) > K) break;
            // 연결
            if (union(ROOT, idx)) {
                usedCnt += cost;

                if (++cnt == N) {
                    linked = true;
                    break;
                }
            }
        }

        // 모든 강의동을 연결할 수 있으면 YES를, 그렇지 않으면 NO를 출력
        System.out.print(linked ? "YES" : "NO");
    }//sol

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) return false;

        if (a < b) parents[b] = a;
        else parents[a] = b;

        return true;
    }//union

    private static int find(int n) {
        if (parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }//find

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 강의동의 수 N, 공사구간의 수 M, 건덕이가 가진 돌의 수 K가 공백으로 구분돼 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Long.parseLong(st.nextToken());

        parents = new int[N+1];
        edges = new long[N]; // 돌의 개수 + 강의동 번호
        blocked = new boolean[N+1]; // 공사 구간

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            long cost = Integer.parseInt(st.nextToken()); // 놓아야하는 돌의 개수
            // 돌의 개수를 기준으로 정렬 예정
            edges[i] = (cost << 32) | (i+1);
            parents[i+1] = i+1;
        }

        // 공사 구간 정보
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int min = Math.min(a, b);
            int max = Math.max(a, b);

            if (min == 1 && max == N) {
                blocked[N] = true;
            } else {
                blocked[min] = true;
            }
        }

        br.close();
    }//init


}//class