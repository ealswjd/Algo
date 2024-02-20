import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
    제목 : 두 단계 최단 경로 1 (골드 4)
    링크 : https://www.acmicpc.net/problem/23793
 */
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, X, Y, Z;
    static ArrayList<ArrayList<Node>> list;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        init();

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, cost));
        }//for

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        Z = Integer.parseInt(st.nextToken());

        StringBuilder ans = new StringBuilder();
        int xToYCost = dijkstra(X, Y, 0);
        if(xToYCost == INF) ans.append(-1);
        else {
            int yToZCost = dijkstra(Y, Z, 0);
            if(yToZCost != INF) ans.append(xToYCost + yToZCost);
            else ans.append(-1);
        }
        ans.append(' ');
        int xToZCost = dijkstra(X, Z, Y);
        if(xToZCost != INF) ans.append(xToZCost);
        else ans.append(-1);

        System.out.print(ans);
    }//main

    private static int dijkstra(int start, int target, int x) {
        pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        int[] minCost = new int[N+1];
        boolean[] visited = new boolean[N+1];
        Arrays.fill(minCost, INF);

        Node cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            if(visited[cur.to]) continue;
            visited[cur.to] = true;

            if(cur.to == target) break;

            for(Node next : list.get(cur.to)) {
                if(visited[next.to] || next.to == x) continue;
                if(minCost[next.to] > cur.cost + next.cost) {
                    minCost[next.to] = cur.cost + next.cost;
                    pq.offer(new Node(next.to, minCost[next.to]));
                }
            }//for
        }//while

        return minCost[target];
    }//dijkstra

    private static void init() {
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
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }//Node

}//class