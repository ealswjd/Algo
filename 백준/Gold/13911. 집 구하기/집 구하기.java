import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/13911
public class Main {
    private static final int INF = 200000002;
    private static int V; // 정점의 개수
    private static int M, S; // 맥도날드의 수 M, 스타벅스의 수 S
    private static int x, y; // 맥세권 조건 x, 스세권 조건 y
    private static List<List<int[]>> list;
    private static boolean[] isM; // 맥도날드
    private static boolean[] isS; // 스타벅스


    public static void main(String[] args) throws IOException {
        init();

        int dist = getDistance();
        System.out.print(dist);
    }//main


    private static int getDistance() {
        int minDist = INF;
        int[] mDist = new int[V+1];
        int[] sDist = new int[V+1];
        PriorityQueue<int[]> mQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        PriorityQueue<int[]> sQ = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        Arrays.fill(mDist, INF);
        Arrays.fill(sDist, INF);

        for(int i=1; i<=V; i++) {
            if(isM[i]) {
                mDist[i] = 0;
                mQ.offer(new int[] {i, 0});
            }
            if(isS[i]) {
                sDist[i] = 0;
                sQ.offer(new int[] {i, 0});
            }
        }

        dijkstra(mQ, mDist, x); // 맥도날드
        dijkstra(sQ, sDist, y); // 스타벅스

        for(int i=1; i<=V; i++) {
            if(isM[i] || isS[i]) continue;

            if(mDist[i] <= x && sDist[i] <= y) {
                int sum = mDist[i] + sDist[i];
                minDist = Math.min(minDist, sum);
            }
        }

        if(minDist == INF) return -1;
        return minDist;
    }//getDistance


    private static void dijkstra(PriorityQueue<int[]> pq, int[] distArr, int max) {
        int city, dist;

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            city = cur[0];
            dist = cur[1];

            if(distArr[city] < dist) continue;

            for(int[] next : list.get(city)) {
                int nextCity = next[0];
                int nextDist = next[1] + dist;

                if(nextDist > max) continue;

                if(distArr[nextCity] > nextDist) {
                    distArr[nextCity] = nextDist;
                    pq.offer(new int[] {nextCity, nextDist});
                }
            }
        }

    }//dijkstra


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken()); // 정점의 개수
        int E = Integer.parseInt(st.nextToken()); // 도로의 개수

        list = new ArrayList<>(V+1);
        isM = new boolean[V+1]; // 맥도날드
        isS = new boolean[V+1]; // 스타벅스

        for(int i=0; i<=V; i++) {
            list.add(new ArrayList<>());
        }

        while(E-- > 0) {
            st = new StringTokenizer(br.readLine());
            // u와 v 사이에 가중치가 w인 도로가 존재한다는 뜻
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new int[] {v, w});
            list.get(v).add(new int[] {u, w});
        }

        // 맥도날드의 수 M와 맥세권 조건 x
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        // M개의 맥도날드 정점 번호가 주어진다.
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
            int v = Integer.parseInt(st.nextToken());
            isM[v] = true;
        }

        // 스타벅스의 수 S와 스세권 조건 y
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        // S개의 스타벅스 정점 번호가 주어진다
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<S; i++) {
            int v = Integer.parseInt(st.nextToken());
            isS[v] = true;
        }

        br.close();
    }//init


}//class