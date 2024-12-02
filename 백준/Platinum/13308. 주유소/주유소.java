import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13308
public class Main {
    private static final int INF = 1_000_000_000;
    private static int N; // 도시의 수
    private static int maxCost;
    private static int[] costArr;
    private static List<List<City>> list;


    public static void main(String[] args) throws IOException {
        init();

        int minCost = getMinCost();
        System.out.print(minCost);
    }//main


    private static int getMinCost() {
        PriorityQueue<City> pq = new PriorityQueue<>();
        int[][] minCost = new int[N+1][maxCost+1];
        for(int i=1; i<=N; i++) {
            Arrays.fill(minCost[i], INF);
        }

        int cost = costArr[1];
        pq.offer(new City(1, 0, 0, cost));
        minCost[1][cost] = 0;

        while(!pq.isEmpty()) {
            City cur = pq.poll();
            int from = cur.to;
            cost = cur.cost;
            int min = cur.min;

            if(minCost[from][min] < cost) continue;
            if(from == N) continue;

            for(City next : list.get(from)) {
                int to = next.to;
                int nextDist = next.dist;
                int nextCost = cost + nextDist * min;
                int nextMin = Math.min(min, costArr[to]);

                if(minCost[to][nextMin] > nextCost) {
                    minCost[to][nextMin] = nextCost;
                    pq.offer(new City(to, nextDist, nextCost, nextMin));
                }
            }
        }

        int result = INF;
        for(int i=1; i<=maxCost; i++) {
            result = Math.min(result, minCost[N][i]);
        }

        return result;
    }//getMinCost


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 수
        int M = Integer.parseInt(st.nextToken()); // 도로의 수

        costArr = new int[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        // 각 도시 주유소의 리터당 가격이 도시 번호 순서대로 N개의 자연수로 주어진다
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            costArr[i] = Integer.parseInt(st.nextToken());
            maxCost = Math.max(maxCost, costArr[i]);
        }

        // M개의 줄 각각에 하나의 도로에 대한 정보가 세 개의 자연수로 주어진다
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.get(a).add(new City(b, d));
            list.get(b).add(new City(a, d));
        }

        br.close();
    }//init


    private static class City implements Comparable<City> {
        int to;
        int dist;
        int cost;
        int min;

        public City(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        public City(int to, int dist, int cost, int min) {
            this(to, dist);
            this.cost = cost;
            this.min = min;
        }

        @Override
        public int compareTo(City o) {
            return Integer.compare(this.cost, o.cost);
        }
    }//City


}//class