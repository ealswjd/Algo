import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/14221
public class Main {
    private static final int INF = 1_000_000_004;
    private static int N; // 정점의 개수
    private static int[] homeList; // 집의 후보지
    private static int[] storeList; // 편의점
    private static List<List<int[]>> list;


    public static void main(String[] args) throws IOException {
        init();

        int home = getHome();
        System.out.print(home);
    }//main


    private static int getHome() {
        int[] minDist = dijkstra();
        int home = 0;

        for(int cur : homeList) {
            if(minDist[home] > minDist[cur]) {
                home = cur;
            }
        }

        return home;
    }//getHome


    private static int[] dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int[] minDist = new int[N+1];
        Arrays.fill(minDist, INF);

        for(int store : storeList) {
            pq.offer(new int[] {store, 0});
            minDist[store] = 0;
        }

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0]; // 현재 정점
            int dist = cur[1]; // 거리

            if(minDist[node] < dist) continue;

            for(int[] next : list.get(node)) {
                int nextNode = next[0];
                int nextDist = next[1] + dist;

                if(minDist[nextNode] > nextDist) {
                    minDist[nextNode] = nextDist;
                    pq.offer(new int[] {nextNode, nextDist});
                }
            }
        }

        return minDist;
    }//dijkstra


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수

        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, c});
            list.get(b).add(new int[] {a, c});
        }

        // 집의 후보지의 개수 p와 편의점의 개수 q
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        homeList = new int[p]; // 집의 후보지
        storeList = new int[q]; // 편의점

        // 집의 후보지들의 정점번호
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<p; i++) {
            homeList[i] = Integer.parseInt(st.nextToken());
        }

        // 편의점의 정점번호
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<q; i++) {
            storeList[i] = Integer.parseInt(st.nextToken());
        }

        // 거리가 같은 곳이 여러 군데라면 정점 번호가 낮은 곳
        Arrays.sort(homeList);

        br.close();
    }//init


}//class