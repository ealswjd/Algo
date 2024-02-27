import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제 이름(난이도) : 골목 대장 호석 - 기능성 (골드 5)
 * 링크 : https://www.acmicpc.net/problem/20168
 * */
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int N, M, S, E, C;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 교차로 개수
        M = Integer.parseInt(st.nextToken()); // 골목 개수
        S = Integer.parseInt(st.nextToken()) - 1; // 시작 교차로 번호
        E = Integer.parseInt(st.nextToken()) - 1; // 도착 교차로 번호
        C = Integer.parseInt(st.nextToken()); // 가진 돈

        init();

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            list.get(from).add(new Node(to, cost, 0));
            list.get(to).add(new Node(from, cost, 0));
        }//while
        br.close();

        int result = dijkstra();
        System.out.print(result);
    }//main

    private static int dijkstra() {
        int min = INF;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(S, 0, 0));

        Node curNode;
        int cur, cost, maxCost;
        while(!pq.isEmpty()) {
            curNode = pq.poll();
            cur = curNode.to;
            cost = curNode.cost;
            maxCost = curNode.maxCost;

            if(cur == E) {
                min = maxCost;
                break;
            }//if

            if(visited[cur]) continue;
            visited[cur] = true;

            for(Node next : list.get(cur)) {
                if(visited[next.to]) continue;
                if(next.cost + cost <= C) {
                    pq.offer(new Node(next.to, next.cost + cost, Math.max(maxCost, next.cost)));
                }
            }//for

        }//while

        if(min == INF) return -1;
        return min;
    }//dijkstra

    private static void init() {
        visited = new boolean[N];
        list = new ArrayList<>();
        for(int i=0; i<N; i++) {
            list.add(new ArrayList<>());
        }//for
    }//init

    static class Node implements Comparable<Node> {
        int to;
        int cost;
        int maxCost;
        public Node(int to, int cost, int maxCost) {
            this.to = to;
            this.cost = cost;
            this.maxCost = maxCost;
        }
        @Override
        public int compareTo(Node n) {
            return this.maxCost - n.maxCost;
        }
    }//Node

}//class