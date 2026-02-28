import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1368
public class Main {
    private static final int ROOT = 0;
    private static int N; // 논의 개수
    private static int[][] P; // i번째 논과 j번째 논 연결 비용

    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        int idx = ROOT, cost = 0;
        int total = 0, cnt = 0;

        pq.offer(new Node(idx, cost));

        Node cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.to; // 논 번호
            cost = cur.cost; // 물 대는 비용
            if (visited[idx]) continue;

            visited[idx] = true;
            total += cost; // 총 비용

            // 모든 논에 물 대기 완료
            if (++cnt == N+1) break;

            for(int next=0; next<=N; next++) {
                if (visited[next] || idx == next) {
                    continue;
                }
                pq.offer(new Node(next, P[idx][next]));
            }
        }

        // 모든 논에 물을 대는데 필요한 최소비용을 출력한다.
        System.out.print(total);
    }//sol

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 논의 개수

        P = new int[N+1][N+1]; // i번째 논과 j번째 논 연결 비용

        for(int i=1; i<=N; i++) {
            // i번째 논에 우물을 팔 때 드는 비용
            int w = Integer.parseInt(br.readLine());
            P[ROOT][i] = P[i][ROOT] = w; // 가상의 논 연결
        }

        StringTokenizer st;
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++) {
                P[i][j] = Integer.parseInt(st.nextToken());
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
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }//Node

}//class