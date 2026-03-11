import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13418
public class Main {
    private static int N; // 건물의 개수
    private static List<List<Node>> roadList; // 도로 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        int min = getCost(new PriorityQueue<>());
        int max = getCost(new PriorityQueue<>(Comparator.reverseOrder()));

        System.out.print(max - min);
    }//sol

    private static int getCost(PriorityQueue<Node> pq) {
        boolean[] visited = new boolean[N+1];
        int idx = 0, cost = 0, total = 0, cnt = 0;

        pq.offer(new Node(idx, cost));

        Node cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.to;
            cost = cur.cost;

            if (visited[idx]) continue;

            visited[idx] = true;
            total += cost;

            if (++cnt == N+1) {
                return total * total;
            }

            for(Node next : roadList.get(idx)) {
                if (visited[next.to]) continue;
                pq.offer(next);
            }
        }

        return -1;
    }//getCost

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 건물의 개수
        int M = Integer.parseInt(st.nextToken()) + 1; // 도로의 개수

        roadList = new ArrayList<>(N+1); // 도로 연결 정ㅇ보
        for(int i=0; i<=N; i++) {
            roadList.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a와 b 건물에 연결된 도로가 있음. c는 오르막 정보
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()) ^ 1;

            roadList.get(a).add(new Node(b, c));
            roadList.get(b).add(new Node(a, c));
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