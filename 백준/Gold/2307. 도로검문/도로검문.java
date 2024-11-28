import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2307
public class Main {
    private static final int INF = 10_000_000;
    private static int N; // 도시 개수
    private static List<List<int[]>> roadList; // 도로 정보
    private static int[] prev; // 최단 경로


    public static void main(String[] args) throws IOException {
        init();

        /*
        경찰이 하나의 도로를 막음으로써 지연시킬 수 있는 최대 시간 구하기
        (단, 그 지연시간이 무한대이면 -1을 출력해야 한다.)
        */
        int maxTime = getMaxTime();
        System.out.print(maxTime);
    }//main


    private static int getMaxTime() {
        int max = 0;
        int min = dijkstra(1, N, 0, 0); // 막지 않았을 경우 최단시간

        for(int from=N; from!=prev[from]; from=prev[from]) {
            int to = prev[from];
            int time = dijkstra(N, 1, from, to);
            max = Math.max(max, time);

            if(max == INF) break;
        }

        if(max == INF) return -1; // 지연시간이 무한대
        return max - min; // 지연시간
    }//getMaxTime


    private static int dijkstra(int start, int end, int from, int to) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] minCost = new int[N+1];
        Arrays.fill(minCost, INF);

        int city = start;
        int time = 0;
        pq.offer(new int[] {city, time});
        minCost[city] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            city = cur[0]; // 도시
            time = cur[1]; // 시간

            if(city == end) return time;

            for(int[] next : roadList.get(city)) {
                int nextCity = next[0];
                int nextCost = next[1] + minCost[city];
                // 도로 막기
                if(city == from && nextCity == to) continue;

                if(minCost[nextCity] > nextCost) {
                    if(start == 1) prev[nextCity] = city;
                    
                    minCost[nextCity] = nextCost;
                    pq.offer(new int[] {nextCity, nextCost});
                }
            }
        }

        return minCost[end];
    }//dijkstra


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken());

        roadList = new ArrayList<>(N+1); // 도로 정보
        prev = new int[N+1];

        for(int i=0; i<=N; i++) {
            roadList.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // 도로(a, b)와 그 통과시간 t가 a b t 로 표시된다.
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            roadList.get(a).add(new int[] {b, t});
            roadList.get(b).add(new int[] {a, t});
        }

        br.close();
    }//init


}//class