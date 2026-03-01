import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/14621
public class Main {
    private static int N; // 학교의 수
    private static char[] gender; // 각 학교 성별 정보
    private static List<List<Node>> list; // 도로 연결 정보

    public static void main(String[] args) throws IOException {
        init();
        int totalDist = getDist();

        System.out.print(totalDist);
    }//main

    private static int getDist() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        int total = 0, cnt = 0;
        int idx = 0, dist = 0;

        pq.offer(new Node(idx, dist));

        Node cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            idx = cur.to; // 현재 학교
            dist = cur.dist; // 거리

            if (visited[idx]) continue;

            visited[idx] = true;
            total += dist;

            if (++cnt == N) return total;

            for(Node next : list.get(idx)) {
                int to = next.to;
                if (visited[to] || gender[idx] == gender[to]) continue;
                pq.offer(next);
            }
        }

        // 모든 학교를 연결하는 경로가 없을 경우 -1을 출력
        return -1;
    }//getDist

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 학교의 수 N와 학교를 연결하는 도로의 개수 M이 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학교의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 개수

        list = new ArrayList<>(N); // 도로 연결 정보
        gender = new char[N]; // 각 학교 성별 정보

        // 둘째 줄에 각 학교가 남초 대학교라면 M, 여초 대학교라면 W이 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            gender[i] = st.nextToken().charAt(0);
            list.add(new ArrayList<>());
        }

        // M개의 줄에 u v d가 주어지며
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // u학교와 v학교가 연결되어 있으며 이 거리는 d임을 나타낸다.
            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, d));
            list.get(v).add(new Node(u, d));
        }

        br.close();
    }//init

    private static class Node implements Comparable<Node> {
        int to;
        int dist;
        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
        @Override
        public int compareTo(Node n) {
            return this.dist - n.dist;
        }
    }//Node

}//class