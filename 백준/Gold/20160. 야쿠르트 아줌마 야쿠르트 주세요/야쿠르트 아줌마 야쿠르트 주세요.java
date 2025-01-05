import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/20160
public class Main {
    private static final int M = 10; // 야쿠르트 아줌마 방문 지점 개수
    private static final int MAX_NODE = 10_001; // 정점 V(1 ≤ V ≤ 10,000)
    private static final int INF = Integer.MAX_VALUE;
    private static int V; // 정점의 개수
    private static int S; // 내가 출발하는 정점 번호
    private static int[] move; // 야쿠르트 아줌마 동선
    private static List<List<Node>> roadList; // 도로 연결 정보


    public static void main(String[] args) throws IOException {
        init();

        // 야쿠르트를 살 수 있는 정점 번호 출력
        int number = getNumber();
        System.out.print(number);
    }//main


    private static int getNumber() {
        int minNumber = (S == move[0]) ? S : MAX_NODE; // 야쿠르트를 살 수 있는 정점
        int[] minTime = dijkstra(S); // 내가 각 지점을 방문하는 최단 시간
        long totalTime = 0; // 총 시간

        // 야쿠르트 아줌마 이동
        out:for(int i=0, end=M-1; i<end; i++) {
            int[] yogurtTime = dijkstra(move[i]);
            int next = i + 1; // 다음 지점

            // 다음 지점 방문할 수 있는지 확인
            while(yogurtTime[move[next]] == INF) {
                next++;
                if(next >= M) break out;
            }

            int nextNode = move[next];

            if(minTime[nextNode] <= yogurtTime[nextNode] + totalTime) {
                if(nextNode < minNumber) minNumber = nextNode;
            }

            totalTime += yogurtTime[nextNode];
            i = next - 1;
        }

        if(minNumber == MAX_NODE) return -1;
        return minNumber;
    }//getNumber


    private static int[] dijkstra(int start) {
        int[] minTime = new int[V+1];
        Arrays.fill(minTime, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        minTime[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int cur = node.to;
            int time = node.time;

            if(minTime[cur] < time) continue;

            for(Node next : roadList.get(cur)) {
                int to = next.to;
                int nextTime = next.time + time;

                if(minTime[to] > nextTime) {
                    minTime[to] = nextTime;
                    pq.offer(new Node(to, nextTime));
                }
            }
        }

        return minTime;
    }//dijkstra


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 도로의 개수

        roadList = new ArrayList<>(V+1);
        for(int i=0; i<=V; i++) {
            roadList.add(new ArrayList<>());
        }

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            // u 와 v 사이에 가중치가 w인 도로가 존재한다는 뜻
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            roadList.get(u).add(new Node(v, w));
            roadList.get(v).add(new Node(u, w));
        }

        // 야쿠르트 아줌마가 야쿠르트를 파는 10개 지점의 정점 번호
        move = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            move[i] = Integer.parseInt(st.nextToken());
        }

        // 내가 출발하는 정점 번호
        S = Integer.parseInt(br.readLine());

        br.close();
    }//init


    private static class Node implements Comparable<Node> {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }//Node


}//class