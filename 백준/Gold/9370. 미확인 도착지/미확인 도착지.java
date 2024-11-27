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
    private static boolean[] isCandidate; // 목적지 후보
    private static boolean[] isTarget; // 목적지


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
        // 가능한 목적지 탐색
        for(int i=1; i<=N; i++) {
            if(isCandidate[i]) {
                if(dijkstra(i)) isTarget[i] = true;
            }
        }

        // 가능한 목적지들을 공백으로 분리시켜 오름차순
        for(int i=1; i<=N; i++) {
            if(isTarget[i]) ans.append(i).append(' ');
        }

        ans.append('\n');
    }//getTarget


    private static boolean dijkstra(int target) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if(o1[1] == o2[1]) return o2[2] - o1[2];
            return o1[1] - o2[1];
        });
        int[] minCost = new int[N+1];
        Arrays.fill(minCost, INF);
        minCost[S] = 0;

        int visited = 0;
        pq.offer(new int[] {S, 0, visited});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int dist = cur[1];
            visited = cur[2];

            if(minCost[node] < dist) continue;
            if(node == target) return visited == 3;

            for(int[] next : list.get(node)) {
                int nextNode = next[0];
                int nextDist = dist + next[1];
                int nextVisited = visited;

                if(isRoadPassed(node, nextNode)) nextVisited = 3;

                if(minCost[nextNode] > nextDist) {
                    minCost[nextNode] = nextDist;
                    pq.offer(new int[] {nextNode, nextDist, nextVisited});
                }
            }
        }

        return false;
    }//dijkstra


    private static boolean isRoadPassed(int a, int b) {
        return (a == G && b == H) || (a == H && b == G);
    }//isRoadPassed


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

        isCandidate = new boolean[N+1]; // 목적지 후보
        isTarget = new boolean[N+1]; // 목적지
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
            int d = Integer.parseInt(st.nextToken()) * 2;

            if(isRoadPassed(a, b)) d--;

            list.get(a).add(new int[] {b, d});
            list.get(b).add(new int[] {a, d});
        }

        // T개의 각 줄마다 정수 x가 주어지는데, T개의 목적지 후보들을 의미
        while(T-- > 0) {
            int x = Integer.parseInt(br.readLine());
            isCandidate[x] = true;
        }

    }//init


}//class