import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1162
public class Main {
    private static final long INF = 10_000_000_002L;
    private static int N, K; // 도시의 수 N, 포장할 도로의 수 K
    private static List<List<Node>> list;


    public static void main(String[] args) throws IOException {
        init();

        long time = getMinTime();
        System.out.print(time);
    }//main


    private static long getMinTime() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[][] minTime = new long[N+1][K+1];

        for(int i=1; i<=N; i++) {
            Arrays.fill(minTime[i], INF);
        }

        pq.offer(new Node(1, 0, 0));
        minTime[1][0] = 0;
        minTime[1][1] = 0;

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int city = cur.city;
            long time = cur.time;
            int cnt = cur.cnt;

            if(minTime[city][cnt] < time) continue;

            for(Node next : list.get(city)) {
                int nextCity = next.city;
                long nextTime = next.time + time;
                int nextCnt = cnt + 1;

                if(nextCnt <= K && minTime[nextCity][nextCnt] > time) {
                    minTime[nextCity][nextCnt] = time;
                    pq.offer(new Node(nextCity, time, nextCnt));
                }
                if(cnt <= K && minTime[nextCity][cnt] > nextTime) {
                    minTime[nextCity][cnt] = nextTime;
                    pq.offer(new Node(nextCity, nextTime, cnt));
                }
            }
        }

        long min = INF;
        for(int i=0; i<=K; i++) {
            min = Math.min(min, minTime[N][i]);
        }

        return min;
    }//getMinTime


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시의 수 N과 도로의 수 M과 포장할 도로의 수 K
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(N+1);
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, t));
            list.get(b).add(new Node(a, t));
        }

        br.close();
    }//init


    private static class Node implements Comparable<Node> {
        int city;
        long time;
        int cnt;

        public Node(int city, long time) {
            this.city = city;
            this.time = time;
        }

        public Node(int city, long time, int cnt) {
            this(city, time);
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            if(this.time == o.time) return this.cnt - o.cnt;
            return Long.compare(this.time, o.time);
        }
    }//Node


}//class