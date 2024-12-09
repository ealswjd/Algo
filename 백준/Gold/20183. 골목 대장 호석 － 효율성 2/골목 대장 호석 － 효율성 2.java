import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20182
public class Main {
    private static final int INF = 1000_000_004;
    private static int N; // 교차로 개수
    private static int S, E; // 시작 교차로 번호, 도착 교차로 번호
    private static long C; // 가진 돈
    private static List<List<Node>> list;


    public static void main(String[] args) throws IOException {
        init();

        int minCost = getMinCost();
        System.out.print(minCost);
    }//main


    private static int getMinCost() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        long[] costArr = new long[N+1];
        int[] result = new int[N+1];
        Arrays.fill(result, INF);

        pq.offer(new Node(S, C, 0));
        result[S] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.to;
            long cost = node.cost;
            int maxCost = node.maxCost;

            if(visited[cur]) continue;
            if(cost == 0 || cur == E) continue;
            visited[cur] = true;

            for(Node next : list.get(cur)) {
                int max = Math.max(maxCost, next.maxCost);
                long nextCost = cost - next.cost;

                if(nextCost < 0) continue;

                if(result[next.to] > max) {
                    result[next.to] = max;
                    costArr[next.to] = nextCost;
                    pq.offer(new Node(next.to, nextCost, max));
                }
                else if(result[next.to] == max && costArr[next.to] < nextCost) {
                    costArr[next.to] = nextCost;
                    pq.offer(new Node(next.to, nextCost, max));
                }
            }
        }

        if(result[E] == INF) return -1;
        return result[E];
    }//getMinCost


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 교차로 개수
        int M = Integer.parseInt(st.nextToken()); // 골목 개수
        S = Integer.parseInt(st.nextToken()); // 시작 교차로 번호
        E = Integer.parseInt(st.nextToken()); // 도착 교차로 번호
        C = Long.parseLong(st.nextToken()); // 가진 돈

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, cost, cost));
            list.get(b).add(new Node(a, cost, cost));
        }

        br.close();
    }//init


    private static class Node implements Comparable<Node> {
        int to;
        long cost;
        int maxCost;
        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
        public Node(int to, long cost, int maxCost) {
            this(to, cost);
            this.maxCost = maxCost;
        }

        @Override
        public int compareTo(Node o) {
            if(this.maxCost == o.maxCost) {
                return Long.compare(o.cost, this.cost);
            }
            return this.maxCost - o.maxCost;
        }
    }//Node


}//class