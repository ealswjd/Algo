import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5972
public class Main {
    static final int NUM=0, C=1, INF=Integer.MAX_VALUE;
    static int N;
    static int[] minCost;
    static boolean[] visited;
    static ArrayList<ArrayList<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 헛간 개수
        int M = Integer.parseInt(st.nextToken()); // 양방향 길 개수

        init();

        while(M-->0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // 소
            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});
        }//while
        br.close();

        int cnt = dijkstra(1, N);
        System.out.print(cnt);
    }//main

    
    private static int dijkstra(int cur, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[C] - o2[C];
            }
        });
        pq.offer(new int[] {cur, 0});
        minCost[cur] = 0;

        int cnt;
        int[] tmp;
        while(!pq.isEmpty()) {
            tmp = pq.poll();
            cur = tmp[NUM];
            cnt = tmp[C];

            if(visited[cur]) continue;
            visited[cur] = true;

            if(cur == end) break;

            for(int[] next : list.get(cur)) {
                if(visited[next[NUM]] || minCost[next[NUM]] <= cnt + next[C]) continue;
                minCost[next[NUM]] = cnt+next[C];
                pq.offer(new int[] {next[NUM], minCost[next[NUM]]});
            }
        }//while

        return minCost[end];
    }//dijkstra

    
    private static void init() {
        list = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }//
        visited = new boolean[N+1];
        minCost = new int[N+1];
        Arrays.fill(minCost, INF);
    }//init

}//class