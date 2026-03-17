import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2176
public class Main {
    private static final int MAX = 55_555_555;
    private static final int S = 1, T = 2; // S = 1, T = 2인 경우로 함
    private static int N; // 정점의 개수
    private static int[] dist; // T와 각 정점의 거리
    private static List<List<Node>> list; // 간선 연결 정보


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        // T와 각 정점의 거리 구하기
        dijkstra(T);

        int[] dp = new int[N+1]; // 합리적인 이동경로의 개수
        Arrays.fill(dp, -1);

        // 합리적인 이동경로의 개수 구하기
        dfs(S, dp);

        // S -> T로 이동할 수 있는 합리적인 이동경로의 개수 출력
        System.out.print(dp[S]);
    }//sol

    private static int dfs(int cur, int[] dp) {
        if (cur == T) return 1;
        if (dp[cur] != -1) return dp[cur];

        int cnt = 0;
        for(Node next : list.get(cur)) {
            // T에 가까워지며 이동하는 경우, 이를 합리적인 이동경로라 한다.
            if (dist[next.to] < dist[cur]) {
                cnt += dfs(next.to, dp);
            }
        }

        return dp[cur] = cnt;
    }//dfs

    private static void dijkstra(int from) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int len = 0;

        dist[from] = len;
        pq.offer(new Node(from, len));

        Node cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            from = cur.to;
            len = cur.len;

            if (dist[from] < len) continue;

            for(Node next : list.get(from)) {
                int nLen = len + next.len;
                if (dist[next.to] > nLen) {
                    dist[next.to] = nLen;
                    pq.offer(new Node(next.to, nLen));
                }
            }
        }
    }//dijkstra

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 정점의 개수 N(1 < N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 100,000이 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        dist = new int[N+1]; // T와 각 정점의 거리
        list = new ArrayList<>(N+1); // 간선 연결 정보

        for(int i=0; i<=N; i++) {
            dist[i] = MAX;
            list.add(new ArrayList<>());
        }

        // M개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다.
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 간선은 방향성이 없다.
            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        br.close();
    }//init

    private static class Node implements Comparable<Node> {
        int to;
        int len;
        Node(int to, int len) {
            this.to = to;
            this.len = len;
        }
        @Override
        public int compareTo(Node n) {
            return this.len - n.len;
        }
    }//Node

}//class