import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/9370
public class Main {
    private static final int INF = 1_000_000_000;
    private static int N; // 교차로 개수
    private static int S, G, H; // 예술가들의 출발지, 지나간 도로(G -> H)
    private static List<List<int[]>> list; // 도로 정보
    private static int[] candidate; // 목적지 후보


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();

        while(tc-- > 0) {
            init(br);
            getTarget(ans);
        }

        System.out.print(ans);
    }//main


    private static void getTarget(StringBuilder ans) {
        int[] sCost = dijkstra(S);
        int[] gCost = dijkstra(G);
        int[] hCost = dijkstra(H);

        // 가능한 목적지 탐색
        for(int city : candidate) {
            int gToH = sCost[G] + gCost[H] + hCost[city];
            int hToG = sCost[H] + hCost[G] + gCost[city];

            int min = Math.min(gToH, hToG);

            if(min == sCost[city]) ans.append(city).append(' ');
        }

        ans.append('\n');
    }//getTarget


    private static int[] dijkstra(int start) {
        Queue<Integer> q = new LinkedList<>();
        int[] minCost = new int[N+1];
        Arrays.fill(minCost, INF);
        minCost[start] = 0;

        q.offer(start);

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int[] next : list.get(cur)) {
                int nextNode = next[0];
                int nextDist = minCost[cur] + next[1];

                if(minCost[nextNode] > nextDist) {
                    minCost[nextNode] = nextDist;
                    q.offer(nextNode);
                }
            }
        }

        return minCost;
    }//dijkstra


    private static void init(BufferedReader br) throws IOException {
        // 교차로, 도로, 목적지 후보의 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 교차로 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int T = Integer.parseInt(st.nextToken()); // 목적지 후보 개수

        // 출발지, 지나간 도로(G -> H)
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()); // 출발지
        G = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        candidate = new int[T]; // 목적지 후보
        list = new ArrayList<>(N+1); // 도로 정보

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        // M개의 각 줄마다 3개의 정수 a, b, d가 주어진다
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a와 b 사이에 길이 d의 양방향 도로가 있다는 뜻
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, d});
            list.get(b).add(new int[] {a, d});
        }

        // T개의 각 줄마다 정수 x가 주어지는데, T개의 목적지 후보들을 의미
        for(int i=0; i<T; i++) {
            int x = Integer.parseInt(br.readLine());
            candidate[i] = x;
        }

        Arrays.sort(candidate);
    }//init


}//class