import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* https://www.acmicpc.net/problem/2593
 A층에서 사는 철수는 B층에 있는 친구 집에 놀러 가려고 한다.
 그런데 가능하면 엘리베이터를 타는 횟수를 줄이고 싶어 한다.
 철수가 '최소 몇 번 엘리베이터를 타야하는지'와 타야할 '엘리베이터의 순서'를 구하는 프로그램을 작성하시오.
 */
public class Main {
    private static int N, E; // 아파트 층수, 엘리베이터 개수
    private static int A, B; // 출발 층, 도착 층
    private static int[] prev; // 엘리베이터를 타는 순서
    private static boolean[] isEnd, visited; // 도착층 도착 가능 여부, 방문 체크
    private static List<List<Integer>> list; // 엘리베이터 환승 정보
    private static Elevator[] elevators; // 엘리베이터 정보
    private static Queue<int[]> q;

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        StringBuilder ans = new StringBuilder();
        int[] result = bfs();
        int idx = result[0]; // 마지막으로 탑승한 엘리베이터 번호
        int cnt = result[1]; // 엘리베이터 탑승 횟수

        ans.append(cnt).append('\n');

        if (cnt > 0) {
            Stack<Integer> order = new Stack<>();

            for(int i=0; i<cnt; i++) {
                order.add(idx + 1);
                idx = prev[idx];
            }

            while(!order.isEmpty()) {
                ans.append(order.pop()).append('\n');
            }
        }

        System.out.print(ans);
    }//sol

    private static int[] bfs() {
        int[] cur;
        int idx, cnt;

        while(!q.isEmpty()) {
            cur = q.poll();
            idx = cur[0]; // 엘리베이터 번호
            cnt = cur[1]; // 탑승 횟수

            if (isEnd[idx]) {
                return cur;
            }

            for(int next : list.get(idx)) {
                if (visited[next]) continue;

                visited[next] = true;
                prev[next] = idx;
                q.offer(new int[] {next, cnt + 1});
            }
        }

        return new int[] {-1, -1};
    }//bfs

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // N과 E가 빈 칸을 사이에 두고 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(E); // 엘리베이터 환승 정보
        elevators = new Elevator[E]; // 엘리베이터 정보
        prev = new int[E]; // 엘리베이터를 타는 순서
        isEnd = new boolean[E]; // 도착층 도착 가능 여부
        visited = new boolean[E]; // 방문 체크
        q = new LinkedList<>();

        // M줄에 걸쳐 엘리베이터 정보가 주어진다
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int step = Integer.parseInt(st.nextToken());

            elevators[i] = new Elevator(start, step);
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        br.close();
        setList();
    }//init

    private static void setList() {
        List<BitSet> bitSets = new ArrayList<>();

        for(int i=0; i<E; i++) {
            Elevator cur = elevators[i];
            BitSet bit = new BitSet();

            for(int n=cur.start; n<=N; n+=cur.step) {
                bit.set(n);

                if (n == A) { // 출발층
                    q.offer(new int[] {i, 1}); // 엘리베이터 번호, 탑승 횟수
                    visited[i] = true;
                } else if (n == B) { // 도착층
                    isEnd[i] = true;
                }
            }
            bitSets.add(bit);
        }

        for(int i=0; i<E-1; i++) {
            for(int j=i+1; j<E; j++) {
                // i번 엘리베이터에서 j번 엘리베이터로 환승 가능
                if (bitSets.get(i).intersects(bitSets.get(j))) {
                    list.get(i).add(j);
                    list.get(j).add(i);
                }
            }
        }
    }//setList

    private static class Elevator {
        int start; // 시작층
        int step; // step씩 증가

        Elevator(int start, int step) {
            this.start = start;
            this.step = step;
        }
    }//Elevator

}//class