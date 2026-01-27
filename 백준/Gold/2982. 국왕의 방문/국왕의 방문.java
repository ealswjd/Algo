import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MAX = 100_000_004;
    private static int N; // 교차로 개수
    private static int A, B, K, G;
    private static List<List<Road>> list; // 도로 정보
    private static int[] kingPath; // 왕 방문 순서
    private static int[][] kStart, kEnd; // 통제 시작, 종료


    public static void main(String[] args) throws IOException {
        init();
        sol();
    }//main

    private static void sol() {
        setKingSchedule();
        int min = dijkstra();

        System.out.print(min);
    }//sol

    private static int dijkstra() {
        int[] minTime = new int[N+1];
        PriorityQueue<Road> pq = new PriorityQueue<>();

        Arrays.fill(minTime, MAX);

        int from = A;
        int time = K;

        minTime[from] = time;
        pq.offer(new Road(from, time));

        Road cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            from = cur.to;
            time = cur.time;

            if (minTime[from] < time) continue;
            if (from == B) return time - K;

            for(Road next : list.get(from)) {
                int to = next.to;
                int nTime = time + next.time;

                // 도로 통제
                if (kStart[from][to] != -1) {
                    int kingIn = kStart[from][to];
                    int kingOut = kEnd[from][to];

                    // 상근이의 이동이 통제 시간에 겹침
                    if (time >= kingIn && time < kingOut) {
                        nTime = kingOut + next.time;
                    }
                }

                if (minTime[to] > nTime) {
                    minTime[to] = nTime;
                    pq.offer(new Road(to, nTime));
                }
            }
        }

        return minTime[B] - K;
    }//dijkstra

    private static void setKingSchedule() {
        for(int i=0; i<=N; i++) {
            Arrays.fill(kStart[i], -1);
            Arrays.fill(kEnd[i], -1);
        }

        int curTime = 0;
        for(int i=0; i<G-1; i++) {
            int from = kingPath[i];
            int to = kingPath[i+1];

            int dist = 0;
            for(Road r : list.get(from)) {
                if (r.to == to) {
                    dist = r.time;
                    break;
                }
            }

            kStart[from][to] = kStart[to][from] = curTime;
            kEnd[from][to] = kEnd[to][from] = curTime + dist;

            curTime += dist;
        }
    }//setKingSchedule

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 교차로의 수 N과 도로의 수 M이 주어진다
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        /* 네 정수 A, B, K, G가 주어진다.
         * A는 상근이가 배달을 시작하는 교차로, B는 상근이가 배달을 마치는 교차로이다.
         *  K는 고둘라가 출발한 시간과 상근이가 출발한 시간의 차이, G는 고둘라가 방문하는 교차로의 개수
         */
        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());

        kStart = new int[N+1][N+1]; // 도로 통제 시작
        kEnd = new int[N+1][N+1]; // 도로 통제 종료
        list = new ArrayList<>(N+1); // 도로 정보

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        // G개의 정수가 주어진다.이 정수는 고둘라가 방문하는 교차로이다.
        kingPath = new int[G];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<G; i++) {
            kingPath[i] = Integer.parseInt(st.nextToken());
        }

        while(M-- > 0) {
            // M개 줄에는 도로의 정보를 나타내는 세 정수 U, V, L이 주어진다
            // 교차로 U와 V를 연결하는 도로를 이동하는데 L분이 걸린다는 뜻
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            list.get(u).add(new Road(v, l));
            list.get(v).add(new Road(u, l));
        }

        br.close();
    }//init

    private static class Road implements Comparable<Road> {
        int to;
        int time;

        Road(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Road o) {
            return this.time - o.time;
        }
    }//Road


}//class