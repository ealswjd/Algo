import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20010
public class Main {
    private static int N, end, max; // 마을의 수
    private static List<List<Node>> roadList, mstList; // 도로 정보, mst 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // 모든 마을을 연결하는 최소 비용
        int min = getCost(new PriorityQueue<>());

        // 마을과 마을을 이동하는 비용이 가장 큰 경로의 비용
        getCost(0, 0, new boolean[N]);
        getCost(end, 0, new boolean[N]);

        System.out.printf("%d\n%d", min, max);
    }//sol

    private static void getCost(int cur, int cost, boolean[] visited) {
        visited[cur] = true;
        if (max < cost) {
            max = cost;
            end = cur;
        }

        for(Node next : mstList.get(cur)) {
            int to = next.to;
            if (!visited[to]) {
                getCost(to, cost + next.cost, visited);
            }
        }
    }//getCost

    private static int getCost(PriorityQueue<Node> pq) {
        boolean[] visited = new boolean[N];
        int idx = 0, cost = 0, total = 0, cnt = 0;

        pq.offer(new Node(idx, idx, cost));

        Node cur;
        int prev;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            prev = cur.prev;
            idx = cur.to;
            cost = cur.cost;

            if (visited[idx]) continue;

            visited[idx] = true;
            total += cost;
            mstList.get(prev).add(cur);
            mstList.get(idx).add(new Node(idx, prev, cost));

            if (++cnt == N) {
                return total;
            }

            for(Node next : roadList.get(idx)) {
                if (visited[next.to]) continue;
                pq.offer(new Node(idx, next.to, next.cost));
            }
        }

        return 0;
    }//getCost

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 마을의 수
        int K = Integer.parseInt(st.nextToken()); // 설치 가능한 교역로의 수

        roadList = new ArrayList<>(N);
        mstList = new ArrayList<>(N);

        for(int i=0; i<N; i++) {
            roadList.add(new ArrayList<>());
            mstList.add(new ArrayList<>());
        }

        while(K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            roadList.get(a).add(new Node(a, b, c));
            roadList.get(b).add(new Node(b, a, c));
        }

        br.close();
    }//init

    private static class Node implements Comparable<Node> {
        int prev;
        int to;
        int cost;
        Node(int prev, int to, int cost) {
            this.prev = prev;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }//Node

}//class