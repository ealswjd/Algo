import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/17835
public class Main {
    static final Long INF = 10_000_000_001L;
    static int N, M, K;
    static long[] minDist; // 최소 거리
    static List<List<int[]>> list; // 간선 정보
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 수
        M = Integer.parseInt(st.nextToken()); // 도로의 수
        K = Integer.parseInt(st.nextToken()); // 면접장의 수

        init();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            // 도시 U에서 V로 갈 수 있는 도로가 존재하고, 그 거리가 C
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(v).add(new int[] {u, c});
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++) {
            int n = Integer.parseInt(st.nextToken());
            pq.offer(new Node(n, 0));
            minDist[n] = 0;
        }

        dijkstra();
        getAnswer();
    }//main

    
    private static void getAnswer() {
        int city = 0; // 면접장까지 거리가 가장 먼 도시
        long dist = 0; // 해당 도시에서 면접장까지의 거리

        for(int i=1; i<=N; i++) {
            if(dist < minDist[i]) {
                city = i;
                dist = minDist[i];
            }
        }

        String ans = city + "\n" + dist;
        System.out.print(ans);
    }//getAnswer

    
    private static void dijkstra() {
        Node cur;
        int city;
        long dist;

        while(!pq.isEmpty()) {
            cur = pq.poll();
            city = cur.to;
            dist = cur.dist;

            if(minDist[city] < dist) continue;

            for(int[] next : list.get(city)) {
                int to = next[0];
                long nextDist = next[1] + dist;

                if(minDist[to] <= nextDist) continue;

                pq.offer(new Node(to, nextDist));
                minDist[to] = nextDist;
            }
        }

    }//dijkstra

    
    private static void init() {
        list = new ArrayList<>(N+1);
        minDist = new long[N+1];
        pq = new PriorityQueue<>();

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
            minDist[i] = INF;
        }
    }//init

    
    static class Node implements Comparable<Node> {
        int to; // 도시
        long dist; // 거리

        public Node(int to, long dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node n) {
            return Long.compare(this.dist, n.dist);
        }
    }//Node

}//class