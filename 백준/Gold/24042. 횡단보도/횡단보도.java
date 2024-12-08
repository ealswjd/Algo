import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/24042
public class Main {
    private static final long INF = Long.MAX_VALUE;
    private static int N; // 지역의 수
    private static int M; // 횡단보도의 주기
    private static List<List<Node>> list;


    public static void main(String[] args) throws IOException {
        init();

        long time = getTime();
        System.out.print(time);
    }//main


    private static long getTime() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N+1];
        long[] minTime = new long[N+1];
        Arrays.fill(minTime, INF);

        int cur = 1;
        long time = 0;
        pq.offer(new Node(cur, time));
        minTime[cur] = time;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            cur = node.to;
            time = node.time;

            if(visited[cur] || minTime[cur] < time) continue;
            if(cur == N) break;
            visited[cur] = true;

            for(Node next : list.get(cur)) {
                long nextTime = next.time;

                if(time > next.time) {
                    nextTime += (long) Math.ceil((double) (time - next.time) / M) * M;
                }

                if(minTime[next.to] > nextTime) {
                    minTime[next.to] = nextTime;
                    pq.offer(new Node(next.to, nextTime));
                }
            }
        }

        return minTime[N];
    }//getTime


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 지역의 수
        M = Integer.parseInt(st.nextToken()); // 횡단보도의 주기

        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        // 파란불이 들어오는 횡단보도
        for(int i=1; i<=M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, i));
            list.get(b).add(new Node(a, i));
        }

        br.close();
    }//init


    private static class Node implements Comparable<Node> {
        int to;
        long time;

        public Node(int to, long time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.time, o.time);
        }

    }//Node


}//class