import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int CITY = 0, COST = 1;
    private static int N, M, K, S;
    private static int P, Q;
    private static boolean[] zombiCity;
    private static int[] costArr;
    private static List<List<Integer>> list;

    
    public static void main(String[] args) throws IOException{
        init();

        long minCost = getMinCost();
        System.out.print(minCost);
    }//main

    
    private static long getMinCost() {
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(o -> o[COST]));
        boolean[] visited = new boolean[N+1];
        pq.offer(new long[] {1, 0});

        int city;
        long cost;

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            city = (int) cur[CITY];
            cost = cur[COST];

            if(city == N) return cost - costArr[N];
            if(visited[city]) continue;

            visited[city] = true;

            for(int next : list.get(city)) {
                if(visited[next] || zombiCity[next]) continue;

                pq.offer(new long[] {next, cost + costArr[next]});
            }

        }

        return -1;
    }//getMinCost

    
    private static void bfs(Queue<int[]> q, boolean[] visited) {
        int city, cnt;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            city = cur[CITY];
            cnt = cur[COST];

            if(cnt <= S) {
                costArr[city] = Q;
                if(cnt == S) continue;
            }

            for(int next : list.get(city)) {
                if(visited[next]) continue;
                visited[next] = true;
                q.offer(new int[] {next, cnt+1});
            }
        }

    }//bfs


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 도시의 수
        M = Integer.parseInt(st.nextToken()); // 길의 수
        K = Integer.parseInt(st.nextToken()); // 좀비에게 점령당한 도시의 수
        S = Integer.parseInt(st.nextToken()); // 위험한 도시의 범위

        st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken()); // 안전한 도시 숙박비
        Q = Integer.parseInt(st.nextToken()); // 위험한 도시 숙박비

        zombiCity = new boolean[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        for(int i=0; i<K; i++) {
            int city = Integer.parseInt(br.readLine());
            zombiCity[city] = true;
            q.offer(new int[] {city, 0});
            visited[city] = true;
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
            list.get(b).add(a);
        }

        costArr = new int[N+1];
        Arrays.fill(costArr, P);
        bfs(q, visited);

        br.close();
    }//init

    
}//class