import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/11779
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, start, target;
    static int[] minCost;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        init();

        StringTokenizer st;
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, cost));
        }//for

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        StringBuilder result = new StringBuilder();
        int[] prev = new int[N+1];
        dijkstra(prev);

        result.append(minCost[target]).append('\n');
        getOrder(prev, result);

        System.out.print(result);
    }//main

    private static void getOrder(int[] prev, StringBuilder result) {
        Stack<Integer> stack = new Stack<>();
        int cur = target;
        stack.add(cur);
        while(prev[cur] != 0) {
            stack.add(prev[cur]);
            cur = prev[cur];
        }//while

        result.append(stack.size()).append('\n');
        while(!stack.isEmpty()) {
            result.append(stack.pop()).append(' ');
        }
    }//getOrder

    private static void dijkstra(int[] prev) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        minCost[start] = 0;

        Node cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();

            if(visited[cur.to] || minCost[cur.to] < cur.cost) continue;
            visited[cur.to] = true;

            if(cur.to == target) break;

            for(Node next : list.get(cur.to)) {
                if(minCost[next.to] > minCost[cur.to] + next.cost) {
                    minCost[next.to] = minCost[cur.to] + next.cost;
                    pq.offer(new Node(next.to, minCost[next.to]));
                    prev[next.to] = cur.to; // 경로
                }
            }//for
        }//while

    }//dijkstra

    private static void init() {
        minCost = new int[N+1];
        Arrays.fill(minCost, INF);
        visited = new boolean[N+1];
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }//for
    }//init

    static class Node implements Comparable<Node> {
        int to;
        int cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}//class