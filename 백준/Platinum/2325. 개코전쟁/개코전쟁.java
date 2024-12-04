import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/2325
public class Main {
    private static final int INF = Integer.MAX_VALUE;
    private static int N;
    private static int[] prev;
    private static List<List<int[]>> list;


    public static void main(String[] args) throws IOException {
        init();

        int max = getMax();
        System.out.print(max);
    }//main

    
    private static int getMax() {
        int max = 0;

        // 도로 파괴 전 최단 거리 구하기
        dijkstra(1, N, 0, 0);

        for(int i=N; i!=prev[i]; i=prev[i]) {
            int to = prev[i];
            // 최단 경로에 해당하는 도로 파괴 후 최단 거리 구하기
            int cost = dijkstra(N, 1, i, to);

            // 최단거리가 최대가 되도록
            max = Math.max(max, cost);
        }

        return max;
    }//getMax

    
    private static int dijkstra(int start, int end, int from, int to) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] minCost = new int[N+1];
        Arrays.fill(minCost, INF);

        pq.offer(new int[] {start, 0});
        minCost[start] = 0;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int cost = cur[1];

            if(minCost[node] < cost) continue;
            if(node == end) return cost;

            for(int[] next : list.get(node)) {
                int nNode = next[0];
                int nCost = next[1] + cost;

                // 도로 파괴
                if(node == from && nNode == to) continue;

                if(minCost[nNode] > nCost) {
                    if(start == 1) prev[nNode] = node;
                    minCost[nNode] = nCost;
                    pq.offer(new int[] {nNode, nCost});
                }
            }
        }

        return minCost[end];
    }//dijkstra

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        prev = new int[N+1];
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, t});
            list.get(b).add(new int[] {a, t});
        }

        br.close();
    }//init

    
}//class