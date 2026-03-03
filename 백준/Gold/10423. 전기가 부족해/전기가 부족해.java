import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/10423
public class Main {
    private static final int ROOT = 0;
    private static int N; // 도시의 개수
    private static List<List<Node>> list; // 케이블 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        int idx = ROOT, cost = 0;

        pq.offer(new Node(idx, cost));

        Node cur;
        int cnt = 0, total = 0;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.to;
            cost = cur.cost;

            if (visited[idx]) continue;

            visited[idx] = true;
            total += cost;

            if (++cnt == N+1) break;

            for(Node next : list.get(idx)) {
                if (visited[next.to]) continue;
                pq.offer(next);
            }
        }

        System.out.print(total);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 케이블 수
        int K = Integer.parseInt(st.nextToken()); // 발전소 개수

        boolean[] isK = new boolean[N+1]; // 발전소 설치 여부
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        // 발전소가 설치된 도시의 번호가 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int n = Integer.parseInt(st.nextToken());
            isK[n] = true;
        }

        // M개의 두 도시를 연결하는 케이블의 정보가 u, v, w로 주어진다.
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, w));
            list.get(v).add(new Node(u, w));

            if (isK[u]) {
                list.get(ROOT).add(new Node(u, 0));
                list.get(u).add(new Node(ROOT, 0));
            }
            if (isK[v]) {
                list.get(ROOT).add(new Node(v, 0));
                list.get(v).add(new Node(ROOT, 0));
            }
        }

        br.close();
    }//init

    private static class Node implements Comparable<Node> {
        int to;
        int cost;
        Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }//Node

}//class