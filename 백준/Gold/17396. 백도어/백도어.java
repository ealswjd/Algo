import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/17396
public class Main {
    private static final long INF = 30_000_000_001L;
    private static int N;
    private static List<List<int[]>> roadList;
    private static boolean[] isUnsafe;


    public static void main(String[] args) throws IOException {
        init();

        long minTime = getMinTime(0, N-1);
        System.out.print(minTime);
    }//main

    
    private static long getMinTime(int start, int end) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> (int) (o1[1] - o2[1]));
        long[] minCost = new long[N];
        Arrays.fill(minCost, INF);

        int city = start;
        long time = 0;
        pq.offer(new long[] {city, time});
        minCost[start] = 0;

        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            city = (int) cur[0];
            time = cur[1];

            if(minCost[city] < time) continue;
            if(city == end) return time;

            for(int[] next : roadList.get(city)) {
                int nextCity = next[0];
                long nextTime = next[1] + time;

                if(isUnsafe[nextCity]) continue;

                if(minCost[nextCity] > nextTime) {
                    minCost[nextCity] = nextTime;
                    pq.offer(new long[] {nextCity, nextTime});
                }
            }
        }

        return -1;
    }//getMinTime

    
    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        roadList = new ArrayList<>(N);
        isUnsafe = new boolean[N];

        for(int i=0; i<N; i++) {
            roadList.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++) {
            if(st.nextToken().equals("1")) {
                isUnsafe[i] = true;
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a번째 분기점과 b번째 분기점 사이를 지나는데 t만큼 시간이 걸리는 것을 의미
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            roadList.get(a).add(new int[] {b, t});
            roadList.get(b).add(new int[] {a, t});
        }

        br.close();
    }//init

    
}//class