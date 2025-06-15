import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/18352
public class Main {
    private static final int MAX = Integer.MAX_VALUE;
    private static int N, K, X;
    private static List<List<Integer>> list;
    private static int[] minDist;


    public static void main(String[] args) throws IOException{
        init();
        dijkstra();
        print();
    }//main


    private static void dijkstra() {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int city = X;
        int dist = 0;

        pq.offer(new int[] {city, dist});
        minDist[city] = dist;

        int[] cur;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            city = cur[0];
            dist = cur[1];

            if(minDist[city] < dist) continue;
            if(dist >= K) continue;

            for(int next : list.get(city)) {
                if(minDist[next] > dist + 1) {
                    pq.offer(new int[] {next, dist + 1});
                    minDist[next] = dist + 1;
                }
            }
        }

    }//dijkstra


    private static void print() {
        StringBuilder ans = new StringBuilder();
        int cnt = 0;

        for(int i=1; i<=N; i++) {
            if(minDist[i] == K) {
                ans.append(i).append('\n');
                cnt++;
            }
        }

        if(cnt == 0) ans.append(-1);

        System.out.print(ans);
    }//print


    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 도시의 개수 N, 도로의 개수 M, 거리 정보 K, 출발 도시의 번호 X가 주어진다.
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        list = new ArrayList<>(N+1);
        minDist = new int[N+1];

        Arrays.fill(minDist, MAX);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            // a번 도시에서 b번 도시로 이동하는 단방향 도로가 존재한다는 의미
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list.get(a).add(b);
        }

        br.close();
    }//init


}//class