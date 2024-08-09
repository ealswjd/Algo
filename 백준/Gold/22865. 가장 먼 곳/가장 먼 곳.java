import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// https://www.acmicpc.net/problem/22865
public class Main {
    static final int INF = 987654321;
    static int N; // 자취할 땅 후보의 개수
    static int A, B, C; // 세 명의 친구 위치
    static List<List<int[]>> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 자취할 땅 후보의 개수

        // // 세 명의 친구 위치
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        init();

        int M = Integer.parseInt(br.readLine()); // 도로의 개수 
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());

            list.get(D).add(new int[] {E, L});
            list.get(E).add(new int[] {D, L});
        }

        int[] aLen = dijkstra(A); // A 위치에서 거리
        int[] bLen = dijkstra(B); // B 위치에서 거리
        int[] cLen = dijkstra(C); // C 위치에서 거리

        int num = getNum(aLen, bLen, cLen);
        System.out.print(num);
    }//main

    private static int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        int[] minLen = new int[N+1];
        Arrays.fill(minLen, INF);
        minLen[start] = 0;
        pq.offer(new int[] {start, 0});

        int[] cur;
        int to, len;
        while(!pq.isEmpty()) {
            cur = pq.poll();
            to = cur[0];
            len = cur[1];

            if(minLen[to] < len) continue;

            int nextLen;
            for(int[] next : list.get(to)) {
                nextLen = len + next[1];

                if(minLen[next[0]] > nextLen) {
                    minLen[next[0]] = nextLen;
                    pq.offer(new int[] {next[0], nextLen});
                }
            }
        }

        return minLen;
    }//dijkstra

    
    private static int getNum(int[] aLen, int[] bLen, int[] cLen) {
        int num = 0;
        int min;
        int max = 0;

        for(int i=1; i<=N; i++) {
            if(i == A || i == B || i == C) continue;

            min = Math.min(aLen[i], Math.min(bLen[i], cLen[i]));
            if(min != INF && min > max) {
                num = i;
                max = min;
            }
        }

        return num;
    }//getNum

    
    private static void init() {
        list = new ArrayList<>(N+1);

        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
    }//init

}//class