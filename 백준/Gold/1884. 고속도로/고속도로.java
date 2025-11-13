import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/1884
public class Main {
    private static class Road implements Comparable<Road> {
        int to; // 도착 도시 번호
        int len; // 도로의 길이
        int cost; // 도로의 통행료
        Road(int to, int len, int cost) {
            this.to = to;
            this.len = len;
            this.cost = cost;
        }
        @Override
        public int compareTo(Road o) {
            return this.len - o.len;
        }
    }

    private static final int INF = 987654321;
    private static int K, N; // 교통비, 도시의 수
    private static List<List<Road>> roadList; // 도로 정보


    public static void main(String[] args) throws IOException {
        init();
        int result = getMinLen();

        System.out.print(result);
    }//main


    private static int getMinLen() {
        PriorityQueue<Road> pq = new PriorityQueue<>();
        int[][] minDist = new int[K+1][N+1]; // k로 n까지 갈 수 있는 가장 짧은 길이
        int min = INF; // 제일 짧은 경로의 길이
        int city = 1;  // 도시
        int len = 0;   // 길이
        int cost = 0;  // 비용

        for(int k=0; k<=K; k++) {
            Arrays.fill(minDist[k], INF);
        }

        pq.offer(new Road(city, len, cost));
        minDist[cost][city] = len;

        Road cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            city = cur.to;
            len = cur.len;
            cost = cur.cost;

            // 이동 불가
            if(minDist[cost][city] > len) {
                continue;
            }
            // 집 도착
            if(city == N) {
                min = Math.min(min, len);
                continue;
            }

            for(Road next : roadList.get(city)) {
                int nCity = next.to; // 다음 도시
                int nLen = next.len + len; // 다음 도시 이동 거리
                int nCost = next.cost + cost; // 다음 도시 이동 비용

                // 교통비 내에서 이동 가능하며 현재 이동 경로가 더 짧은지 확인
                if(nCost <= K && minDist[nCost][nCity] > nLen) {
                    minDist[nCost][nCity] = nLen;
                    pq.offer(new Road(nCity, nLen, nCost));
                }
            }

        }

        // 제일 짧은 경로의 길이를 출력. 가능한 경로가 없을 때에는 -1을 출력
        return min == INF ? -1 : min;
    }//getMinLen


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 교통비 K가 주어진다.
        K = Integer.parseInt(br.readLine());
        // 도시의 숫자 N
        N = Integer.parseInt(br.readLine());
        // 도로의 숫자 R이 주어진다.
        int R = Integer.parseInt(br.readLine());

        roadList = new ArrayList<>(N+1); // 도로 정보
        for(int i=0; i<=N; i++) {
            roadList.add(new ArrayList<>());
        }

        // R개의 줄에 각 도로의 정보가 주어지는데
        StringTokenizer st;
        while(R-- > 0) {
            // 각 줄은 네 개의 숫자 s, d, l, t로 이루어져 있다
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 출발 도시 번호
            int d = Integer.parseInt(st.nextToken()); // 도착 도시 번호
            int l = Integer.parseInt(st.nextToken()); // 도로의 길이
            int t = Integer.parseInt(st.nextToken()); // 도로의 통행료

            // 각 도로는 일방통행로이다.
            roadList.get(s).add(new Road(d, l, t));
        }

        br.close();
    }//init


}//class