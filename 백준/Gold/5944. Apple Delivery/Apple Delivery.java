import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/5944
public class Main {
    static final int INF = Integer.MAX_VALUE;
    static int P;
    static List<List<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken()); // 길 개수
        P = Integer.parseInt(st.nextToken()); // 목초지 개수
        int PB = Integer.parseInt(st.nextToken()); // 출발지
        int PA1 = Integer.parseInt(st.nextToken()); // 친구1 위치
        int PA2 = Integer.parseInt(st.nextToken()); // 친구2 위치

        init();

        while(C-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            list.get(a).add(new int[] {b, d});
            list.get(b).add(new int[] {a, d});
        }
        br.close();

        int[] distFromPB = dijkstra(PB);
        int[] distFromPA1 = dijkstra(PA1);
        int[] distFromPA2 = dijkstra(PA2);

        int minDist = Math.min(distFromPB[PA1] + distFromPA1[PA2]
                , distFromPB[PA2] + distFromPA2[PA1]);

        System.out.print(minDist);
    }//main

    
    private static int[] dijkstra(int start) {
        int[] minDist = new int[P + 1];
        Arrays.fill(minDist, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});
        minDist[start] = 0;

        int[] cur;
        int to, dist;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            to = cur[0];
            dist = cur[1];

            if (dist > minDist[to]) continue;

            for (int[] next : list.get(to)) {
                int nTo = next[0];
                int nDist = dist + next[1];

                if (nDist < minDist[nTo]) {
                    minDist[nTo] = nDist;
                    pq.offer(new int[]{nTo, nDist});
                }
            }
        }

        return minDist;
    }//dijkstra

    
    private static void init() {
        list = new ArrayList<>(P+1);

        for(int i=0; i<=P; i++) {
            list.add(new ArrayList<>());
        }
    }//init

    
}//class